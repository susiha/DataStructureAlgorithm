package com.susiha.algorithm;

import com.susiha.Utils.BaseUtils;
import com.susiha.concept.Matrix;
import com.susiha.datastructure.base.LinkedList;
import com.susiha.datastructure.stack.StackByArray;

import java.util.Random;

/**
 * 迷宫问题
 */
public class Maze {

    private int[][] mazeArray =new int[12][12];


    public Maze(){
        //随机生成一个可以访问的路径
        LinkedList<Path> visitPath = randomPath();
        //随机生成一个二维数组,里面随机生成了一些路径但并不保证能够走通
        initMaze();
        generateMaze(visitPath);
        Matrix.printlnMatrix(mazeArray);
        BaseUtils.println("可访问路径 ："+visitPath.toString());
    }

    //生成真正的迷宫
    private void generateMaze(LinkedList<Path> visitPath){
        for(int i = 0;i<visitPath.getSize();i++){
            mazeArray[visitPath.get(i).x][visitPath.get(i).y] =0;
        }
    }


    //随机生成一个路径 从终点开始
    private  LinkedList<Path> randomPath(){

        int x = 10;
        int y = 10;
        int randomFactor = 4;

        //可访问的路径堆栈
        LinkedList<Path> visitPath = new LinkedList<>();

        while(true){
            if(x==1&&y==1){
                return visitPath;
            }
            /**
             * 随机出四个方向
             * 0 表示x-1
             * 1 表示y-1
             * 2 表示x+1
             * 3 表示y+1
             */
            int dirct =Math.abs(new Random().nextInt()%randomFactor);

            if(dirct == 0 && x > 1){
                //表示坐标随机往两边去了 需要往坐标1，1上靠一靠
                if(Math.abs(x-y)>=5){
                    randomFactor=2;
                }
                x--;
            }else if(dirct == 1 && y > 1){
                //表示坐标随机往两边去了 需要往坐标1，1上靠一靠
                if(Math.abs(x-y)>=5){
                    randomFactor=2;
                }
                y--;
            }else if(dirct == 2 && x < 10){
                if(x<4||y<4){
                    randomFactor=4;
                }
                x++;
            }else if(dirct == 3 && y < 10){
                if(x<4||y<4){
                    randomFactor=4;
                }
                y++;
            }
            if(!checkPath(x,y,visitPath)){

                Path path = new Path(x,y);
                visitPath.addLast(path);
            }
        }
    }


    /**
     * 初始化二维数组 生成伪随机路径
     */
    private void initMaze(){

        for(int i= 0;i<12;i++){
            for(int j=0;j<12;j++){

                if(i==1&&j==1 ||i==10&&j==10){
                    mazeArray[i][j] =0;
                }else if(i==0||j==0||i==11||j==11){
                    mazeArray[i][j] =1;
                }else{
                    mazeArray[i][j] =random();
                }
            }
        }
    }

    private int  random(){
        int result = new Random().nextInt();
        return Math.abs(result%2);
    }

    /**
     *
     *
     * 1  1  1  1  1  1  1  1  1  1  1  1
     * 1  0  1  0  1  0  1  1  0  0  1  1
     * 1  0  0  0  1  1  0  0  0  0  0  1
     * 1  1  0  0  0  1  0  0  0  1  0  1
     * 1  0  0  0  1  0  1  0  0  0  1  1
     * 1  0  0  0  1  1  1  0  1  0  1  1
     * 1  0  1  0  0  1  0  0  1  0  1  1
     * 1  1  1  0  0  0  1  0  1  1  0  1
     * 1  1  1  0  1  1  1  0  0  1  1  1
     * 1  0  0  0  1  0  1  1  1  0  1  1
     * 1  1  1  1  0  1  1  1  0  1  0  1
     * 1  1  1  1  1  1  1  1  1  1  1  1
     *
     *
     */


    public void maze(){
        //栈用来记录路径 走到死胡同时可以回退
        StackByArray<Path> stack = new StackByArray();
        //用来记录回退过的记录
        LinkedList<Path> backList = new LinkedList<>();
        //表示走过的记录
        LinkedList<Path> pathList = new LinkedList<>();
        //初始化入口坐标就是1,1
        int x =1;
        int y =1;
        while(true){
            //表示找到出口
            if(x==10&&y==10 ){
                BaseUtils.println("找到出口路径为"+stack.toString());
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(mazeArray[x][y] ==0 ){
                Path  path = new Path(x,y);
                //把第一个点放进栈中
                if(stack.isEmpty()){
                    stack.push(path);
                    pathList.addLast(path);
                    BaseUtils.println("起始点 坐标："+x+" , "+y);
                }else{
                    if(!(stack.peek().x==x&&stack.peek().y==y)){
                        stack.push(path);
                        pathList.addLast(path);
                        BaseUtils.println("走过当前点坐标: "+x+" , "+y);
                    }
                }



                if(mazeArray[x+1][y] ==0&&!checkPath(x+1,y,pathList) && !checkPath(x+1,y,backList)){ //东
                    BaseUtils.println("尝试南方向: "+(x+1)+" , "+y);
                    x++;
                }else if(mazeArray[x][y+1] ==0&&!checkPath(x,y+1,pathList)&& !checkPath(x,y+1,backList)){ //南
                    BaseUtils.println("尝试东方向: "+x+" , "+(y+1));
                    y++;
                }else if(mazeArray[x-1][y] ==0&&!checkPath(x-1,y,pathList)&& !checkPath(x-1,y,backList)){ //西
                    BaseUtils.println("尝试北方向: "+(x-1)+" , "+y);
                    x--;
                }else if(mazeArray[x][y-1] ==0&&!checkPath(x,y-1,pathList)&& !checkPath(x,y-1,backList)){ //北
                    BaseUtils.println("尝试西方向: "+x+" , "+(y-1));
                    y--;
                }else{
                    //回退到入口原点
                    if(x==1&&y==1){
                        BaseUtils.println("没有找到出口");
                        BaseUtils.println("尝试过的路径 :"+backList.toString());
                        return;
                    }
                    //后退
                    backList.addLast(stack.pop());
                    pathList.removeLast();
                    BaseUtils.println("A 需要后退 当前坐标 : "+x+" , "+y);
                    x= stack.peek().x;
                    y = stack.peek().y;
                    BaseUtils.println("A 回退到上一个坐标 : "+x+" , "+y);
                }
            }else{ //走不通
                //回退到入口原点
                if(x==1&&y==1){
                    BaseUtils.println("没有找到出口");
                    BaseUtils.println("尝试过的路径 :"+backList.toString());
                    return;
                }
                //后退
                backList.addLast(stack.pop());
                pathList.removeLast();
                BaseUtils.println("B需要后退当前坐标: "+x+" , "+y);
                x= stack.peek().x;
                y = stack.peek().y;
                BaseUtils.println("B后退到上一个坐标: "+x+" , "+y);
            }
        }
    }



    private boolean checkPath(int x,int y,LinkedList<Path> list){
        if(list.isEmpty()){
            return false;
        }

        for(int i= 0;i<list.getSize();i++){

            if(x == list.get(i).x&&y==list.get(i).y){
                return true;
            }
        }
        return false;
    }



}


class Path{
    public Path(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
