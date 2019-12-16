package com.susiha.datastructure.queue;

import com.susiha.datastructure.base.LinkedList;

public class QueueByLinked<T> implements Queue<T> {


    private LinkedList<T> list;
    public QueueByLinked(){
        list = new LinkedList<>();
    }
    @Override
    public void enQueue(T t) {
        list.addLast(t);
    }

    @Override
    public T deQueue() {
        return list.removeFirst();
    }

    @Override
    public T getFront() {
        return list.getFirst();
    }

    @Override
    public T getTail() {
        return list.getLast();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }
}
