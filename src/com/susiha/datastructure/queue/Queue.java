package com.susiha.datastructure.queue;

/**
 * 队列 先进先出
 */
public interface Queue<T> {
    //入队
     void enQueue(T t);
     //出队
     T deQueue();
     //获取队首
     T getFront();
     //获取队尾
     T getTail();
     //是否为空
     boolean isEmpty();
     //获取大小
     int getSize();
}
