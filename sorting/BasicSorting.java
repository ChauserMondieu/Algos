package sorting;

import java.util.Arrays;
import java.util.Random;

public class BasicSorting {
    private int size;
    private int[] arr;

    public BasicSorting(int size) {
        this.size = size;
        arr = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(100);
        }
    }

    public BasicSorting() {
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

    public static void main(String[] args) {
        BasicSorting sorting = new BasicSorting(20);
        System.out.println(Arrays.toString(sorting.getArr()));
        System.out.println(Arrays.toString(sorting.insertion()));
        System.out.println(Arrays.toString(sorting.switching()));
        System.out.println(Arrays.toString(sorting.shellInsertion()));
        System.out.println(Arrays.toString(sorting.shellSwitching()));
        int[] quickArr = sorting.getArr().clone();
        sorting.quick(quickArr, 0, 19);
        System.out.println(Arrays.toString(quickArr));
    }

    public int[] insertion() {
        int[] thisArr = this.arr.clone();
        int value;
        int index;
        for (int i = 1; i < this.size; i++) {
            value = thisArr[i];
            index = i - 1;
            while (index >= 0 && value < thisArr[index]) {
                thisArr[index + 1] = thisArr[index];
                index--;
            }
            thisArr[index + 1] = value;
        }
        return thisArr;
    }

    public int[] switching() {
        int[] thisArr = this.arr.clone();
        boolean flag = true;
        int temp;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size - 1 - i; j++) {
                if (thisArr[j] > thisArr[j + 1]) {
                    flag = false;
                    temp = thisArr[j];
                    thisArr[j] = thisArr[j + 1];
                    thisArr[j + 1] = temp;
                }
            }
            if (flag) {
                return thisArr;
            }
        }
        return thisArr;
    }

    public int[] shellSwitching() {
        int[] thisArr = this.arr.clone();
        int temp;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i += gap) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (thisArr[j] > thisArr[j + gap]) {
                        temp = thisArr[j];
                        thisArr[j] = thisArr[j + gap];
                        thisArr[j + gap] = temp;
                    }
                }
            }
        }
        return thisArr;
    }

    public int[] shellInsertion() {
        int[] thisArr = this.arr.clone();
        int value;
        int index;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i++) {
                index = i - gap;
                value = thisArr[i];
                while (index >= 0 && thisArr[index] > value) {
                    thisArr[index + gap] = thisArr[index];
                    index -= gap;
                }
                thisArr[index + gap] = value;
            }
        }
        return thisArr;
    }

    public void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[l];
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
        // at this time l == r
        arr[l] = pivot;
        if (l < right) {
            quick(arr, l + 1, right);
        }
        if (r > left) {
            quick(arr, left, r - 1);
        }
    }
}

