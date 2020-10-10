package sorting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class MergeSorting {
    private int size;
    private int[] arr;
    private int count;

    public MergeSorting(int size) {
        this.count = 0;
        this.size = size;
        arr = new int[this.size];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    public MergeSorting() {
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
        MergeSorting merges = new MergeSorting(20);
        int[] temp = new int[merges.getSize()];
        System.out.println(Arrays.toString(merges.getArr()));

        long start = System.currentTimeMillis();
        Date dateStart = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String strStart = format.format(dateStart);

        merges.mergesort(merges.getArr(), 0, merges.getSize() - 1, temp);

        long end = System.currentTimeMillis();
        Date dateEnd = new Date();
        String strEnd = format.format(dateEnd);

        System.out.println(Arrays.toString(merges.getArr()));
        System.out.printf("Algorithm start time: %s, and end time: %s \n", strStart, strEnd);
        System.out.printf("total execution time is: %s ms. \n", (int)(end-start));
    }


    public void mergesort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergesort(arr, left, mid, temp);
            mergesort(arr, mid + 1, right, temp);
            merge(arr, left, right, mid, temp);
            this.count ++;
            System.out.printf("merge %s times\n", this.count);
        }

    }

    public void merge(int[] arr, int left, int right, int mid, int[] temp) {
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
            lcursor++;
            t++;
        }
    }

}
