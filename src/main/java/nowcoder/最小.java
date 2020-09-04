package nowcoder;

import java.util.*;

public class 最小 {
    public static int changeNum(String str,String target){
        int result = 0;
        StringBuilder strSB = new StringBuilder(str);
        StringBuilder tarSB = new StringBuilder(target);
        for(int i = 0;i < strSB.length();i++){
            int size = tarSB.indexOf(String.valueOf(strSB.charAt(i)));
            if(size != -1){
                strSB = strSB.deleteCharAt(i);
                tarSB = tarSB.deleteCharAt(size);
                i--;
            }
        }
        int strlength = strSB.length();
        int targetlength = tarSB.length();
        if(targetlength <= strlength){
            result = result + strlength;
        }else{
            result = result + targetlength;
        }
        return result;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String ord = scanner.nextLine();
            String target = scanner.nextLine();
            System.out.println(changeNum(ord,target));
        }
    }
}
