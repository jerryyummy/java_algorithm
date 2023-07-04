//You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
//        Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//        Return the maximum amount of water a container can store.
//
//        Notice that you may not slant the container

public class MaxArea {
    public int maxArea(int[] height) {
        int i=0,j=height.length-1;
        int result = Integer.MIN_VALUE;
        while (i < j){
            result = Math.max(result,(j-i)*Math.min(height[i],height[j]));
            if (height[i]<height[j]){
                i++;
            } else if (height[i]>height[j]) {
                j--;
            }else {
                i++;
                j--;
            }
        }
        return result;
    }

}
