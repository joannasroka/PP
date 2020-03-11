package com.company;

public class Test {

    public void test() throws FullException, EmptyException {
        CyclicArrayQueue<Integer> queue = new CyclicArrayQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(2);
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.isEmpty());

    }
}
