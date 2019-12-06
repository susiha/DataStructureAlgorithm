package com.susiha.datastructure.base;

/**
 * 自定义线性表
 * @param <T>
 */
public class Array<T> {
    public static final String GET = "get element";
    public static final String ADD = "add element";
    public static final String SET = "set element";
    public static final String REMOVE = "remove element";
    private T[] data;
    private int size = 0;

    public Array(){
        this(10);
    }

    public Array(int capacity){
        data = (T[]) new Object[capacity];
        size =0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 是否包含指定的元素t
     * @param t
     * @return
     */
    public boolean contains(T t){
        for(int i =0;i<size;i++){
            if(t.equals(data[i])){
                return true;
            }
        }
        return false;
    }


    /**
     * 找到指定元素t所在的索引 如果没有 就返回-1
     * @param t
     * @return
     */
    public int find(T t){
        for(int i =0;i<size;i++){
            if(t.equals(data[i])){
                return i;
            }
        }
        return -1;
    }



    public T get(int index){
        checkIndex(index,GET);
        return data[index];
    }

    public T getLast(){
        return get(size-1);
    }

    public T getFirst(){
        return get(0);
    }


    /**
     * 向指定索引位置添加元素
     * @param index
     * @param t
     */
    public void add(int index, T t){
        if(size == data.length){
            reSize(2*data.length);
        }
        checkIndex(index,ADD);
        //首先把所插入索引后面的元素集体向后移动一位
        for(int i = size-1;i>=index;i--) {
            data[i+1] = data[i];
        }

        data[index] =t;
        size++;
    }

    public void addFirst(T t){
        add(0,t);
    }

    public void addLast(T t){
        add(size,t);
    }


    public void set(int index,T t){
        checkIndex(index,SET);
        data[index] = t;
    }

    /**
     * 删除指定的索引的元素，并返回该被删除的元素
     * @param index
     * @return
     */
    public T remove(int index){
        checkIndex(index,REMOVE);
        T result = data[index];

        for(int i = index+1;i<size;i++){
            data[i-1] = data[i];
        }

        size--;
        //如果数组上容量是长度的四倍以上且data.length不是1
        if(size<= data.length/4&&data.length/2!=0){
            reSize(data.length/2);
        }
        return result;
    }

    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size-1);
    }

    /**
     * 扩容
     * @param newCapacity
     */
    private void reSize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];
        for(int i = 0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }


    /**
     * 检查索引
     * @param index
     * @param commond
     */
    private void checkIndex(int index,String commond){
        if(index<0||index>size){
            throw new IllegalArgumentException(commond+"is failed,index illegal");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array:size = %d,capacity = %d \n",size,data.length));
        for(int i =0;i < size;i++){
            sb.append(data[i]);
            if(i!=size-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
