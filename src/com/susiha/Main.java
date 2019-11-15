package com.susiha;

import com.susiha.Utils.BaseUtils;
import com.susiha.concept.Matrix;
import com.susiha.concept.Polynomial;
import com.susiha.datastructure.Array;
public class Main {
    public static void main(String args[]){
//        testArray();
//        testShowMatrix();
//          testAddMatrix();
//        testMutilMatrix();
//        testTranspositionMatrix();
//        testSparseMatrix();
//        testPrintlnPolynomial();
        testAddPolynomail();
    }

    private static void testArray(){

        Array<Integer> array = new Array();
        int size = (int) (Math.random()*100);
        System.out.println(String.format("随机Array元素的个数是%d",size));
        for(int i = 0;i< size ;i++){
            int element = (int) (Math.random()*100);
            System.out.println(String.format("第%d个随机数是%d",i,element));
            array.addFirst(element);
        }
        System.out.println(array.toString());
    }

    private static void testShowMatrix(){
        int arr[][] = {{1,2,3},{2,3,7},{8,0,9}};
        Matrix.printlnMatrix(arr);
    }

    private static void testAddMatrix(){

        int arrA[][] ={{1,1,1},{2,2,2},{3,3,3}};
        int arrB[][] ={{2,2,2},{3,3,3},{4,4,4}};
        BaseUtils.println("arrA --");
        Matrix.printlnMatrix(arrA);
        BaseUtils.println("------------");
        BaseUtils.println("arrB --");
        Matrix.printlnMatrix(arrB);
        BaseUtils.println("------------");
        BaseUtils.println("Add Result --");
        Matrix.printlnMatrix(Matrix.matrixAdd(arrA,arrB));
        BaseUtils.println("------------");
    }


    private static void testMutilMatrix(){

        int arrA[][] ={{1,1,1},{1,1,1}};
        int arrB[][] ={{1,1},{1,1},{1,1}};
        BaseUtils.println("arrA --");
        Matrix.printlnMatrix(arrA);
        BaseUtils.println("------------");
        BaseUtils.println("arrB --");
        Matrix.printlnMatrix(arrB);
        BaseUtils.println("------------");
        BaseUtils.println("mutil Result --");
        Matrix.printlnMatrix(Matrix.matrixMultip(arrA,arrB));
        BaseUtils.println("------------");
    }

    private static void testTranspositionMatrix(){

        int arrA[][] ={{1,2,3},{4,5,6},{7,8,9}};
        BaseUtils.println("arrA --");
        Matrix.printlnMatrix(arrA);
        BaseUtils.println("------------");
        BaseUtils.println("transposition Result --");
        Matrix.printlnMatrix(Matrix.matrixTransposition(arrA)); }


    private static void testSparseMatrix(){

        int arrA[][] ={{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        BaseUtils.println("arrA --");
        Matrix.printlnMatrix(arrA);
        BaseUtils.println("------------");
        BaseUtils.println("Sparse Result --");
        int sparseArr[][] =Matrix.matrixSparse(arrA);
        Matrix.printlnMatrix(sparseArr);

        BaseUtils.println("parse Result --");
        Matrix.printlnMatrix(Matrix.matrixParseSparse(sparseArr));

    }


    private static void testPrintlnPolynomial(){
        int a[] ={5,5,0,0,3,0,1};

        Polynomial.printPolynomial(a);
    }

    private static void testAddPolynomail(){
        int arrA[] = {4,3,7,0,6,2};
        int arrB[] = {4,1,5,2,0,9};

        BaseUtils.println("arrA -----");
        Polynomial.printPolynomial(arrA);
        BaseUtils.println("arrB -----");
        Polynomial.printPolynomial(arrB);
        BaseUtils.println("Result -----");
        Polynomial.printPolynomial(Polynomial.addPolynomoal(arrA,arrB));

    }








}
