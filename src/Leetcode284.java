import java.util.Iterator;
import java.util.NoSuchElementException;

public class Leetcode284 implements Iterator<Integer>{
  private Iterator<Integer> iter;
  private Integer next = null;

  public Leetcode284(Iterator<Integer> iterator) {
    // Avoid an exception being thrown in the constructor.
    if (iterator.hasNext()) {
      next = iterator.next();
    }
    iter = iterator;
  }

  public Integer peek() {
    return next;
  }

  @Override
  public Integer next() {
    /* As per the Java Iterator specs, we should throw a NoSuchElementException
     * if the next element doesn't exist. */
    if (next == null) {
      throw new NoSuchElementException();
    }
    Integer toReturn = next;
    next = null;
    if (iter.hasNext()) {
      next = iter.next();
    }
    return toReturn;
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }
}
