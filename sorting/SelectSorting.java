package sorting;

import java.util.Arrays;
import java.util.Random;

public class SelectSorting {
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

    public SelectSorting(int size) {
        this.size = size;
        arr = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for(int i=1; i<this.size; i++){
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        SelectSorting select = new SelectSorting(10);
        System.out.println(Arrays.toString(select.getArr()));
        select.select();
        System.out.println(Arrays.toString(select.getArr()));
    }

    public void select(){
        for(int i=0; i<this.size; i++){
            int min = arr[i];
            int index = i;
            for(int j=i+1; j<this.size; j++){
                if(arr[j]<min){
                    index = j;
                    min = arr[j];
                }
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
