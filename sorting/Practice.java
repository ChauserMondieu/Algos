package sorting;

import java.util.Arrays;
import java.util.Random;

public class Practice {
    private int[] arr;
    private int size;
    private int scale;

    public Practice(int size, int scale) {
        this.size = size;
        this.scale = scale;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            this.arr[i] = rand.nextInt(this.scale);
        }
    }

    public Practice() {
        this(10, 100);
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
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
        return this.arr.clone();
    }

    public static void main(String[] args) {
        Practice practice = new Practice();
        System.out.println("original: " + Arrays.toString(practice.getArr()));

        int[] arrBubble = practice.getCopy(practice.getArr());
        practice.bubble(arrBubble);
        System.out.println("bubble: " + Arrays.toString(arrBubble));

        int[] arrInsert = practice.getCopy(practice.getArr());
        practice.insert(arrInsert);
        System.out.println("insertion: " + Arrays.toString(arrInsert));

        int[] arrSelect = practice.getCopy(practice.getArr());
        practice.select(arrSelect);
        System.out.println("selection: " + Arrays.toString(arrSelect));

        int[] arrQuick = practice.getCopy(practice.getArr());
        practice.quick(arrQuick, 0, practice.getSize() - 1);
        System.out.println("quick: " + Arrays.toString(arrQuick));

        int[] arrMerge = practice.getCopy(practice.getArr());
        int[] temp = new int[practice.size];
        practice.merge(arrMerge, 0, practice.getSize() - 1, temp);
        System.out.println("merge: " + Arrays.toString(arrMerge));

        int[] arrRadix = practice.getCopy(practice.getArr());
        practice.radix(arrRadix);
        System.out.println("radix: " + Arrays.toString(arrRadix));

        int[] arrShellSwap = practice.getCopy(practice.getArr());
        practice.shellSwap(arrShellSwap);
        System.out.println("shellSwap: " + Arrays.toString(arrShellSwap));

        int[] arrShellInsert = practice.getCopy(practice.getArr());
        practice.shellSwap(arrShellInsert);
        System.out.println("shellInsert: " + Arrays.toString(arrShellInsert));
    }

    public void bubble(int[] arr) {
        boolean flag = true;
        int temp;
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

    public void insert(int[] arr) {
        int value;
        int index;
        for (int i = 1; i < this.size; i++) {
            index = i - 1;
            value = arr[index + 1];
            while (index >= 0 && arr[index] > value) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = value;
        }
    }

    public void select(int[] arr) {
        int min;
        int index;
        int temp;
        for (int i = 0; i < this.size; i++) {
            min = arr[i];
            index = i;
            for (int j = i; j < this.size; j++) {
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

    public void quick(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[l];
        if (l < r) {
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
        if (l < right) {
            quick(arr, l + 1, right);
        }
        if (r > left) {
            quick(arr, left, r - 1);
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

    public void radix(int[] arr) {
        int[][] bucket = new int[10][this.size];
        int[] cursor = new int[10];
        int max = arr[0];
        for (int i = 1; i < this.size; i++) {
            if (arr[i] > max) {
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
        }
        int index = 0;
        for (int k = 0; k < 10; k++) {
            if (cursor[k] != 0) {
                for (int l = 0; l < cursor[k]; l++) {
                    arr[index++] = bucket[k][l];
                    bucket[k][l] = 0;
                }
                cursor[k] = 0;
            }
        }
    }

    public void shellSwap(int[] arr) {
        int temp;
        for (int gap = this.size / 3; gap > 0; gap /= 3) {
            for (int i = 2 * gap; i < this.size; i += gap) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void shellInsert(int[] arr) {
        int index;
        int value;
        for (int gap = this.size / 3; gap > 0; gap /= 3) {
            for (int j = gap; j < this.size; j += gap) {
                index = j - gap;
                value = arr[index + gap];
                while (index >= 0 && arr[index] > value) {
                    arr[index + gap] = arr[index];
                    index--;
                }
                arr[index + gap] = value;
            }
        }
    }
}
