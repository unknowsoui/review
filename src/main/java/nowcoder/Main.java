package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strings = sc.nextLine().split(",");
        Arrays.sort(strings, (s1, s2) -> (s1 + s2).compareTo((s2 + s1)));
        for (String s : strings) {
            System.out.print(s);
        }
    }
}
