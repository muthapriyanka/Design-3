import java.util.*;
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextInt;
   Stack<Iterator<NestedInteger>> stack;
   public NestedIterator(List<NestedInteger> nestedList) {
       stack = new Stack<Iterator<NestedInteger>>();
       stack.push(nestedList.iterator());
   }

   @Override
   public Integer next() {
       return nextInt != null ? nextInt.getInteger() : null; //Just in case
   }
   @Override
   public boolean hasNext() {
       while (!stack.isEmpty()) {
           if (!stack.peek().hasNext()) stack.pop();
           else if ((nextInt = stack.peek().next()).isInteger()) return true;
           else stack.push(nextInt.getList().iterator());
       }
       return false;
   }
   public static void main(String[] args) {
    // Create a nested list: [[1,1], 2, [1,1]]
    List<NestedInteger> nestedList = new ArrayList<>();
    List<NestedInteger> firstList = new ArrayList<>();
    firstList.add(new SimpleNestedInteger(1));
    firstList.add(new SimpleNestedInteger(1));
    nestedList.add(new SimpleNestedInteger(firstList));
    nestedList.add(new SimpleNestedInteger(2));
    List<NestedInteger> secondList = new ArrayList<>();
    secondList.add(new SimpleNestedInteger(1));
    secondList.add(new SimpleNestedInteger(1));
    nestedList.add(new SimpleNestedInteger(secondList));

    NestedIterator i = new NestedIterator(nestedList);
    while (i.hasNext()) {
        System.out.print(i.next() + " ");
    }
}
   }