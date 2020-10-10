package tree;

import java.util.Random;

public class HeapPractice {
    private int[] arr;
    private int size;

    public HeapPractice(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt();
        }
    }

    public int[] getCopy(int[] arr) {
        return arr.clone();
    }

    public void heap(int[] arr, int index) {
        int temp;
        for (int i = this.size / 2 - 1; i > 0; i--) {
            shiftDown(arr, i, this.size);
        }
        for (int i = this.size - 1; i > 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            shiftDown(arr, 0, i);
        }
    }

    private void shiftDown(int[] arr, int index, int size) {
        int temp = arr[index];
        for (int i = 2 * index + 1; i < size; i = i * 2 + 1) {
            if (i + 1 < size && arr[i + 1] > arr[i]) {
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
