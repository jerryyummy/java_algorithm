import java.util.ArrayList;
import java.util.List;

public class Leetcode986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while(i<firstList.length && j<secondList.length){
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end   = Math.min(firstList[i][1], secondList[j][1]);

            // 有交集才加入
            if (start <= end) {
                list.add(new int[]{start, end});
            }

            // 谁先结束，移动谁
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        int[][] res = new int[list.size()][2];
        for(int k=0;k<list.size();k++){
            res[k][0] = list.get(k)[0];
            res[k][1] = list.get(k)[1];
        }
        return res;
    }
}
