import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RandomizedSet {
  Map<Integer, Integer> map; // Maps value to its index in the list
  List<Integer> list; // Stores the elements

  public RandomizedSet() {
    map = new HashMap<>();
    list = new ArrayList<>();
  }

  public boolean insert(int val) {
    if (map.containsKey(val)) {
      return false; // Element already exists, no insertion
    } else {
      list.add(val); // Add element to the list
      map.put(val, list.size() - 1); // Map the element to its index in the list
      return true;
    }
  }

  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false; // Element does not exist, cannot remove
    } else {
      int indexToRemove = map.get(val); // Get index of the element to remove
      int lastElement = list.get(list.size() - 1); // Get the last element in the list
      list.set(indexToRemove, lastElement); // Move the last element to the 'removed' position
      map.put(lastElement, indexToRemove); // Update the map for the last element
      list.remove(list.size() - 1); // Remove the last element
      map.remove(val); // Remove the element from the map
      return true;
    }
  }

  public int getRandom() {
    int randomIndex = (int) (Math.random() * list.size()); // Generate a random index
    return list.get(randomIndex); // Return the element at the random index
  }
}
public class Leetcode380 {

}
