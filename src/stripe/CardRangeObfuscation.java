package stripe;

import java.util.*;

public class CardRangeObfuscation {

    // 定义区间类，存储偏移量和品牌
    static class Interval {
        long start;
        long end;
        String brand;

        public Interval(long start, long end, String brand) {
            this.start = start;
            this.end = end;
            this.brand = brand;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取 BIN 号
        if (!scanner.hasNext()) return;
        String bin = scanner.next();

        // 读取区间数量 n
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        if (scanner.hasNextLine()) scanner.nextLine(); // 消耗换行符

        List<Interval> intervals = new ArrayList<>();

        // 解析输入区间
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            while (line.trim().isEmpty() && scanner.hasNextLine()) {
                line = scanner.nextLine();
            }
            if (line.trim().isEmpty()) break;

            String[] parts = line.split(",");
            long start = Long.parseLong(parts[0].trim());
            long end = Long.parseLong(parts[1].trim());
            String brand = parts[2].trim();
            intervals.add(new Interval(start, end, brand));
        }

        // 1. 按照起始位置排序
        intervals.sort(Comparator.comparingLong(i -> i.start));

        // 2. 填充起始空隙 (Extend Backward)
        // 如果第一个区间不是从 0 开始，将其延伸至 0
        if (!intervals.isEmpty()) {
            if (intervals.get(0).start > 0) {
                intervals.get(0).start = 0;
            }
        }

        // 3. 填充中间空隙 (Extend Forward)
        // 将当前区间的结束位置延伸到下一个区间的起始位置之前
        for (int i = 0; i < intervals.size() - 1; i++) {
            Interval curr = intervals.get(i);
            Interval next = intervals.get(i + 1);

            // 检查是否存在间隙
            if (curr.end < next.start - 1) {
                curr.end = next.start - 1;
            }
        }

        // 4. 填充末尾空隙 (Extend Forward)
        // 如果最后一个区间没有覆盖到最大值，将其延伸
        if (!intervals.isEmpty()) {
            Interval last = intervals.get(intervals.size() - 1);
            if (last.end < 9999999999L) {
                last.end = 9999999999L;
            }
        }

        // 5. 格式化输出
        for (Interval interval : intervals) {
            // 使用 %010d 确保偏移量补齐 10 位
            String startFull = bin + String.format("%010d", interval.start);
            String endFull = bin + String.format("%010d", interval.end);
            System.out.println(startFull + "," + endFull + "," + interval.brand);
        }

        scanner.close();
    }
}
