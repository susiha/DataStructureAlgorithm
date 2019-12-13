package com.susiha.algorithm;

import com.susiha.Utils.BaseUtils;
import com.susiha.concept.Matrix;

/**
 * 八皇后问题
 */
public class EightQueen {


    private int[][] queen = new int[8][8];

    private int count = 0;
    public EightQueen(){
        init();
    }

    public void findQueen(){
        findQueen(0);
    }

    //初始化二维数组
    private void init(){
        for(int i =0;i<8;i++){
            for(int j = 0;j<8;j++){
                queen[i][j] =0;
            }
        }
    }



    private void findQueen(int row){
        if(row>7){
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }

            count++;
            BaseUtils.println("---------------分割线----------------");
            BaseUtils.println("count = "+count);
            BaseUtils.println("---------------分割线----------------");
            printlnMarix(queen);
            return;
        }

        for(int col =0;col<8;col++){
            if(check(row,col)){
                queen[row][col] =1;
                findQueen(row+1);
                queen[row][col] =0;
            }
        }


    }


    private boolean check(int row,int col){
        for(int i =0;i<8;i++){ //检查该行数据可不可以放数据
           if(queen[row][i]==1||queen[i][col]==1){
               return false;
           }
        }
        for(int i=row-1,j=col-1; i>=0 && j>=0; i--,j--){//检查左上对角线
           if(queen[i][j]==1){
               return false;
           }
        }
        for(int i=row-1,m=col+1; i>=0 && m<=7; i--,m++){//检查右上对角线
            if(queen[i][m]==1){
                return false;
            }
        }
        return true;

    }


    private void printlnMarix(int[][] arr){
        Matrix.printlnMatrix(arr);
    }



}
