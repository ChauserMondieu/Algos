package Practice;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
    private int[] arr;
    private int size;

    public Sorting(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(1000);
        }
    }

    public Sorting() {
        this(10);
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

    public int[] getCopy(int[] arr) {
        return arr.clone();
    }

    public static void main(String[] args) {
        Sorting sorting = new Sorting(20);
        System.out.println(Arrays.toString(sorting.getArr()));

        int[] arr1 = sorting.getCopy(sorting.getArr());
        sorting.bubble(arr1);
        System.out.println("bubble: " + Arrays.toString(arr1));

        int[] arr2 = sorting.getCopy(sorting.getArr());
        sorting.select(arr2);
        System.out.println("select: " + Arrays.toString(arr2));

        int[] arr3 = sorting.getCopy(sorting.getArr());
        sorting.insert(arr3);
        System.out.println("insert: " + Arrays.toString(arr3));

        int[] arr4 = sorting.getCopy(sorting.getArr());
        sorting.shellSwap(arr4);
        System.out.println("shell swap: " + Arrays.toString(arr4));

        int[] arr5 = sorting.getCopy(sorting.getArr());
        sorting.shellInsert(arr5);
        System.out.println("shell insert: " + Arrays.toString(arr5));

        int[] arr6 = sorting.getCopy(sorting.getArr());
        sorting.quick(arr6, 0, sorting.getSize() - 1);
        System.out.println("quick: " + Arrays.toString(arr6));

        int[] arr7 = sorting.getCopy(sorting.getArr());
        int[] arr71 = new int[sorting.getSize()];
        sorting.merge(arr7, 0, sorting.getSize() - 1, arr71);
        System.out.println("merge: " + Arrays.toString(arr7));

        int[] arr8 = sorting.getCopy(sorting.getArr());
        sorting.radix(arr8);
        System.out.println("radix: " + Arrays.toString(arr8));

        int[] arr9 = sorting.getCopy(sorting.getArr());
        sorting.heap(arr9);
        System.out.println("heap: " + Arrays.toString(arr9));

        int num = sorting.getArr()[5];
        int res = sorting.binarySearch(arr8, 0, sorting.getSize() - 1, num);
        System.out.printf("the original num is %s; and the result is %s.\n", num, res);

    }

    public void bubble(int[] arr) {
        int temp;
        boolean flag = true;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                return;
            }
        }
    }

    public void select(int[] arr) {
        int index;
        int min;
        int temp;
        for (int i = 0; i < this.size; i++) {
            min = arr[i];
            index = i;
            for (int j = i + 1; j < this.size; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    public void insert(int[] arr) {
        int value;
        int index;
        for (int i = 1; i < this.size; i++) {
            value = arr[i];
            index = i - 1;
            while (index >= 0 && arr[index] > value) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = value;
        }
    }

    public void shellSwap(int[] arr) {
        int temp;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i += gap) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public void shellInsert(int[] arr) {
        int index;
        int value;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i++) {
                value = arr[i];
                index = i - gap;
                while (index >= 0 && arr[index] > value) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                arr[index + gap] = value;
            }
        }
    }

    public void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[l];
        while (l < r) {
            while (l < r && arr[r] > pivot) {
                r--;
            }
            arr[l] = arr[r];
            while (l < r && arr[l] < pivot) {
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

        int cursor = left;
        t = 0;
        while (cursor <= right) {
            arr[cursor++] = temp[t++];
        }
    }

    public void radix(int[] arr) {
        int[][] bucket = new int[10][this.size];
        int[] cursor = new int[10];
        int max = arr[0];
        for (int i = 1; i < this.size; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int digit = (max + "").length();

        for (int i = 0, n = 1; i < digit; i++, n *= 10) {
            for (int j = 0; j < this.size; j++) {
                int temp = arr[j] / n % 10;
                bucket[temp][cursor[temp]] = arr[j];
                cursor[temp]++;
            }

            int index = 0;
            for (int k = 0; k < 10; k++) {
                if (cursor[k] != 0) {
                    for (int l = 0; l < cursor[k]; l++) {
                        arr[index++] = bucket[k][l];
                        bucket[k][l] = 0;
                    }
                }
                cursor[k] = 0;
            }
        }
    }

    public void heap(int[] arr) {
        int temp;
        for (int i = this.size / 2 - 1; i >= 0; i--) {
            shiftDown(arr, i, this.size);
        }
        for (int i = this.size - 1; i >= 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            shiftDown(arr, 0, i);
        }
    }

    private void shiftDown(int[] arr, int index, int length) {
        int temp = arr[index];
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                i++;
            }
            if (arr[i] > temp) {
                arr[index] = arr[i];
                index = i;
            } else {
                break;
            }
            arr[index] = temp;
        }
    }

    public int binarySearch(int[] arr, int left, int right, int value) {
        if (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < value) {
                return binarySearch(arr, mid + 1, right, value);
            } else if (arr[mid] > value) {
                return binarySearch(arr, left, mid - 1, value);
            } else {
                return mid;
            }
        } else {
            return -1;
        }
    }

}
