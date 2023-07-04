import java.util.*;

//You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is
// the type of fruit the ith tree produces.
//
//        You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
//
//        You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
//        Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits
//        must fit in one of your baskets.
//        Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
//        Given the integer array fruits, return the maximum number of fruits you can pick.
public class TotalFruit {
    public int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> map = new HashMap();

        int n = fruits.length;
        int rpointer=0;
        int maxFruits=Integer.MIN_VALUE;
        int lpointer=0;
        for(rpointer=0;rpointer<n;rpointer++)
        {
// if same type of fruit occurs keep on adding and keep the latest index of fruit in hashmap  -->
            if(map.containsKey(fruits[rpointer])) {
                map.put(fruits[rpointer],rpointer);
            }
// else if the different type of fruit havent reached limit in basket and keep the latest index of fruit in hashmap
            else if(map.size()<2) {
            map.put(fruits[rpointer],rpointer);
            }
//remove fruit with the min index so that we loose on less fruits and also dont forget to add new fruit to the basket
            else {
                int min = Integer.MAX_VALUE;
                int minFruit = Integer.MAX_VALUE;
                for(int val:map.keySet())//遍历hashmap里面的水果种类
                {

                    if(min>map.get(val))//获得能够完全删除一种水果出现的最后索引
                    {
                        min=map.get(val);
                        minFruit = val;
                    }
                }
                lpointer=min+1;//左指针改变位置，移除该种类水果
                map.remove(minFruit);
                map.put(fruits[rpointer],rpointer);
            }
// check count of fruits in every iteration and keep track of maximum
            int count =rpointer-lpointer+1;
            maxFruits =Math.max(count,maxFruits);
        }
        return maxFruits;
    }

}