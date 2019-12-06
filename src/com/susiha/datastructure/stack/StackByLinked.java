package com.susiha.datastructure.stack;

import com.susiha.datastructure.base.LinkedList;

public class StackByLinked<T> implements Stack<T> {
    private LinkedList<T> list;

    public StackByLinked(){
        list = new LinkedList();
    }
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T o) {
        list.addLast(o);
    }

    @Override
    public T pop() {
        return list.removeLast();
    }

    @Override
    public T peek() {
        return list.getLast();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i<list.getSize();i++){
            sb.append(list.get(i));
            if(i!=list.getSize()-1){
                sb.append(" , ");
            }
        }
        sb.append(" 栈顶]");
        return sb.toString();
    }
}
