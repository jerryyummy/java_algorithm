import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Leetcode691 {
    public int minStickers(String[] stickers, String target) {
        int res = bfs(target,stickers);
        return res;
    }

    public int bfs(String target, String[] stickers){
        int n = target.length();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        // bfs不需要备忘录，但要避免节点重复访问
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int state = queue.remove();
                if (state == (1 << n)-1) return step;
                for (String sticker:stickers){
                    int temp = state;
                    for (char c:sticker.toCharArray()){
                        for (int j = 0; j < target.length(); j++) {
                            if (target.charAt(j)==c && (temp>>j & 1)==0){
                                temp = temp | (1<<j);
                                break;
                            }
                        }
                    }
                    if (temp!=state && !set.contains(temp)){
                        queue.add(temp);
                        set.add(temp);
                    }
                }
            }
            step++;
        }
        return -1;
    }

}
