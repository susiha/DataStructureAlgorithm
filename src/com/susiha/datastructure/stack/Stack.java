package com.susiha.datastructure.stack;

/**
 * 栈接口
 * 栈的两个基本特征
 * 1 只能通过栈顶访问元素
 * 2 符合后进先出的原则
 * @param <T>
 */
public interface Stack<T> {
    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入栈
     * @param t
     */
    void push(T t);

    /**
     * 出栈
     * @return
     */
    T pop();

    /**
     * 访问栈顶元素
     * @return
     */
    T peek();

    /**
     * 获取栈的大小
     * @return
     */
    int getSize();
}
