package com.datastructure.sparearray;

public class SpareArray {
    public static void main(String[] args) {
        //定义二维数组 11*11
        // 1 代表黑色棋子，2 代表篮色棋子，0 代表没有棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][4] = 1;
        //输出原始二维数组
        System.out.println("原始二维数组为：");
        for (int[] row : chessArr1 ){
            for (int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组的思想
        //  1.遍历原始数组不为0的个数
        int sum = 0;
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[i].length;j++){
                if (chessArr1[i][j] != 0){
                    ++sum;
                }
            }
        }
        System.out.println("sum : "+sum);

        //   2.创建对应的稀疏数组
        int[][] spareArr = new int[sum+1][3];
        spareArr[0][0]=chessArr1.length;
        spareArr[0][1]=chessArr1[0].length;
        spareArr[0][2]=sum;
        int count = 0;
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[i].length;j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    spareArr[count][0]=i;
                    spareArr[count][1]=j;
                    spareArr[count][2]=chessArr1[i][j];
                }
            }
        }

        System.out.println("对应的稀疏数组为：");
        for (int i=0;i<spareArr.length;i++){
            for (int j=0;j<spareArr[i].length;j++){
                System.out.printf("%d\t",spareArr[i][j]);
            }
            System.out.println();
        }


        //把稀疏数组转化为二维数组
        int[][] chessArr = new int[spareArr[0][0]][spareArr[0][1]];
        for (int i=1;i<spareArr.length;i++){
            chessArr[spareArr[i][0]][spareArr[i][1]]=spareArr[i][2];
        }

        System.out.println("回复后的原始数组为：");

        for (int i=0;i<chessArr.length;i++){
            for (int j=0;j<chessArr[i].length;j++){
                System.out.printf("%d\t",chessArr[i][j]);
            }
            System.out.println();
        }
    }
}
