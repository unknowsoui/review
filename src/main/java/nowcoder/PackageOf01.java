package nowcoder;

import java.util.*;

public class PackageOf01{
    public static int packageOf01(int n,int w,int[] weight,int[] value){
        int[][] table = new int[n + 1][w + 1];// 创建一个二维数组，横列是物品的价值，竖列是物品的重量
        // 蓝色代码注释开始
        for (int i = 1; i <= n; i++) { //物品
            for (int j = 1; j <= w; j++) {  //背包大小
                if (weight[i] > j) {
                    //当前物品i的重量比背包容量j大，装不下，肯定就是不装
                    table[i][j] = table[i - 1][j];
                } else { //装得下，Max{装物品i， 不装物品i}
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        // 蓝色代码注释结束
        return table[n][w];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int number = Integer.parseInt(scanner.nextLine());
            int weight = Integer.parseInt(scanner.nextLine());
            int[] weights = new int[number + 1];
            int[] value = new int[number + 1];
            for(int i = 1;i < weights.length;i++){
                weights[i] = scanner.nextInt();
            }
            for(int i = 1;i < value.length;i++){
                value[i] = scanner.nextInt();
            }
            int result = packageOf01(number,weight,weights,value);
            System.out.println(result);
        }
    }
}