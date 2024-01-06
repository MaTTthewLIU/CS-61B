import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    // initial size must be 8
    public void InitialSizeCheck() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(3);
        lld1.addFirst(1);
        lld1.addLast(1);
        lld1.addLast(4);
        lld1.addFirst(0);
        lld1.addLast(-1);
        assertThat(lld1.size()).isEqualTo(6);
    }

    @Test
    public void RemoveCheck() {
        Deque<Character> lld1 = new ArrayDeque<>();
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
    public void CheckEmptyList() {
        Deque<Character> lld1 = new ArrayDeque<>();
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
    public void GetCheck() {
        Deque<Character> lld1 = new ArrayDeque<>();
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
    public void toListCheck() {
        Deque<Character> lld1 = new ArrayDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        assertThat(lld1.toList()).containsExactly('L', 'M', 'A', 'T', 'T').inOrder();
    }

    @Test
    public void AddRemove() {
        Deque<Character> lld1 = new ArrayDeque<>();
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
        assertThat(lld1.toList()).containsExactly('o', 'k').inOrder();
        assertThat(lld1.get(0)).isEqualTo('o');
        assertThat(lld1.get(1)).isEqualTo('k');
        assertThat(lld1.removeFirst()).isEqualTo('o');
        assertThat(lld1.removeLast()).isEqualTo('k');
    }

    @Test
    /** is empty_false */
    public void emptyC() {
        Deque<Character> lld1 = new ArrayDeque<>();
        lld1.addLast('M');
        lld1.addFirst('L');
        lld1.addLast('A');
        lld1.addLast('T');
        lld1.addLast('T');
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void addd() {
        Deque<Character> lld1 = new ArrayDeque<>();
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
        lld1.addFirst('T');
        lld1.addFirst('S');
        assertThat(lld1.get(0)).isEqualTo('S');
        assertThat(lld1.get(1)).isEqualTo('T');
    }

    @Test
    public void largeadd() {
        Deque<Character> lld1 = new ArrayDeque<>();
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        lld1.addFirst('L');
        assertThat(lld1.size()).isEqualTo(22);
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.removeFirst()).isEqualTo('L');
        assertThat(lld1.isEmpty()).isEqualTo(true);
    }
    @Test
    public void largedds() {
        Deque<Character> lld1 = new ArrayDeque<>();
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        lld1.addLast('M');
        assertThat(lld1.size()).isEqualTo(20);
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.removeLast()).isEqualTo('M');
        assertThat(lld1.isEmpty()).isEqualTo(true);
        assertThat(lld1.size()).isEqualTo(0);
    }
    @Test
    public void sadwdwa() {
        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addLast(0);
        lld1.removeFirst();
        lld1.addLast(2);
        lld1.get(0) ;
        lld1.removeLast() ;
        lld1.addFirst(5);
        lld1.removeFirst();
        lld1.addFirst(7);
        lld1.removeFirst();
        lld1.addFirst(9);


    }
}


