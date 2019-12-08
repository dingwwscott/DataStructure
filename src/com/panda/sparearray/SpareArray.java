package com.panda.sparearray;

public class SpareArray {
    public static void main(String[] args) {
        //定义二维数组 11*11
        // 1 代表黑子，2 代表篮子，0 代表没有棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        for (int[] row : chessArr1 ){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


        //遍历原始数组不为0的个数
        int sum = 0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j] != 0){
                    ++sum;
                }
            }
        }
        System.out.println("sum3 : "+sum);

    }
}
