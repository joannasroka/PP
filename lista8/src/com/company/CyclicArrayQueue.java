package com.company;

import java.util.ArrayList;

public class CyclicArrayQueue<E> implements MyQueue<E> {
    private ArrayList<E> queue;
    private int size; // pojemnosc
    private int first;
    private int current;

    public CyclicArrayQueue(int size) {
        this.size = size;
        queue = new ArrayList<>(size + 1); // bo jedno miejsce w pamieci jest marnowane
        first = 0;
        current = 0;
        for (int i = 0; i < size+1; i++) {
            queue.add(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return first == current;
    }

    @Override
    public boolean isFull() {
        return (current + 1) % size == first;
    }

    @Override
    public void enqueue(E x) throws FullException {
        if (this.isFull()) throw new FullException();
        queue.set(current,x);
        current = (current + 1) % size;
    }

    @Override
    public void dequeue() {
        if (this.isEmpty()) return;
        first = (first+1) % size;
    }

    @Override
    public E first() throws EmptyException {
        if(this.isEmpty()) throw new EmptyException();
        return queue.get(first);
    }


}
