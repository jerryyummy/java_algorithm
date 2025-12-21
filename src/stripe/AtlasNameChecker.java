package stripe;

import java.util.*;

public class AtlasNameChecker {

    // 存储注册信息: Key = Normalized Name, Value = Account ID
    private Map<String, String> registry = new HashMap<>();

    // 定义需要忽略的后缀 (全部小写，方便匹配)
    private static final Set<String> SUFFIXES = new HashSet<>(Arrays.asList(
            "inc.", "corp.", "llc", "l.l.c.", "llc."
    ));

    // 定义需要忽略的前缀
    private static final Set<String> PREFIXES = new HashSet<>(Arrays.asList(
            "the", "an", "a"
    ));

    public static void main(String[] args) {
        AtlasNameChecker checker = new AtlasNameChecker();

        // 模拟输入数据
        List<String> inputs = Arrays.asList(
                "1|Llama, Inc.",
                "2|The Llama, Inc.",           // 应该冲突
                "3|Llama And Friend, LLC",
                "4|Llama Friend Corp.",        // 应该冲突 (因为 'And' 被忽略, Suffix 被忽略)
                "5|And Llama",                 // 'And' 在开头，保留
                "6|Llama",                     // 应该冲突 (与 1, 2 冲突)
                "RECLAIM,1,Llama, Inc.",       // 1号回收名字
                "7|Llama",                     // 回收后，现在应该可用
                "8|  &  "                      // 空名字测试
        );

        for (String line : inputs) {
            System.out.println(checker.processLine(line));
        }
    }

    public String processLine(String line) {
        // Part 3: 处理 Reclaim 请求
        if (line.startsWith("RECLAIM")) {
            // 注意：题目中 Reclaim 用逗号分隔
            String[] parts = line.split(",", 3);
            if (parts.length < 3) return ""; // 错误处理

            String accountId = parts[1];
            String originalName = parts[2];

            String normalized = normalize(originalName);

            // 校验所有权：只有注册者才能注销
            if (registry.containsKey(normalized) && registry.get(normalized).equals(accountId)) {
                registry.remove(normalized);
                return ""; // 题目未明确Reclaim的输出，通常系统内部操作不返回UI消息，或返回ACK
            }
            return "";
        }

        // Part 1 & 2: 处理注册请求
        // 格式: account_id|proposed_name
        String[] parts = line.split("\\|", 2);
        if (parts.length < 2) return "Name Not Available"; // 格式错误

        String accountId = parts[0];
        String proposedName = parts[1];

        String normalized = normalize(proposedName);

        // 规则：如果是空名字，不可用
        if (normalized.isEmpty()) {
            return accountId + "|Name Not Available";
        }

        // 检查是否已存在
        if (registry.containsKey(normalized)) {
            return accountId + "|Name Not Available";
        } else {
            // 注册成功
            registry.put(normalized, accountId);
            return accountId + "|Name Available";
        }
    }

    /**
     * 核心 Normalization 逻辑
     */
    private String normalize(String name) {
        if (name == null) return "";

        // 1. Ignore case
        String lower = name.toLowerCase();

        // 2. Treat & and , as spaces
        // 3. Collapse consecutive spaces (在 split 时使用正则处理)
        String processed = lower.replaceAll("[&,]", " ");

        // 分割单词
        String[] tokens = processed.trim().split("\\s+");
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokens));

        // 如果只有空格或空字符串
        if (tokenList.isEmpty() || (tokenList.size() == 1 && tokenList.get(0).isEmpty())) {
            return "";
        }

        // 4. Ignore "And" unless it appears at the start
        // 这里的 start 指的是原始字符串分词后的第一个位置
        // 我们倒序遍历删除，避免索引错位
        for (int i = tokenList.size() - 1; i >= 0; i--) {
            if (tokenList.get(i).equals("and")) {
                // 如果不是第一个单词，则移除
                if (i != 0) {
                    tokenList.remove(i);
                }
            }
        }

        if (tokenList.isEmpty()) return "";

        // 5. Ignore standard company suffixes (从末尾移除)
        // 使用 while 循环，因为可能出现 "Name Inc LLC" 这种多个后缀的情况
        // 题目未明确是否递归移除，但通常是移除末尾的匹配项
        while (!tokenList.isEmpty()) {
            String last = tokenList.get(tokenList.size() - 1);
            if (SUFFIXES.contains(last)) {
                tokenList.remove(tokenList.size() - 1);
            } else {
                break;
            }
        }

        if (tokenList.isEmpty()) return "";

        // 6. Ignore leading "The", "An", "A" (从开头移除)
        // 注意：只移除一次，还是递归移除？通常 "The A Llama" -> "Llama"
        // 题目示例: "The Llama" -> "Llama"。
        while (!tokenList.isEmpty()) {
            String first = tokenList.get(0);
            if (PREFIXES.contains(first)) {
                tokenList.remove(0);
            } else {
                break;
            }
        }

        // 重新组合
        return String.join(" ", tokenList);
    }
}
