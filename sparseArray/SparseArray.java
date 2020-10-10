package sparseArray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        int board[][] = new int[11][11];
//        int[][] board2 = new int[11][11];
        board[1][2] = 1;
        board[2][3] = 2;
        for(int[] row : board) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        SparseArray arrs = new SparseArray();

        int[][] sparse = arrs.store(board);
        try {
            arrs.storeToFile(sparse);
            sparse = arrs.loadToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int[] row : sparse) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        int[][] arr = arrs.load(sparse);
        for(int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public int[][] store(int[][] arr){
        int sum = 0;
        int count = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]!=0){
                    sum++;
                }
            }
        }
        int[][] sparse = new int[sum+1][3];
        sparse[0][0] = arr.length;
        sparse[0][1] = arr[0].length;
        sparse[0][2] = sum;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j]!=0){
                    count ++;
                    sparse[count][0] = i;
                    sparse[count][1] = j;
                    sparse[count][2] = arr[i][j];
                }
            }
        }
        return sparse;
    }

    public int[][] load(int[][] sparse){
        int[][] arr = new int[sparse[0][0]][sparse[0][1]];
        int sum = sparse[0][2];
        for(int i=1;i<=sum;i++){
            arr[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        return arr;
    }

    public boolean storeToFile(int[][] arr) throws IOException {
        String path = "C://Users//AAAAA//Documents//programming-documents//store.txt";
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(
                        new File(path))
        );

        try {
            oos.writeObject(arr);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally{
            if (null!=oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
    return true;
    }

    public int[][] loadToFile() throws IOException {
        String path = "C://Users//AAAAA//Documents//programming-documents//store.txt";
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File(path))
        );
        int[][] arr = null;
        try {
            arr = (int[][]) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if (null!=ois) {
                ois.close();
            }
        }
        return arr;
    }
}
