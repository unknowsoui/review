package nowcoder;

public class duplicate {
    public static boolean duplicate(int numbers[],int length,int [] duplication) {
        StringBuilder str = new StringBuilder();
        for(int i = 0;i < numbers.length;i++){
            str.append(numbers[i]);
        }
        char note;
        for(int i = 0;i < length;i++){
            note = str.charAt(i);
            String sp = str.substring(i + 1,str.length());
            if(sp.indexOf(note) != -1){
                duplication[0] = Integer.parseInt(String.valueOf(note));
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] i = {2,4,3,1,4};
        int[] i1 = new int[1];
        System.out.println(duplicate(i,5,i1));
    }
}
