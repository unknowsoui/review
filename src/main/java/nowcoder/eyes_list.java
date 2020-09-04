package nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class eyes_list {
    private static class arrays{
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        public boolean isCompareTo(arrays arrays){
            if(a == arrays.a && b == arrays.b && c == arrays.c && d == arrays.d){
                return true;
            }else {
                return false;
            }
        }
    }

    private static ArrayList<arrays> Arrays = new ArrayList<>();

    private static int eyesListNum(int n, arrays arrays) {
        int[][] array = new int[n][n];
        int num = arrays.a + arrays.b + arrays.c + arrays.d;
        if(arrays.a != 0) {
            array[0][n - 1] = 1;
            arrays.a--;
        }else if(arrays.b != 0){
            array[0][n - 1] = 1;
            arrays.b--;
        }else if(arrays.c != 0){
            array[0][n - 1] = 1;
            arrays.c--;
        }else if(arrays.d != 0){
            array[0][n - 1] = 1;
            arrays.d--;
        }
        for(int i = 0;i < num - 1;i++){

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            arrays arrays = new arrays();
            int n = scanner.nextInt();
            scanner.
            arrays.a = scanner.nextInt();
            arrays.b = scanner.nextInt();
            arrays.c = scanner.nextInt();
            arrays.d = scanner.nextInt();
            System.out.println(eyesListNum(n,arrays));
        }
    }
}
