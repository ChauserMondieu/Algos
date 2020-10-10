package sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Review {
    private int size;
    private int[] arr;

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

    public Review(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
//        int i = (int)(Math.random()*10000);
        for(int i =0; i<this.size;i++){
            this.arr[i] = rand.nextInt(10000);
        }
    }

    public Review(){
        this(20);
    }

    public int[] getCopy(int[] arr){
        return arr.clone();
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = new Date();
        String start = format.format(startDate);

        Review review = new Review(50);
        int[] arr1 = review.getCopy(review.getArr());
        int[] arr2 = review.getCopy(review.getArr());
        System.out.println(Arrays.toString(review.getArr()));

        long startLong = System.currentTimeMillis();
        review.radixSorting(arr1);
        long endLong = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr1));
        System.out.printf("the total time for execution is: %s ms.\n", (int)(endLong-startLong));

        long startLong2 = System.currentTimeMillis();
        review.quick(arr2,0, review.getSize()-1);
        long endLong2 = System.currentTimeMillis();

        System.out.println(Arrays.toString(arr1));
        System.out.printf("the total time for execution is: %s ms.\n", (int)(endLong2-startLong2));
    }

    public void radixSorting(int[] arr){
        int[][] bucket = new int[10][this.size];
        int[] cursor = new int[10];
        int max = arr[0];
        for(int i=1; i<this.size; i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        int digit = (max+"").length();

        for(int i=0, n=1; i<digit; i++, n*=10){
            for(int j=0; j<this.size; j++){
                int temp = arr[j] /n %10;
                bucket[temp][cursor[temp]] = arr[j];
                cursor[temp] ++;
            }
            int index = 0;
            for(int k=0;k<10;k++){
                if(cursor[k]!=0){
                    for(int l=0; l<cursor[k];l++){
                        arr[index++] = bucket[k][l];
                        bucket[k][l] = 0;
                    }
                    cursor[k] = 0;
                }
            }
        }
    }

    public void quick(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[l];
        while(l<r){
            while(l<r && arr[r]>=pivot){
                r--;
            }
            arr[l] = arr[r];
            while(l<r && arr[l]<=pivot){
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        if(l<right){
            quick(arr,l+1,right);
        }
        if(r>left){
            quick(arr,left,r-1);
        }
    }
}
