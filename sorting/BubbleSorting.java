package sorting;

import java.util.Arrays;
import java.util.Random;

public class BubbleSorting {
    private int[] arr;
    private int size;

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BubbleSorting(int size) {
        this.size = size;
        this.arr = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i=0; i<size; i++) {
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        BubbleSorting bubble = new BubbleSorting(8);
        System.out.println(Arrays.toString(bubble.getArr()));
        bubble.bubble();
        System.out.println(Arrays.toString(bubble.getArr()));
    }

    public void bubble(){
        boolean flag = true;
        for(int i=0; i<size; i++){
            for(int j=0; j<size-1-i; j++){
                if(arr[j]>arr[j+1]){
                    flag = false;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(flag){
                return;
            }
        }
    }
}
