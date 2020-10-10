package sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private int size;
    private int[] arr;

    public QuickSort(int size) {
        this.size = size;
        arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        QuickSort quick = new QuickSort(20);
        System.out.println(Arrays.toString(quick.getArr()));
        System.out.println(Arrays.toString(quick.quick(quick.getArr(),0, 19)));
    }

    public QuickSort() {
        this(10);
    }

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

    public int[] quick(int[] thisArr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = thisArr[r];
        while (l < r) {
            while (l < r && thisArr[l] <= pivot) {
                l++;
            }
            thisArr[r] = thisArr[l];
            while (l < r && thisArr[r] >= pivot) {
                r--;
            }
            thisArr[l] = thisArr[r];
        }
        thisArr[l] = pivot;
        if (left < r) {
            quick(thisArr, left, r-1);
        }
        if (right > l) {
            quick(thisArr,l+1, right);
        }
        return thisArr;
    }
}
