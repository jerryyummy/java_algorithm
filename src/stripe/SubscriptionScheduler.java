package stripe;

import java.util.*;

public class SubscriptionScheduler {

    // 定义事件类型优先级，确保同一天的事件按逻辑顺序处理：
    // Change/Renew (更新状态) > Start (开始) > Email (发送提醒)
    enum EventType {
        CHANGE_OR_RENEW(1),
        SUBSCRIPTION_START(2),
        EMAIL_NOTIFICATION(3);

        int priority;
        EventType(int p) { this.priority = p; }
    }

    // 内部类：表示一个时间轴上的事件
    static class Event implements Comparable<Event> {
        int day;
        EventType type;
        String userName;
        String planName; // 快照：生成事件时的套餐名
        int version;     // 快照：生成事件时的版本号

        // 仅用于邮件事件
        String emailType;

        // 仅用于变更/续费事件
        String newPlan;
        int extensionDays;
        boolean isRenewal;

        public Event(int day, EventType type, String userName, int version) {
            this.day = day;
            this.type = type;
            this.userName = userName;
            this.version = version;
        }

        @Override
        public int compareTo(Event other) {
            if (this.day != other.day) {
                return Integer.compare(this.day, other.day);
            }
            return Integer.compare(this.type.priority, other.type.priority);
        }
    }

    // 内部类：用户状态
    static class User {
        String name;
        String plan;
        int startDate;
        int endDate;
        int version; // 核心：用于验证事件是否有效

        public User(String name, String plan, int startDate, int duration) {
            this.name = name;
            this.plan = plan;
            this.startDate = startDate;
            this.endDate = startDate + duration;
            this.version = 0;
        }
    }

    // 全局状态
    Map<String, User> users = new HashMap<>();
    // 假设我们需要从初始数据中推断或存储套餐的标准时长 (Part 2 需要)
    Map<String, Integer> planDurations = new HashMap<>();
    // 发送规则: relativeDay -> messageType ("start", "end", or offsets like -15)
    Map<Object, String> sendSchedule;

    public SubscriptionScheduler(Map<Object, String> schedule) {
        this.sendSchedule = schedule;
    }

