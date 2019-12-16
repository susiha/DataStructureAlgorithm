package com.susiha.datastructure.queue;

import com.susiha.datastructure.base.Array;

public class QueueByArray<T> implements Queue<T> {


    private Array<T> array;
    public QueueByArray(){
        array = new Array<>();
    }
    public QueueByArray(int capacity){
        array = new Array<>(capacity);
    }

    @Override
    public void enQueue(T t) {
        array.addLast(t);
    }

    @Override
    public T deQueue() {
        return array.removeFirst();
    }

    @Override
    public T getFront() {
        return array.getFirst();
    }

    @Override
    public T getTail() {
        return array.getLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }
}
