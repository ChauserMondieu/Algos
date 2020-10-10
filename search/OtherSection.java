package search;

import java.util.Arrays;

public class OtherSection {
    private int[] arr;
    private int count;
    private int[] fib;
    private int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int[] getFib() {
        return fib;
    }

    public void setFib(int[] fib) {
        this.fib = fib;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OtherSection(int k) {
        this.arr = new int[100];
        this.k = k;
        this.fib = new int[this.k];
    }

    public static void main(String[] args) {
        OtherSection other = new OtherSection(50);
        System.out.println(other.fibbo(5));
        System.out.println(Arrays.toString(other.getArr()));
        System.out.println("the total count time is: " + other.getCount());

        other.fib(other.getFib(), other.getK());
        int[] fibbo = new int[100];
        for (int i = 0; i < 100; i++) {
            fibbo[i] = i + 20;
        }
        int res = other.fibboSection(fibbo, 45);
        System.out.println(res);
    }

    public void fib(int[] arr, int k) {
        arr[0] = arr[1] = 1;
        for (int i = 0; i < k - 2; i++) {
            arr[i + 2] = arr[i + 1] + arr[i];
        }
    }

    public int fibboSection(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int[] fib = this.fib;
        int k = 0;
        while (fib[k] - 1 < high) {
            k++;
        }
        int[] arr2 = Arrays.copyOf(arr, fib[k]);
        for (int i = 0; i < fib[k] - arr.length; i++) {
            arr2[high + 1 + i] = arr[high];
        }
        while (low <= high) {
            int mid = low + fib[k - 1] - 1;
            if (key < arr2[mid]) {
                high = mid - 1;
                k--;
            } else if (key > arr2[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int fibbo(int i) {
        if (i == 1 || i == 2) {
            return 1;
        } else {
            int res = fibbo(i - 1) + fibbo(i - 2);
            arr[count++] = res;
            return res;
        }
    }
}
