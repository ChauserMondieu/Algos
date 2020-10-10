package tree;

import java.util.Arrays;
import java.util.Random;

public class Heap {
    private int[] arr;
    private int size;

    public Heap(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(1000);
        }
    }

    public Heap() {
        this(20);
    }

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

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        System.out.println(Arrays.toString(heap.getArr()));
        int[] arr = heap.getCopy(heap.getArr());
        heap.heap(arr,0);
        System.out.println(Arrays.toString((arr)));
    }

    public int[] getCopy(int[] arr){
        return arr.clone();
    }

    public void heap(int[] arr, int index) {
        int temp;
        for(int i=this.size/2-1;i>=0;i--){
            shiftDown(arr,i, this.size);
        }
        for(int i=this.size-1;i>0;i--){
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            shiftDown(arr,0, i);
        }
    }

    private void shiftDown(int[] arr, int index, int size) {
        int temp = arr[index];
        for (int j = index * 2 + 1; j < size; j = j * 2 + 1) {
            if (j + 1 < size && arr[j + 1] > arr[j]) {
                j++;
            }
            if (temp < arr[j]) {
                arr[index] = arr[j];
                index = j;
            } else {
                break;
            }
        }
        arr[index] = temp;

    }
}

class testFor{
    public static void main(String[] args) {
        for(int i=0; i<5;i++){
            System.out.println(i);
        }
    }
}
