package sorting;

import java.util.Arrays;
import java.util.Random;

public class AdvancedSorting {
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

    public AdvancedSorting(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(10000);
        }
    }

    public AdvancedSorting() {
        this(20);
    }

    public int[] getCopy(int[] arr) {
        return arr.clone();
    }

    public static void main(String[] args) {
        AdvancedSorting sorting = new AdvancedSorting(20);
        int[] arr2 = sorting.getCopy(sorting.getArr());
        int[] arr3 = sorting.getCopy(sorting.getArr());
        int[] temp = new int[sorting.getSize()];
        System.out.println(Arrays.toString(sorting.getArr()));
        sorting.mergeSort(arr2, 0, sorting.getSize() - 1, temp);
        System.out.println(Arrays.toString(arr2));
        sorting.quick(arr3, 0, sorting.getSize() - 1);
        System.out.println(Arrays.toString(arr3));
    }

    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int t = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[t] = arr[l];
                l++;
            } else {
                temp[t] = arr[r];
                r++;
            }
            t++;
        }
        while (l <= mid) {
            temp[t] = arr[l];
            l++;
            t++;
        }
        while (r <= right) {
            temp[t] = arr[r];
            r++;
            t++;
        }
        t = 0;
        int lcursor = left;
        while (lcursor <= right) {
            arr[lcursor] = temp[t];
            t++;
            lcursor++;
        }

    }

    public void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[left];
        while (l < r) {
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            arr[l] = arr[r];
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        if (r < right) {
            quick(arr, r + 1, right);
        }
        if (l > left) {
            quick(arr, left, l - 1);
        }
    }
}
