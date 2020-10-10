package sorting;

import java.util.Arrays;
import java.util.Random;

public class ShellSorting {
    private int size;
    private int[] arr;

    public ShellSorting(int size) {
        this.size = size;
        this.arr = new int[size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(100);
        }
    }

    public static void main(String[] args) {
        ShellSorting shell = new ShellSorting(10);
        System.out.println(Arrays.toString(shell.getArr()));
        shell.shell2();
        System.out.println(Arrays.toString(shell.getArr()));
        shell.shell();
        System.out.println(Arrays.toString(shell.getArr()));
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

    public void shell() {
        int temp;
        int count = 0;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i += gap) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                        count++;
                    }
                }
            }
//            System.out.println(Arrays.toString(arr));
        }
    }

    public void shell2() {
        int value;
        int index;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i++) {
                index = i - gap;
                value = arr[i];
                while (index >= 0 && value < arr[index]) {
                    arr[index + gap] = arr[index];
                    index-=gap;
                }
                arr[index + gap] = value;
            }
        }
    }
}