    // 主逻辑入口
    public void process(List<Map<String, Object>> userInputs, List<Map<String, Object>> changes) {
        PriorityQueue<Event> eventQueue = new PriorityQueue<>();

        // 1. 初始化用户并生成初始事件
        for (Map<String, Object> u : userInputs) {
            String name = (String) u.get("name");
            String plan = (String) u.get("plan");
            int date = (int) u.get("account_date");
            int duration = (int) u.get("duration");

            // 记录套餐时长供后续 Part 2 使用 (假设同名套餐时长一致)
            planDurations.put(plan, duration);

            User user = new User(name, plan, date, duration);
            users.put(name, user);

            // 调度初始邮件
            scheduleEmails(user, eventQueue);
        }

        // 2. 将变更/续费事件加入队列
        if (changes != null) {
            for (Map<String, Object> ch : changes) {
                String name = (String) ch.get("name");
                int date = (int) ch.get("change_date");

                Event e = new Event(date, EventType.CHANGE_OR_RENEW, name, -1); // Version -1 for external events

                if (ch.containsKey("new_plan")) {
                    e.isRenewal = false;
                    e.newPlan = (String) ch.get("new_plan");
                } else if (ch.containsKey("extension")) {
                    e.isRenewal = true;
                    e.extensionDays = (int) ch.get("extension");
                }
                eventQueue.offer(e);
            }
        }

        // 3. 处理事件循环
        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            User user = users.get(e.userName);

            if (e.type == EventType.CHANGE_OR_RENEW) {
                handleStateChange(e, user, eventQueue);
            } else if (e.type == EventType.EMAIL_NOTIFICATION) {
                // 核心逻辑：版本校验
                // 只有当事件版本 == 用户当前版本时，才处理
                if (e.version == user.version) {
                    System.out.println(e.day + ": [" + e.emailType + "] Subscription for " + user.name + " (" + e.planName + ")");
                }
            }
        }
    }

    // 辅助：生成邮件事件
    private void scheduleEmails(User user, PriorityQueue<Event> queue) {
        for (Map.Entry<Object, String> entry : sendSchedule.entrySet()) {
            Object offsetObj = entry.getKey();
            String msgType = entry.getValue();

            int triggerDay;

            // 处理 "start"
            if (offsetObj instanceof String && offsetObj.equals("start")) {
                // 如果是 Plan Change 后的新排期，通常不再发 Start 邮件，
                // 除非题目要求 Change 当天算新 Start。
                // 这里我们简化处理：只有初始版本(0)才发Start，或者根据具体需求调整。
                // 本题 Part 1 示例输出包含 Start。
                triggerDay = user.startDate;
            }
            // 处理 "end"
            else if (offsetObj instanceof String && offsetObj.equals("end")) {
                triggerDay = user.endDate;
            }
            // 处理 数字偏移 (例如 -15)
            else if (offsetObj instanceof Integer) {
                int offset = (Integer) offsetObj;
                triggerDay = user.endDate + offset;
            } else {
                continue;
            }

            // 创建事件
            Event emailEvent = new Event(triggerDay, EventType.EMAIL_NOTIFICATION, user.name, user.version);
            emailEvent.emailType = msgType;
            emailEvent.planName = user.plan; // 绑定当前套餐名
            queue.offer(emailEvent);
        }
    }

    // 辅助：处理状态变更
    private void handleStateChange(Event e, User user, PriorityQueue<Event> queue) {
        // 更新版本号，立即使队列中旧版本的 Future Events 失效
        user.version++;

        if (e.isRenewal) {
            // Part 3: Renewal
            System.out.println(e.day + ": [Renewed] Subscription for " + user.name + " (" + user.plan + ")");
            user.endDate += e.extensionDays;

        } else {
            // Part 2: Plan Change
            System.out.println(e.day + ": [Changed] Subscription for " + user.name + " (" + user.plan + " -> " + e.newPlan + ")");

            user.plan = e.newPlan;
            // 题目要求: "Recalculate the remaining duration relative to the change date"
            // 通常这意味着：新套餐从变更当日开始算起，拥有完整的套餐时长
            int newDuration = planDurations.getOrDefault(e.newPlan, 30); // 默认30天防NPE

            // user.startDate = e.day; // 视需求而定，通常变更不改变由于"Start"触发的逻辑，只改变结束日
            user.endDate = e.day + newDuration;
        }

        // 基于新的 endDate 和 version 重新排期未来的邮件
        // 注意：这里我们可能会重新加入 "start" 邮件。
        // 如果题目不希望变更后重发 "start"，可以在 scheduleEmails 里加判断 (if triggerDay < currentDay continue)
        scheduleEmailsV2(user, queue, e.day);
    }

    // 优化的排期逻辑：只排期今天及未来的邮件
    private void scheduleEmailsV2(User user, PriorityQueue<Event> queue, int currentDay) {
        for (Map.Entry<Object, String> entry : sendSchedule.entrySet()) {
            Object key = entry.getKey();
            String msg = entry.getValue();
            int triggerDay = -1;

            if ("start".equals(key)) continue; // 变更/续费通常不重发 Start 邮件
            if ("end".equals(key)) triggerDay = user.endDate;
            if (key instanceof Integer) triggerDay = user.endDate + (Integer) key;

            if (triggerDay >= currentDay) {
                Event email = new Event(triggerDay, EventType.EMAIL_NOTIFICATION, user.name, user.version);
                email.emailType = msg;
                email.planName = user.plan;
                queue.offer(email);
            }
        }
    }

    // 测试 Main 函数
    public static void main(String[] args) {
        // 1. 配置规则
        Map<Object, String> schedule = new HashMap<>();
        schedule.put("start", "Welcome");
        schedule.put(-15, "Upcoming Expiry");
        schedule.put("end", "Expired");

        SubscriptionScheduler scheduler = new SubscriptionScheduler(schedule);

        // 2. 用户数据
        List<Map<String, Object>> users = new ArrayList<>();
        Map<String, Object> u1 = new HashMap<>();
        u1.put("name", "Alice");
        u1.put("plan", "Silver");
        u1.put("account_date", 0);
        u1.put("duration", 30);
        users.add(u1);

        // 3. 变更数据 (包含 Plan Change 和 Renewal)
        List<Map<String, Object>> changes = new ArrayList<>();

        // Alice 在第 10 天升级到 Gold (Gold 时长假设也为 30，代码逻辑会自动记录)
        // 为了让代码能查到 Gold 的时长，我们需预设或模拟。这里简单起见，我们在 Process 里会用 getOrDefault。
        // 更好的做法是在 users 输入里包含所有可能的 Plan 定义，这里直接 Hack 一下 planDurations
        scheduler.planDurations.put("Gold", 60);

        Map<String, Object> ch1 = new HashMap<>();
        ch1.put("name", "Alice");
        ch1.put("new_plan", "Gold");
        ch1.put("change_date", 10);
        changes.add(ch1);

        // Alice 在第 50 天续费 15 天
        Map<String, Object> ch2 = new HashMap<>();
        ch2.put("name", "Alice");
        ch2.put("extension", 15);
        ch2.put("change_date", 50);
        changes.add(ch2);

        System.out.println("--- Processing Events ---");
        scheduler.process(users, changes);
    }
}
