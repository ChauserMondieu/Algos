package Queue;

import java.util.Scanner;

public class ArrayQueue {
    private int[] arr;
    private int length;
    private int head;
    private int rear;

    public static void main(String[] args){
        ArrayQueue arr = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while(loop){
            System.out.println("s(show):the the queue");
            System.out.println("e(exit):exit the program");
            System.out.println("a(add):add one new element to the queue");
            System.out.println("g(get):get the last element from the the queue");
            System.out.println("h(head):show the first element of the queue");
            key = scanner.next().charAt(0);
            switch(key){
                case 's':
                    arr.showAll();
                    break;
                case 'a':
                    System.out.println("please input one number:");
                    int value = scanner.nextInt();
                    try {
                        arr.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int out = arr.deQueue();
                        System.out.println("the data taken out is: " + out);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = arr.getHead();
                        System.out.println("the head is: " + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("program has stopped...");

    }

    public ArrayQueue(int length){
        this.arr = new int[length];
        this.length = length;
        this.head = -1;
        this.rear = -1;
    }

    public ArrayQueue(){
        this.length = 16;
        this.arr = new int[this.length];
    }

    public boolean isFull(){
        return this.rear == this.length - 1;
    }

    public boolean isEmpty(){
        return this.head == this.rear;
    }

    public boolean addQueue(int n){
        if(isFull()){
            throw new RuntimeException("the queue is full");
        }
        rear++;
        this.arr[rear] = n;
        return true;
    }

    public int deQueue(){
        if(isEmpty()){
            throw new RuntimeException("the queue is empty");
        }
        head ++;
        return this.arr[head];
    }

    public int getHead(){
        if(isEmpty()){
            throw new RuntimeException("the queue is empty");
        }
        return this.arr[head + 1]
        ;
    }

    public boolean showAll(){
        if(isEmpty()){
            System.out.println("the queue is empty");
            return false;
        }
        for(int i= head+1; i< rear+1; i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
        return true;
    }
}
