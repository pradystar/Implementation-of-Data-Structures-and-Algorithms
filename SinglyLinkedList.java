package cs6301.g32;

import java.util.*;

public class SinglyLinkedList<T> implements Iterable<T> {

    /**
     * Class Entry holds a single node of the list
     */
    static class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public SinglyLinkedList() {
        head = new Entry<>(null, null);
        tail = head;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new SLLIterator<>(this);
    }

    private class SLLIterator<E> implements Iterator<E> {
        SinglyLinkedList<E> list;
        Entry<E> cursor, prev;
        boolean ready;  // is item ready to be removed?

        SLLIterator(SinglyLinkedList<E> list) {
            this.list = list;
            cursor = list.head;
            prev = null;
            ready = false;
        }

        public boolean hasNext() {
            return cursor.next != null;
        }

        public E next() {
            prev = cursor;
            cursor = cursor.next;
            ready = true;
            return cursor.element;
        }

        // Removes the current element (retrieved by the most recent next())
        // Remove can be called only if next has been called and the element has not been removed
        public void remove() {
            if (!ready) {
                throw new NoSuchElementException();
            }
            prev.next = cursor.next;
            // Handle case when tail of a list is deleted
            if (cursor == list.tail) {
                list.tail = prev;
            }
            cursor = prev;
            ready = false;  // Calling remove again without calling next will result in exception thrown
            size--;
        }
    }

    // Add new elements to the end of the list
    public void add(T x) {
        tail.next = new Entry<>(x, null);
        tail = tail.next;
        size++;
    }

    public void printList() {
    /* Code without using implicit iterator in for each loop:

        Entry<T> x = head.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
	*/

        System.out.print(this.size + ": ");
        for (T item : this) {
            System.out.print(item + " ");
        }

        System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    public void unzip() {
        if (size < 3) {  // Too few elements.  No change.
            return;
        }

        Entry<T> tail0 = head.next;
        Entry<T> head1 = tail0.next;
        Entry<T> tail1 = head1;
        Entry<T> c = tail1.next;
        int state = 0;

        // Invariant: tail0 is the tail of the chain of elements with even index.
        // tail1 is the tail of odd index chain.
        // c is current element to be processed.
        // state indicates the state of the finite state machine
        // state = i indicates that the current element is added after taili (i=0,1).
        while (c != null) {
            if (state == 0) {
                tail0.next = c;
                tail0 = c;
                c = c.next;
            } else {
                tail1.next = c;
                tail1 = c;
                c = c.next;
            }
            state = 1 - state;
        }
        tail0.next = head1;
        tail1.next = null;
    }

    /**
     * This method reverses the list by making recursive calls
     */
    public void rreverse(){
        Entry<T> item1 = this.head.next;
        rreverse(this.head, this.head.next);
        this.tail = item1;
        item1.next = null;
    }

    /**
     * At start of the recursive call item1.next = item2, at the end of the fucntion item2.next = item1
     * @param item1
     * @param item2
     */
    private void rreverse(Entry<T> item1, Entry<T> item2){
        if(item2 == null){
            this.head.next = item1;
            return;
        }
        Entry<T> temp = item2.next;
        item2.next = item1;
        rreverse(item2, temp);
    }

    /**
     * This method reverses the list iteratively
     */

    public void ireverse(){
        Entry<T> curr = this.head.next;
        Entry<T> prev = null;
        while (curr != null){
            Entry<T> temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        this.tail = this.head.next;
        this.head.next = prev;

    }

    /**
     * This method prints the list in reverse recursively without actually reversing it
     */
    public void rprint_reverse(){
        rprint_reverse(this.head.next);
        System.out.println();
    }

    /**
     * This is a recursive method that will recurse on the elements of the linked list.
     *
     * @param item : An entry in the linked list
     */
    private void rprint_reverse(Entry<T> item) {
        if(item.next == null){
            System.out.print(item.element + " ");
            return;
        }
        rprint_reverse(item.next);
        System.out.print(item.element + " ");
    }

    public void iprint_reverse(){
        ArrayDeque<T> stack = new ArrayDeque<>();
        for (T item : this) {
            stack.push(item);
        }
        while( !stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    /**
     * Rearrange the elements of the list by linking the elements at kth index
     * followed by the elements at k+1 th index. Implemented by rearranging pointers
     * of existing elements without allocating any new elements.
     *
     * @param k : int : index
     */
    public void multiUnzip(int k) {
        if (size < 2 * k - 1) { // too few elements
            return; // no change
        }
        Entry<T> tail0 = head.next;
        Entry<T>[] heads = new Entry[k-1];
        Entry<T>[] tails = new Entry[k-1];
        Entry<T> prev_tail = tail0;
        for(int i = 0; i < k - 1; i++){
            heads[i] = prev_tail.next;
            prev_tail = heads[i];
            tails[i] = heads[i];
        }
        Entry<T> c = prev_tail.next;
        int state = 0;
        while(c != null){
            if(state == 0){
                tail0.next = c;
                tail0 = c;
                c = c.next;
            } else {
                for (int i = 0; i < k - 1; i++) {
                    tails[i].next = c;
                    tails[i] = c;
                    c = c.next;
                    if(c == null) break;
                }
            }
            state = 1 - state;
        }
        tail0.next = heads[0];
        for(int i = 1; i < k - 1; i++){
            tails[i-1].next = heads[i];
        }
        tails[k - 2].next = null;
    }

    public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst.add(new Integer(i));
        }
        System.out.println("Before unzip");
        lst.printList();
        System.out.println("after unzip");
        lst.multiUnzip(3);
        lst.printList();
        System.out.println("after reverse");
        lst.ireverse();
        lst.printList();
        lst.iprint_reverse();
        lst.printList();
    }
}

/* Sample input:
   1 2 1 2 1 1 1 2 1 1 2 0
   Sample output:
10: 1 2 3 4 5 6 7 8 9 10
1
9: 2 3 4 5 6 7 8 9 10
2
8: 3 4 5 6 7 8 9 10
3
4
5
7: 3 4 6 7 8 9 10
6
7
6: 3 4 6 8 9 10
6: 3 4 6 8 9 10
6: 3 6 9 4 8 10
*/