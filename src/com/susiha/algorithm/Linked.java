package com.susiha.algorithm;

import com.susiha.Utils.BaseUtils;
import com.susiha.datastructure.base.LinkedList;

/**
 * 链表相关算法
 */
public class Linked {
    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0;i<10;i++){
            linkedList.addLast(i);
        }
        BaseUtils.println(linkedList.toString());

//        BaseUtils.println(LinkedReversal2(linkedList).toString());

        BaseUtils.println(printlnLinkedListByDummyHead(LinkedReversal3(linkedList)));
    }


    /**
     * 链表反转 通过辅助LinkedList 时间复杂度0(n)
     * @param linked
     * @return
     */
    public static  LinkedList LinkedReversal(LinkedList linked){
        //链表没有数据或者只有一个数据 反转没有意义
        if(linked ==null||linked.isEmpty()||linked.getSize()==1){
            return linked;
        }

        LinkedList reversalLinked = new LinkedList();

        for(int i=0;i<linked.getSize();i++){
            reversalLinked.addFirst(linked.get(i));
        }
        return reversalLinked;
    }


    /**
     * 反转链表2 不借助任何数据结构 通过删除一个数据 然后添加到第一个位置上 时间复杂度是0(n*n)
     * @param linked
     * @return
     */
    public static  LinkedList LinkedReversal2(LinkedList linked){
        //链表没有数据或者只有一个数据 反转没有意义
        if(linked ==null||linked.isEmpty()||linked.getSize()==1){
            return linked;
        }

        for(int i = 1;i<linked.getSize();i++){
            linked.addFirst(linked.remove(i));
        }

        return linked;
    }


    /**
     * 反转链表3 不借助任务辅助数据结构 实现时间复杂度为0(n)
     * @param linked
     * @return
     */
    public static  LinkedList.Node LinkedReversal3(LinkedList linked){

        //链表没有数据或者只有一个数据 反转没有意义
        if(linked ==null||linked.isEmpty()){
            throw  new IllegalArgumentException("参数必须是正常的链表");
        }
        if(linked.getSize()==1){
            return linked.getDummyHead();
        }

        LinkedList.Node dummyHead = linked.getDummyHead();
        LinkedList.Node preNode = dummyHead.next;
        LinkedList.Node curNode = preNode.next;
        LinkedList.Node nextNode = curNode.next;
        preNode.next =null;
        while(curNode!=null){
            curNode.next = preNode;
            if(nextNode == null){
                dummyHead.next =curNode;
                break;
            }else{
                preNode =curNode;
                curNode = nextNode;
                nextNode = nextNode.next;
            }
        }
        return dummyHead;
    }

    public static String printlnLinkedListByDummyHead(LinkedList.Node dummyHead){

        StringBuilder sb = new StringBuilder();
        LinkedList.Node cur = dummyHead.next;
        while(cur!=null){
            sb.append(cur.toString()).append(" -->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }


}
