package com.susiha.concept;

import com.susiha.Utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 矩阵相关概念和基本操作
 */
public class Matrix {
    //分割符
    private static final String SPLIT = "##";


    /**
     * 矩阵相加，如果两个矩阵相加 前提条件必须是两个矩阵的行数和列数相等
     * 相加后的矩阵的行数和列数也相同
     *
     * @param arrA
     * @param arrB
     * @return
     */
    public static int[][] matrixAdd(int arrA[][],int arrB[][]){

        int rowA = getMatrixRow(arrA);//A的X维度
        int rowB = getMatrixRow(arrB);//B的X维度
        if(rowA ==0 ||rowA!=rowB){
            throw  new IllegalArgumentException("矩阵的行维度必须大于0且两个矩阵的维度必须相同");
        }
        int colA = arrA[0].length;//A的Y维度
        int colB = arrB[0].length;//B的Y维度
        if(colB ==0 ||colB!=colA){
            throw  new IllegalArgumentException("矩阵的列维度必须大于0且两个矩阵的维度必须相同");
        }
        int res[][] = new int[rowA][colA];
        for(int row = 0;row <rowA;row++){
            for(int col = 0;col<colA;col++){
                res[row][col] =arrA[row][col]+arrB[row][col];
            }
        }
        return res;
    }


    /**
     * 矩阵相乘必须满足 A(m*n) * B(n*p) = C(m*p)的条件
     * 也就是说乘号之前的列数必须与乘号之后的行数是一样的
     * @param arrA
     * @param arrB
     * @return
     */

    public static int[][] matrixMultip(int arrA[][],int arrB[][]){


        int rowA = getMatrixRow(arrA);
        int colA = getMatrixCol(arrA);
        int rowB = getMatrixRow(arrB);
        int colB = getMatrixCol(arrB);

        if(rowA ==0){
            throw new IllegalArgumentException("arrA is empty");
        }
        if(colA!=rowB){
            throw new IllegalArgumentException("不满足矩阵相乘的条件");
        }
        int res[][] = new int[rowA][colB];
        for(int i = 0;i<rowA;i++){
            for(int j = 0;j<colB;j++){
                int temp  =0;
                for(int m = 0;m<colA;m++){
                    temp +=arrA[i][m]*arrB[m][j];
                }
                res[i][j] =temp;
            }
        }

        return res;
    }


    /**
     * 矩阵转置
     * @param arr
     * @return
     */
    public static int[][] matrixTransposition(int arr[][]){
        int row = getMatrixRow(arr);
        int col = getMatrixCol(arr);
        int res[][] = new int[col][row];
        for(int i  =0;i<row;i++){
            for(int j = 0;j<col;j++){
                res[j][i] = arr[i][j];
            }
        }
        return res;
    }


    /**
     * 稀疏矩阵 矩阵中大部分都是0 为了节省存储空进 把为0的部分不存储，只存储有值的，这样组成了一个新的
     * 矩阵 就是稀疏矩阵 这个矩阵有三列
     * 第一行 00 表示有多少行 01 表示有多少列 02 表示总共有多少个有值的
     * 后边依 x0 表示行坐标 x1 表示列坐标 x2 表示具体的值
     *
     * @param arr
     * @return
     */
    public static int[][] matrixSparse(int arr[][]){

        int row = getMatrixRow(arr);
        int col = getMatrixCol(arr);
        //借助List 保存对应有值的元素信息 保存格式i##j##value
        List<String> valueTemp = new ArrayList<>();
        for(int i  =0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(arr[i][j]!=0){
                    valueTemp.add(i+SPLIT+j+SPLIT+arr[i][j]);
                }
            }
        }

        //构造稀疏矩阵
        int res[][] = new int[valueTemp.size()+1][3];
        //首先为稀疏矩阵的基本信息赋值
        res[0][0] = row;
        res[0][1] = col;
        res[0][2] = valueTemp.size();
        //为稀疏矩阵赋值
        for(int i = 1;i<valueTemp.size()+1;i++){
            String[] resTemp =valueTemp.get(i-1).split(SPLIT);
            res[i][0] = Integer.parseInt(resTemp[0]);
            res[i][1] = Integer.parseInt(resTemp[1]);
            res[i][2] = Integer.parseInt(resTemp[2]);
        }
        return res;
    }

    /**
     * 解析稀疏矩阵
     * @param sparseArr
     * @return
     */
    public static int[][] matrixParseSparse(int sparseArr[][]){

        int row = getMatrixRow(sparseArr);
        int col = getMatrixCol(sparseArr);

        if(col!=3||row==0){
            throw new IllegalArgumentException("not a sparse matrix");
        }

        int res[][] = new int[sparseArr[0][0]][sparseArr[0][1]];


        //初始化都为0
        for(int i=0;i<sparseArr[0][0];i++){
            for(int j =0;j<sparseArr[0][1];j++){
                    res[i][j] =0;
            }
        }

        //对有值的地方进行重新赋值
        for(int m = 1;m<row;m++){
            res[sparseArr[m][0]][sparseArr[m][1]] =sparseArr[m][2];
        }


        return res;
    }











    /**
     * 打印矩阵
     * @param arr
     */
    public static void printlnMatrix(int arr[][]){
         int col = getMatrixCol(arr);
         int raw = getMatrixRow(arr);
        if(raw ==0){
            BaseUtils.println("没有数据");
            return;
        }
        for(int i = 0;i<arr.length;i++){

            for(int j = 0;j<col;j++){
                BaseUtils.print(arr[i][j]+"  ");
            }
            BaseUtils.print("\n");
        }
    }

    /**
     * 检查是否是矩阵
     * @param arr
     * @return
     */
    private static String checkMatrixAndGetRowCol(int arr[][]){
        if(arr ==null||arr.length ==0){
            throw new IllegalArgumentException("matrix must be not empty");
        }
        int row = arr.length;
        int col = arr[0].length;
        for (int i = 0;i<row;i++){
            if(arr[i].length!=col){
                throw new IllegalArgumentException("not matrix just normal[][]");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(row).append(SPLIT).append(col);
        return sb.toString();

    }

    private static  int  getMatrixRow(int arr[][]){
        String res[] = checkMatrixAndGetRowCol(arr).split(SPLIT);
        return Integer.parseInt(res[0]);
    }

    private static int  getMatrixCol(int arr[][]){
        String res[] = checkMatrixAndGetRowCol(arr).split(SPLIT);
        return Integer.parseInt(res[1]);
    }



}
