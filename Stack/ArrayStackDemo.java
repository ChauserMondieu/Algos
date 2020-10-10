package Stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
//        stack.push(2);
//        stack.push(6);
//        stack.push(3);
//        stack.push(7);
//        stack.push(9);
//        stack.push(12);
//        stack.show();
//        System.out.println("=================");
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        stack.show();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key = ' ';
        while(loop){
            System.out.println("s - show");
            System.out.println("e - exit");
            System.out.println("u - push");
            System.out.println("p - pop");
            key = scanner.next().charAt(0);
            switch(key){
                case 's':
                    try{
                        stack.show();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }finally{
                        break;
                    }
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'u':
                    System.out.println("please input a number:");
                    try{
                        int value = scanner.nextInt();
                        stack.push(value);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }finally{
                        break;
                    }
                case 'p':
                    try{
                        int out = stack.pop();
                        System.out.printf("the number is = %d\n", out);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }finally{
                        break;
                    }
            }
        }

    }
}

class ArrayStack{
    private int size;
    private int top;
    private int[] stack;

    public int getSize() {
        return size;
    }

    public ArrayStack() {
        this.size = 16;
        this.top = -1;
        stack = new int[this.size];
    }

    public ArrayStack(int size) {
        this();
        this.size = size;
    }

    public boolean isEmpty(){
        return this.top == -1;
    }

    public boolean isFull(){
        return this.top == this.size-1;
    }

    public boolean push(int num){
        if(isFull()){
            throw new RuntimeException("stack is full!");
        }
        top++;
        this.stack[top] = num;
        return true;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("stack is empty!");
        }
        return stack[top--];
    }

    public void show(){
        if(isEmpty()){
            System.out.println("stack is already empty!");
            return;
        }
        for(int i=0; i<=top; i++){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
