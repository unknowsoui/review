package nowcoder;

import java.util.Scanner;

public class reserve_array_sum {
    public static int printAddSum(int n ,int m){
        int result = 0;
        int prev = 0;
        int mi = -1;
        for(int i = 1;i <= n;i++){
            if(prev == m){
                mi *= -1;
                prev = 0;
            }
            prev++;
            result += mi * i;
        }
        return result;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println( printAddSum(n,m));
    }
}
