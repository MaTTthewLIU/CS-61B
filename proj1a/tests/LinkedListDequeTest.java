import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
        List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();
        assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

     /**Below, you'll write your own tests for LinkedListDeque.*/
     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     /** size check */
     public void SizeCheck() {
         Deque<Integer> lld1 = new LinkedListDeque<>();
         lld1.addLast(3);
         lld1.addFirst(1);
         lld1.addLast(1);
         lld1.addLast(4);
         lld1.addFirst(0);
         lld1.addLast(-1);
         assertThat(lld1.size()).isEqualTo(6);
     }
     @Test
    /** check remove method. (remove first and last) */
    public void RemoveCheck(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        lld1.addFirst('?');
        lld1.addLast('5');
        lld1.removeFirst();
        lld1.removeLast();
        assertThat(lld1.removeLast()).isEqualTo('T');
        assertThat(lld1.removeFirst()).isEqualTo('L');
    }
    @Test
    /** check empty list */
    public void CheckEmptyList(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        lld1.addFirst('?');
        lld1.addLast('5');
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        assertThat(lld1.toList()).isEmpty();
    }
    @Test
    /** check get method */
    public void GetCheck(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        assertThat(lld1.get(1)).isEqualTo('M');
        assertThat(lld1.get(666)).isEqualTo(null);
        assertThat(lld1.get(-1)).isEqualTo(null);

    }
    @Test
    /** check getRecursive method */
    public void getRecursiveCheck(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
        assertThat(lld1.getRecursive(0)).isEqualTo('L');
        assertThat(lld1.getRecursive(888888)).isEqualTo(null);

    }
    @Test
    /** check toList method */
    public void toListCheck(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        assertThat(lld1.toList()).containsExactly('L', 'M', 'A', 'T', 'T').inOrder();

    }
    @Test
    /** check for add after remove */
    public void AddRemove(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast('o');
        lld1.addLast('k');
        assertThat(lld1.toList()).containsExactly('o','k').inOrder();
        assertThat(lld1.get(0)).isEqualTo('o');
        assertThat(lld1.get(1)).isEqualTo('k');
        assertThat(lld1.removeFirst()).isEqualTo('o');
        assertThat(lld1.removeLast()).isEqualTo('k');
        assertThat(lld1.getRecursive(888)).isEqualTo(null);
    }
    @Test
    /** is empty_false */
    public void emptyC(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isEqualTo(false);
    }
    @Test
    public void a(){
        Deque<Character> lld1 = new LinkedListDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        assertThat(lld1.getRecursive(5)).isEqualTo('T');
    }












}
