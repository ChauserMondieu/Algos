package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightQueens {
    private static int count;
    private static int compare;
    private List<int[]>  store = new ArrayList();
    private int max;
    private int[] arr;

    public EightQueens(int max) {
        this.max = max;
        arr = new int[max];
    }

    public static void main(String[] args) {
        EightQueens queens = new EightQueens(8);
        queens.place(0);
        queens.print();
    }

    public boolean place(int row){
        if(row == max){
            store.add(arr.clone());
            count ++;
            return true;
        }else{
            for(int i=0; i<max; i++){
                arr[row] = i;
                if(compare(row)){
                    place(row+1);
                }
            }
        }
        return true;
    }

    private void print(){
        for (int[] ints : store) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.printf("compared %s times, and total result number is %s", compare, count);
    }

    private boolean compare(int row){
        for(int i=0; i<row; i++){
            compare ++;
            if(arr[i] == arr[row] || Math.abs(arr[i]-arr[row]) == Math.abs(i-row)){
                return false;
            }
        }
        return true;
    }

}
