public class Leetcode11 {
  public int maxArea(int[] height) {
    int left = 0, right = height.length-1;
    int res = 0;
    while(left<right){
      res = Math.max(Math.min(height[left],height[right])*(right-left),res);
      if(height[left]<=height[right]) left++;
      else{
        right--;
      }
    }
    return res;
  }
}
