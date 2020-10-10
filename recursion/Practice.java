package recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Practice {
    private List<int[]> list = new LinkedList<>();
    private int[] arr;
    private int size;
    private int count;

    public List<int[]> getList() {
        return list;
    }

    public void setList(List<int[]> list) {
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Practice(int size) {
        this.size = size;
        this.count = 0;
        this.arr = new int[this.size];
    }

    public Practice() {
        this(8);
    }

    private boolean compare(int row) {
        for (int i = 0; i < row; i++) {
            count++;
            if (arr[i] == arr[row] || Math.abs(i - row) == Math.abs(arr[i] - arr[row])) {
                return false;
            }
        }
        return true;
    }

    public boolean queen(int row) {
        if (row == this.size) {
            this.list.add(this.arr.clone());
            return true;
        }
        for (int i = 0; i < this.size; i++) {
            arr[row] = i;
            if (compare(row)) {
                queen(row + 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Practice practice = new Practice();
        practice.queen(0);
        for (int[] arr : practice.getList()) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.printf("the total number of possibilities is: %s .\n", practice.getList().size());
        System.out.printf("this total comparason is %s times. \n", practice.getCount());
    }
}
