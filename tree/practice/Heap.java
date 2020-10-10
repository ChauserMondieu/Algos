package tree.practice;

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
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        Heap tree = new Heap(5);
        System.out.println(Arrays.toString(tree.getArr()));
        int[] arr = tree.getCopy(tree.getArr());
        tree.heap(arr);
        System.out.println(Arrays.toString(arr));
    }

    public int[] getCopy(int[] arr){
        return arr.clone();
    }

    public void heap(int[] arr) {
        int temp;
        for (int i = this.size / 2 - 1; i >= 0; i--) {
            shiftDown(arr, i, this.size);
        }
        for (int i = this.size - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            shiftDown(arr, 0, i);
        }
    }

    public void shiftDown(int[] arr, int index, int length) {
        int temp = arr[index];
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                i++;
            }
            if (temp < arr[i]) {
                arr[index] = arr[i];
                index = i;
            }
        }
        arr[index] = temp;
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
}
