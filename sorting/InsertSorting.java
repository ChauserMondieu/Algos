package sorting;

import java.util.Arrays;
import java.util.Random;

public class InsertSorting {
    private int size;
    private int[] arr;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public InsertSorting(int size) {
        this.size = size;
        this.arr = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for(int i=0; i<this.size; i++){
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        InsertSorting insert = new InsertSorting(10);
        System.out.println(Arrays.toString(insert.getArr()));
        insert.insert();
        System.out.println(Arrays.toString(insert.getArr()));
    }

    public void insert(){
        for(int i=1; i<size; i++){
            int insertVal = arr[i];
            int index = i-1;
            while(index >= 0 && arr[index]>insertVal){
                arr[index+1] = arr[index];
                index --;
            }
            arr[index+1] = insertVal;
        }
    }
}
