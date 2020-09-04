package nowcoder;

public class TwoArrayFind {
    public static boolean Find(int target, int [][] array) {
        if(array[0].length == 0){
            return false;
        }
        int i = array.length - 1;
        int j = 0;
        while(i != -1 && j != array.length){
            if(target > array[i][j]){
                j++;
            }else if(target < array[i][j]){
                i--;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] i = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int[][] j = {{}};
        System.out.println(Find(5,j));
    }
}
