package nowcoder;

public class MInNumerArray {
    public static int minNumberInRotateArray(int [] array) {
        if(array.length == 0){
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int mid = 0;
        int min = 0;
        while(left <= right){
            mid = (left + right)/2;
            min = array[mid];
            if(array[mid] >= array[right]){
                left = mid + 1;
            }else if(array[mid] <= array[right]){
                right = mid - 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] i = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(i));
    }
}
