package sorting;

import java.util.Arrays;
import java.util.Random;

public class RadixSorting {
    private int size;
    private int[] arr;

    public RadixSorting(int size) {
        this.size = size;
        arr = new int[this.size];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = random.nextInt(10000);
        }
    }

    public RadixSorting() {
        this(50);
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
        RadixSorting radix = new RadixSorting(20);
        System.out.println(Arrays.toString(radix.getArr()));

        long start = System.currentTimeMillis();

        int[] arr2 = radix.radix();

        long end = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr2));
        System.out.printf("the total time for execution is: %s ms.", (int) (end - start));
    }

    public int[] radix() {
        int[] thisArr = this.arr.clone();
        // placing each digit
        int[][] bucket = new int[10][this.size];
        // storing cursor of each bucket
        int[] digitCursor = new int[10];
        // find the maximum digits of all elements
        int max = thisArr[0];
        for (int i = 1; i < this.size; i++) {
            if (thisArr[i] > max) {
                max = thisArr[i];
            }
        }
        int digit = (max + "").length();

        //start the bucket sort
        for (int i = 0, n = 1; i < digit; i++, n *= 10) {
            for (int j = 0; j < this.size; j++) {
                int temp = thisArr[j] / n % 10;
                bucket[temp][digitCursor[temp]] = thisArr[j];
                digitCursor[temp]++;
            }

            // store the elements back to previous array.
            int index = 0;
            for (int k = 0; k < 10; k++) {
                if (digitCursor[k] != 0) {
                    for (int l = 0; l < digitCursor[k]; l++) {
                        thisArr[index++] = bucket[k][l];
                        bucket[k][l] = 0;
                    }
                    digitCursor[k] = 0;
                }
            }
//            System.out.println(Arrays.toString(thisArr));
        }
        return thisArr;
    }
}
