package xhs;

import java.util.Arrays;
import java.util.Scanner;

/*
题目描述：小红书生态团队在评论审核中，需要对得分接近的评论判定观点相近，这一判断逻辑可以帮助团队灵活的调整评论区的观点统一性/观点多样性。
 现在，将模型简化如下:给定长度为n的整数数组{a1,a2,…an}和一个整数 d。
 若|ai-aj|≤d，则称 ai与aj观点相近。
 一次操作可以选择一对元素，并将其同时从数组中删除(数组长度减少2)。
 经过若干操作后，需要保证数组中不含任何观点相近的元素，且希望保留的元素数量尽可能多。
 请你计算，经过若干操作后，最终保留下来的最大元素数量。输入描述：每个测试文件均包含多组测试数据。
 第一行输入一个整数T(1≤T≤10^4)代表数据组数，每组测试数据描述如下:
 第一行输入两个整数 n和 d(1≤n≤2*10^5;0≤d≤10^9),表示数组长度、观点相近的阈值。
 第二行输入 n个整数 a1,a2,…an(0≤ai≤10^9)，表示数组元素。除此之外，保证单个测试文件的n之和不超过2*10^5。

 输出描述：对于每组测试数据，新起一行输出一个整数，表示最终保留下来的最大元素数量。
 */
public class EcologicalAudit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 读取测试用例数量

        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            int d = scanner.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            Arrays.sort(a);
            int S = 0;
            long last = - (long) 1e10;

            for (int x : a) {
                if (x > last + d) {
                    S++;
                    last = x;
                }
            }

            if ((n - S) % 2 == 0) {
                System.out.println(S);
            } else {
                System.out.println(S - 1);
            }
        }
        scanner.close();
    }
}

/*
我们需要从数组中移除偶数个元素（通过多次移除一对元素实现），使得剩余元素中没有任何两个元素的差值绝对值 ≤ d（即无“观点相近”元素）。
目标是最大化剩余元素的数量。 等价于：找到一个最大子集 S，其中 S 中任意两元素差 > d，且 n - |S| 为偶数（因为移除数量必须偶数）。
该问题可建模为在一维数轴上的点集，禁止距离 ≤ d 的点共存。这是一个区间图（interval graph）的最大独立集问题。
由于数组排序后，冲突关系是连续的，可以用贪心高效求解最大独立集大小（无视奇偶约束下的最大剩余）
 */