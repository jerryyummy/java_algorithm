import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Leetcode120 {
//  public int minimumTotal(List<List<Integer>> triangle) {
//    HashMap<Pair,Integer> map=new HashMap<>();
//    return fn(0, 0, triangle,map);
//  }
//
//  public static int fn(int i, int j, List<List<Integer>> triangle, HashMap<Pair,Integer> map) {
//    // Base Case: If we reach the bottom of the triangle
//    Pair key=new Pair(i,j);
//    if(map.containsKey(key))
//      return map.get(key);
//    if (i == triangle.size() - 1) {
//      return triangle.get(i).get(j);
//    }
//
//    // Recurrence Relations
//    int take1 = fn(i + 1, j, triangle,map)+ triangle.get(i).get(j);
//    int take2 = fn(i + 1, j + 1, triangle,map)+ triangle.get(i).get(j);
//
//    // Return the minimum of the two possible paths
//    map.put(key, Math.min(take1, take2));
//    return Math.min(take1, take2) ;
//  }

   public int minimumTotal(List<List<Integer>> triangle) {
       List<List<Integer>> dp = new ArrayList<>();
       dp.add(triangle.get(0));
       for(int i = 1;i < triangle.size(); i++){
           List<Integer> temp = new ArrayList<>();
           for( int j = 0; j<triangle.get(i).size(); j++){
               if(j==0){
                   temp.add(dp.get(i-1).get(j)+triangle.get(i).get(j));
               }else if(j==triangle.get(i).size()-1){
                   temp.add(dp.get(i-1).get(j-1)+triangle.get(i).get(j));
               }else{
                   temp.add(Math.min(dp.get(dp.size()-1).get(j),dp.get(dp.size()-1).get(j-1))+triangle.get(i).get(j));
               }
           }
           dp.add(temp);
       }
       return Collections.min(dp.get(dp.size()-1));
   }
}
