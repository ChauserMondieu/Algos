package Stack;

public class Calculator {
    public static void main(String[] args) {
        cal("3+6*2-4");
    }

    public static void cal(String expression){
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        char ch = ' ';
        CalStack numStack = new CalStack();
        CalStack operStack = new CalStack();
        while(index<expression.length()){
            ch = expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                if(operStack.isEmpty()){
                    operStack.push(ch);
                }else{
                    if(operStack.operPriority((char)operStack.peek())>=operStack.operPriority(ch)){
                        char oper = (char)operStack.pop();
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }
            }else{
                numStack.push(ch - 48);
            }
            index ++;
        }

        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            char oper = (char)operStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println(expression + " = " + numStack.pop());;
    }

}

class CalStack{
    private int size;
    private int top;
    private int[] stack;

    public int getSize() {
        return size;
    }

    public CalStack() {
        this.size = 16;
        this.top = -1;
        stack = new int[this.size];
    }

    public CalStack(int size) {
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

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("stack is empty!");
        }
        return stack[top];
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

    public boolean isOper(char oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    public int operPriority(char oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            throw new RuntimeException("wrong operator! - at priority check");
        }
    }

    public int cal(int num1, int num2, char oper){
        switch(oper){
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                throw new RuntimeException("wrong operators!");
        }
    }
}