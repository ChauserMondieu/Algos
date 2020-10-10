package sorting;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Practice2 {
    private int size;
    private int[] arr;
    private int scale;
    private List list;

    public Practice2(int size, int scale) {
        this.size = size;
        this.scale = scale;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(this.scale);
        }
    }

    public Practice2() {
        this(10, 100);
    }

    public int[] getCopy(int[] arr) {
        return arr.clone();
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

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public static void main(String[] args) {
        Practice2 p = new Practice2();
        System.out.println(Arrays.toString(p.getArr()));

        int[] arrBubble = p.getCopy(p.getArr());
        p.bubble(arrBubble);
        System.out.println("bubble: " + Arrays.toString(arrBubble));

        int[] arrInsert = p.getCopy(p.getArr());
        p.insert(arrInsert);
        System.out.println("insert: " + Arrays.toString(arrInsert));

        int[] arrSelect = p.getCopy(p.getArr());
        p.select(arrSelect);
        System.out.println("select: " + Arrays.toString(arrSelect));

        int[] arrShellSwap = p.getCopy(p.getArr());
        p.shellSwap(arrShellSwap);
        System.out.println("shellSwap: " + Arrays.toString(arrShellSwap));

        int[] arrShellInsert = p.getCopy(p.getArr());
        p.shellInsert(arrShellInsert);
        System.out.println("shellInsert: " + Arrays.toString(arrShellInsert));

        int[] arrQuick = p.getCopy(p.getArr());
        p.quick(arrQuick, 0, p.getSize() - 1);
        System.out.println("quick: " + Arrays.toString(arrQuick));

        int[] arrRadix = p.getCopy(p.getArr());
        p.radix(arrRadix);
        System.out.println("radix: " + Arrays.toString(arrRadix));
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

    public void select(int[] arr) {
        int max;
        int temp;
        int index;
        for (int i = this.size - 1; i >= 0; i--) {
            max = arr[i];
            index = i;
            for (int j = i; j >= 0; j--) {
                if (arr[j] > max) {
                    max = arr[j];
                    index = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    public void insert(int[] arr) {
        int index;
        int value;
        for (int i = 1; i < this.size; i++) {
            index = i - 1;
            value = arr[i];
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
            for (int i = gap; i < this.size; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + 1]) {
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
        int temp;
        for (int gap = this.size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.size; i++) {
                index = i - gap;
                value = arr[i];
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
        if (l < right) {
            quick(arr, l + 1, right);
        }
        if (r > left) {
            quick(arr, left, r - 1);
        }
    }

    public void radix(int[] arr) {
        int[][] bucket = new int[10][this.size];
        int[] cursor = new int[10];
        int max = arr[0];
        for (int i = 0; i < this.size; i++) {
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
    }
}
