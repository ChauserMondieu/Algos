package search;

import java.util.Arrays;
import java.util.Random;

public class Bisection {
    private int size;
    private int[] arr;
    private int scale;
    private int[] arrB;
    private int Bcursor;
    // calling times for function bisection2
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getArrB() {
        return arrB;
    }

    public void setArrB(int[] arrB) {
        this.arrB = arrB;
    }

    public int getBcursor() {
        return Bcursor;
    }

    public void setBcursor(int bcursor) {
        Bcursor = bcursor;
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

    public int[] getCopy() {
        return this.arr.clone();
    }

    public static void main(String[] args) {
        Bisection bisection = new Bisection(10, 100);
        int[] arr = bisection.getCopy();
        int[] temp = new int[bisection.size];
        bisection.merge(arr, 0, bisection.getSize() - 1, temp);
        bisection.bisection(arr, 0, bisection.getSize() - 1, 4);
        System.out.println(bisection.getArr()[4]);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(bisection.getArrB()));

        int[] arr2 = new int[20];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i + 15;
        }
        System.out.println(Arrays.toString(arr2));
        int res = bisection.bisection2(arr2, 0, arr2.length - 1, 20);
        System.out.printf("the position is in: %s. ", res);
        System.out.printf("total calling time is: %s. ", bisection.getCount());
    }

    public Bisection(int size, int scale) {
        this.size = size;
        this.scale = scale;
        this.arrB = new int[this.size];
        this.Bcursor = 0;
        arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(scale);
        }
    }

    public void merge(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            merge(arr, left, mid, temp);
            merge(arr, mid + 1, right, temp);
            mergeSort(arr, left, mid, right, temp);
        }
    }

    public void mergeSort(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int t = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        t = 0;
        int leftCursor = left;
        while (leftCursor <= right) {
            arr[leftCursor++] = temp[t++];
        }
    }

    public void bisection(int[] arr, int left, int right, int value) {
        if (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                arrB[Bcursor++] = mid + 1;
            }
            bisection(arr, left, mid, value);
            bisection(arr, mid + 1, right, value);
        } else {
            return;
        }
    }

    public int bisection2(int[] arr, int left, int right, int value) {
        if (value > arr[right] && value < arr[left]) {
            System.out.println("no such value!~");
            return -1;
        }

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (value < midValue) {
            count ++;
            bisection(arr, left, mid - 1, value);
        } else if (value > midValue) {
            count ++;
            bisection2(arr, mid + 1, right, value);
        }
        return mid;
    }
}
