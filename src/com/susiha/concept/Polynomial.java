package com.susiha.concept;

import com.susiha.Utils.BaseUtils;

/**
 * 多项式的基本概念和操作
 * 在这里幂使用^来表示,比如x的平方 用x^2
 *
 * 多项式如 f(x) =5x^5+3x^2+1 表示5乘以x的5次方+3乘以x的平方+1
 * 多项式存储使用一维数组来保存 有两种保存方式
 * 1 按照指数降序存储 n+2个存储空间 n 是最大指数 其中第一位存储最大最大指数 后面依次存储各个次幂的
 * 常数项 比如上面的多项式存储 为5，5，0，0，3，0，1 其中第一个0 表示4次幂的常数项为0 依次类推
 * 这种存储的劣势是如果x的100次方+1 的话就会很浪费空间,但是在运算的时候有优势
 *
 * 2 只存储非0项 如果有m个非想就占用2m+1个存储空间 如上述多项式存储为5，5，5，3，2，1，0 优势是节省
 * 存储空间，但是在运算的时候不方便
 *
 * 这里的运算使用的是第一种存储方法是
 *
 * 多项式也可以使用链表表示 每一个Node 有两个数字 第一个数字是多项式的系数，第是多项式的幂 这样就比较节省空间和计算方便
 * 比如说f(x) = 5x^6+4x^3-2x+1
 * 可以用链表表示为 -->|5|6|-->|4|3|-->|-2|1|-->|1|0|
 *
 */
public class Polynomial {
    public static  final String POWER="x^";
    public static  final String X="x";

    public static int[] addPolynomoal(int arrA[],int arrB[]){
        checkPolynomial(arrA);
        checkPolynomial(arrB);


        //取出指数最大值
        int maxValue = Math.max(arrA[0],arrB[0]);
        int res[] =new int[maxValue+2];
        res[0] =maxValue;
        //获取两个长度的差值
        int diff = Math.abs(arrA.length-arrB.length);
        for(int i = res.length-1;i>=0;i--){
            //A长B短
            if(arrA.length>=arrB.length){
                if(i<=diff){
                    res[i] =arrA[i];
                }else{
                    res[i] = arrA[i]+arrB[i-diff];
                }

            }else{ //A短B长
                if(i<=diff){
                    res[i] =arrB[i];
                }else{
                    res[i] = arrA[i-diff]+arrB[i];
                }
            }


        }
        return res;
    }

    public static void printPolynomial(int arr[]){

        checkPolynomial(arr);
        StringBuilder sb = new StringBuilder();
        sb.append("f(x) = ");
        //最大指数
        int high = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i]!=0){

                if(arr[i]==1&&high !=0){
                    if(high ==1){
                        sb.append(X);

                    }else{
                        sb.append(POWER).append(high);
                    }

                }else{
                    if(high ==0){
                        sb.append(arr[i]);
                    }else if(high==1){
                        sb.append(arr[i]).append(X);
                    }else{
                        sb.append(arr[i]).append(POWER).append(high);
                    }
                }

                if(i!=arr.length-1){
                    sb.append("+");
                }
            }
            high--;
        }

        BaseUtils.println(sb.toString());
    }


    /**
     * 检测多项式是否存储格式正确
     * @param arr
     */
    private static void checkPolynomial(int arr[]){
        if(arr ==null||arr.length<2){
            throw  new IllegalArgumentException(arr+" polynomial is empty");
        }

        if(arr.length!=arr[0]+2){
            //存储空间不对不是多项式
            throw  new IllegalArgumentException(arr+" not a polynomial");
        }

        //最大次幂的项不能为0
        if(arr[1] ==0){
            throw  new IllegalArgumentException(arr+" max power can not be 0");
        }

    }

}
