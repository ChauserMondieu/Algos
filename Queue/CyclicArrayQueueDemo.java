package Queue;

import java.util.Scanner;

public class CyclicArrayQueueDemo {
    public static void main(String[] args){
        CyclicArrayQueue queue = new CyclicArrayQueue(3);
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
                    try{
                        queue.showAll();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("please input the number:");
                    int value = scanner.nextInt();
                    try{
                        queue.addQueue(value);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try{
                        int out = queue.deQueue();
                        System.out.printf("the output number is: %d\n", out);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int head = queue.showHead();
                        System.out.printf("the currnet head number is : %d\n", head);
                    }catch(Exception e){
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
        System.out.println("this program is successfully terminated.");
    }
}

class CyclicArrayQueue{
    private int head;
    private int rear;
    private int size;
    private int[] arr;

    public CyclicArrayQueue(int size){
        this.size = size;
        this.arr = new int[this.size];
        this.head = 0;
        this.rear = 0;
    }

    public CyclicArrayQueue(){
        this(16);
    }

    public boolean isFull(){
        return (this.rear + 1) % this.size  == head;
    }

    public boolean isEmpty(){
        return this.rear == this.head;
    }

    public boolean addQueue(int n){
        if(isFull()){
            throw new RuntimeException("the quque is already full!");
        }
        arr[rear] = n;
        rear = (rear + 1) % this.size;
        return true;
    }

    public int deQueue(){
        if(isEmpty()){
            throw new RuntimeException("the queue is empty!");
        }
        head = (head + 1) % this.size;
        return arr[head-1];
    }

    private int actualSize(){
        return (this.rear + this.size - this.head) % size;
    }

    public boolean showAll(){
        if(isEmpty()){
            System.out.println("the queue is empty!");
            return false;
        }
        for(int i = head; i <= actualSize(); i++){
            System.out.printf("arr[%d]=%d\n", i % size, arr[i % size]);
        }
        return true;
    }

    public int showHead(){
        if(isEmpty()){
            throw new RuntimeException("the queue is empty!");
        }
        return arr[head];
    }
}
