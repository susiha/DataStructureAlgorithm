package com.susiha.datastructure.base;

/**
 * 自定义链式列表
 * @param <T>
 */
public class LinkedList<T> {

    private int size;
    private Node dummyHead;//虚拟头结点

    public LinkedList(){
        dummyHead = new Node();
        size =0;
    }

    /**
     * 获取虚拟头结点
     * @return
     */
    public Node getDummyHead(){
        return dummyHead;
    }


    public int getSize(){
        return size;
    }

    public boolean  isEmpty(){
        return size == 0;
    }

    /**
     * 在指定索引位置上添加指定的元素
     * @param index
     * @param t
     */
    public void add(int index,T t){

        // 检测索引是否超过范围
        if(index<0||index>size){
            throw new IllegalArgumentException("add error ,index illegal");
        }
        //从虚拟头结点开始
        Node pre = dummyHead;
        //遍历到指定索引的前一个位置
        for(int i =0;i<index;i++){
            pre = pre.next;
        }
        //创建插入元素的Node 把后一个位置连接到添加的索引Node后面
        Node node = new Node(t,pre.next);
        //把新创建的元素的节点连接到前一个元素的后面
        pre.next = node;
        //长度+1
        size++;
    }


    //从头结点开始插入
    public void addFirst(T t){
        add(0,t);
    }

    public void addLast(T t){
        add(size,t);
    }


    /**
     * 获取指定索引的节点
     * @param index
     * @return
     */
    public Node getNode(int index){

        if(index<0||index>=size){
            throw new IllegalArgumentException("get error ,index illegal");
        }

        //从头结点开始遍历 一直遍历到指定索引的前一个位置
        Node pre = dummyHead;
        for(int i = 0; i< index;i++){
            pre = pre.next;
        }
        return pre.next;
    }

    public T get(int index){
        return getNode(index).t;
    }




    public T getFirst(){
        return get(0);
    }

    public T getLast(){
        return get(size-1);
    }

    /**
     * 修改指定索引值
     * @param index
     * @param t
     */
    public void set(int index,T t){
        if(index<0||index>=size){
            throw new IllegalArgumentException("set error ,index illegal");
        }
        Node node = getNode(index);
        node.t = t;
    }


    /**
     * 判断链表中是否包含指定的元素
     * @param t
     * @return
     */
    public boolean contains(T t){
        Node pre = dummyHead;
        while (pre.next!=null){
            if(pre.next.t.equals(t)){
                return true;
            }
            pre = pre.next;
        }
        return false;
    }

    /**
     * 删除指定索引的节点 并返回该节点的值
     * @param index
     * @return
     */
    public T remove(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("remove error ,index illegal");
        }

        //遍历到需要删除节点的前一个节点
        Node pre = dummyHead;
        for(int i =0;i<index;i++){
            pre = pre.next;
        }

        //找到需要删除的节点
        Node cur = pre.next;
        //把需要删除的节点的下一个节点连接到上一个节点上
        pre.next = cur.next;
        cur.next =null;
        size--;
        return cur.t;

    }

    /**
     * 删除指定的Node 并把这个Node 返回
     * @param removeNode
     * @return
     */
    public Node remove(Node removeNode){

        if(removeNode ==null ||!contains(removeNode.t)){
            throw new IllegalArgumentException("remove error ,removeNode do not exist");
        }

        //遍历到需要删除节点的前一个节点
        Node pre = dummyHead;

        while(pre.next!=null&&!pre.next.equals(removeNode)){
            pre = pre.next;
        }
        //找到需要删除的节点
        Node cur = pre.next;
        //把需要删除的节点的下一个节点连接到上一个节点上
        pre.next = cur.next;
        cur.next =null;
        size--;
        return removeNode;

    }



    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur!=null){
            sb.append(cur.toString()).append(" -->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public class Node{
        public T t;
        public Node next;
        public Node(T t,Node next){
            this.t  = t;
            this.next = next;
        }

        public Node(T t){
            this(t,null);
        }

        public Node(){
            this(null);
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }
}
