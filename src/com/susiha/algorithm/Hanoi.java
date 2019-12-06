package com.susiha.algorithm;

import com.susiha.Utils.BaseUtils;
/**
 * 汉诺塔
 * 把N各盘子从位置1移动到位置3 可以分解为
 * 1 把N-1个盘子移动到位置2
 * 2 把第N个盘子移动到位置3
 * 3 把N-1个盘子移动到位置3 这重复了1的操作
 * 4 直到最后一个盘子移动到位置3
 */
public class Hanoi {
    public void hanoi(int n){
        move(n,"position1","position2","position3");
    }


    private void move(int n,String position1,String position2,String position3){
        if(n == 1){
            BaseUtils.println(" move "+n +" from "+position1+ " to "+position3);
            return;
        }else{
            move(n-1,position1,position3,position2);
            BaseUtils.println(" move "+n +" from "+position1+ " to "+position3);
            move(n-1,position2,position1,position3);
        }
    }



}
