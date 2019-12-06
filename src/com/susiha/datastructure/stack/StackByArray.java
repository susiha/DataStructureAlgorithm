package com.susiha.datastructure.stack;

import com.susiha.datastructure.base.Array;

/**
 * 通过数组列表构建的栈
 * @param <T>
 */
public class StackByArray<T> implements Stack<T> {

    private Array<T> array;


    public StackByArray(){
        array = new Array<>();
    }

    public StackByArray(int capacity){
        array = new Array<>(capacity);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public T pop() {
        return array.removeLast();
    }

    @Override
    public void push(T t) {
        array.addLast(t);
    }

    @Override
    public T peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i<array.getSize();i++){
            sb.append(array.get(i));
            if(i!=array.getSize()-1){
                sb.append(" , ");
            }
        }
        sb.append(" 栈顶]");
        return sb.toString();
    }
}
