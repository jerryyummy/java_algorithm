import java.util.*;

public class Leetcode127 {
    int res =Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        /* 使用bfs
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int step = 0;
        queue.add(beginWord);
        visited.add(beginWord);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                if (Objects.equals(cur, endWord)) return step;
                for (String neighbor:wordList){
                    if (!visited.contains(neighbor) && judge(cur,neighbor)){
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            step++;
        }

        return 0;
         */

        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;
        Deque<String> que = new ArrayDeque<>();
        que.offer(beginWord);
        int result = 1;
        while(!que.isEmpty()) {
            int size = que.size();
            for(int k = 0; k < size; k++){
                String word = que.poll();
                if(word.equals(endWord)) return result;
                int l = word.length();
                for(int i = 0; i < l; i++) {
                    char[] array = word.toCharArray();
                    // 全小写字母
                    for(int j = 0; j < 26; j++){
                        array[i] = (char)('a' + j);
                        String newW = new String(array);
                        if(set.contains(newW)){
                            que.offer(newW);
                            //撤销
                            set.remove(newW);
                        }
                    }
                }
            }
            result++;
        }
        return 0;
    }

    public boolean judge(String word1, String word2){
        int count  = 0;
        int i = 0;
        while(i<word1.length()){
            if(word1.charAt(i)==word2.charAt(i)){
                i++;
                continue;
            }else{
                count++;
                if (count>1) return false;
            }
            i++;
        }
        return count==1;
    }
}
