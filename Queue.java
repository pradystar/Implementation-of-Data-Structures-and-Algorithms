package cs6301.g32;

import cs6301.g00.Timer;

import java.util.Iterator;

/**
 * @author prady
 * Ver 1.0:    9/10/17
 */

public class Queue<T> implements Iterable<T> {

    private T[] queue;
    private static final int DEFAULT_CAPACITY = 16;
    private int size;
    private int head, tail;

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return queue.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    Queue() {
        this.queue = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    @SuppressWarnings("unchecked")
    Queue(int capacity) {
        assert capacity >= DEFAULT_CAPACITY;
        this.queue = (T[]) new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * This method adds an element to the queue. If the queue size is over 90% then this methods doubles the capacity of the queue
     *
     * @param item : Element to be added to the queue
     * @return : true if the element was added successfully
     */
    public boolean offer(T item) {
        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        if (++size > 0.9 * queue.length) {
            resize(queue.length * 2);
        }
        return true;
    }

    /**
     * This method removes and returns element at the head of the queue. If the queue is less than 25% occupied
     * then it reduces the size of the queue
     * only if the new size is not less than DEFAULT_CAPACITY
     *
     * @return Element at the head of the queue or null if the queue is empty
     */
    public T poll() {
        if (size == 0) return null;
        T item = queue[head];
        queue[head] = null;
        head = head + 1;
        size = size - 1;
        if ((size < (0.25 * queue.length)) && ((queue.length / 2) >= DEFAULT_CAPACITY)) {
            resize(queue.length / 2);
        }
        return item;
    }

    /**
     * This method returns element at the head of the queue
     *
     * @return Element at the head of the queue or null if the queue is empty
     */
    public T peek() {
        return queue[head];
    }

    /**
     * This method resizes the array to the specified capacity
     *
     * @param capacity : int : the new capacity of the array
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= DEFAULT_CAPACITY;
        T[] tmp = (T[]) new Object[capacity];
        Iterator it = this.iterator();
        int i = 0;
        while (true) {
            tmp[i++] = (T) it.next();
            if (!it.hasNext()) break;
        }
        queue = tmp;
        head = 0;
        tail = size - 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator<>();
    }

    class QueueIterator<T> implements Iterator<T> {
        private int cursor; // maintains state of the iterator
        private int counter;

        QueueIterator() {
            cursor = head;
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            T item = (T) queue[cursor];
            cursor = (cursor + 1) % queue.length;
            counter = counter + 1;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return null;
        }
        Iterator<T> it = this.iterator();
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(it.next() + " ");
            if (!it.hasNext()) return sb.toString().trim();
        }
    }

    public static void main(String[] args) {
        Queue<Integer> integerQueue = new Queue<>();
        System.out.println("Queue created with capacity : " + integerQueue.getCapacity());
        for (int i = 1; i <= 14; i++) {
            integerQueue.offer(i);
        }
        Timer t = new Timer();
        System.out.println("Elements in queue:");
        System.out.println(integerQueue);
        System.out.println("Size of queue: " + integerQueue.size);
        System.out.println("Total capacity of queue: " + integerQueue.getCapacity());
        System.out.println("-------------Queue resize-------------");
        integerQueue.offer(15);
        System.out.println("Elements in queue:");
        System.out.println(integerQueue);
        System.out.println("Size of queue: " + integerQueue.size);
        System.out.println("Total capacity of queue: " + integerQueue.getCapacity());
        System.out.println("-------------Queue shrink-------------");
        for(int i = 1; i<=8; i++){
            System.out.println("Removed " + integerQueue.poll());
        }
        System.out.println("Element at top: " + integerQueue.peek());
        System.out.println("Elements in queue:");
        System.out.println(integerQueue);
        System.out.println("Size of queue: " + integerQueue.size);
        System.out.println("Total capacity of queue: " + integerQueue.getCapacity());
        System.out.println(t.end());
    }
}
