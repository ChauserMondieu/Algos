package LinkedList;

import javax.lang.model.element.NestingKind;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        Node node1 = new Node(1,"a","aa");
        Node node2 = new Node(2,"b","bb");
        Node node3 = new Node(3,"c","cc");
        Node node4 = new Node(4,"d","dd");
        list.addNodeByOrder(node1);
        list.addNodeByOrder(node4);
        list.addNodeByOrder(node2);
        list.addNodeByOrder(node3);
        list.showList();
//        Node node22 = new Node(2,"B","BB");
//        list.update(2,node22);
//        list.showList();
//        list.delete(2);
//        list.showList();
//        System.out.println(SingleLinkedList.getLength(list.getHead()));
//        System.out.println(SingleLinkedList.getKth(3,list.getHead()).toString());
        SingleLinkedList.reverse(list.getHead());
        list.showList();
        SingleLinkedList.reversePrint(list.getHead());

    }
}

class SingleLinkedList{
    private Node head = new Node();

    public Node getHead(){
        return this.head;
    }

    public boolean isEmpty(){
        return head.next == null;
    }

    public boolean addNode(Node node){
        Node temp = head;
        while(true){
            if(null == temp.next){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.next = null;
        return true;
    }

    public boolean addNodeByOrder(Node node){
        Node temp = head;
        boolean flag = false;
        while(true){
            if(null == temp.next){
                break;
            }
            if(node.getNum() < temp.next.getNum()){
                break;
            }else if(node.getNum() == temp.getNum()){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            throw new RuntimeException("already exists!");
        }
        else{
            node.next = temp.next;
            temp.next = node;
        }
        return true;
    }

    public void showList(){
        if(isEmpty()){
            throw new RuntimeException("the list is empty!");
        }
        Node temp = head.next;
        while(true){
            if(null == temp.next){
                System.out.println(temp.toString());
                break;
            }
            System.out.println(temp.toString());
            temp = temp.next;
        }
        return;
    }

    public boolean update(int num, Node node){
        if(isEmpty()){
            throw new RuntimeException("the list is empty");
        }
        boolean flag = false;
        Node temp = head;
        while(true){
            if(null == temp){
                break;
            } else if(num == temp.next.getNum()){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            node.next = temp.next.next;
            temp.next = node;
        } else{
            System.out.println("didn't find the corresponding position.");
            return false;
        }
        return true;
    }

    public boolean delete(int num){
        if(isEmpty()){
            throw new RuntimeException("the list is already empty");
        }
        boolean flag = false;
        Node temp = head;
        while(true){
            if(null == temp){
                break;
            }else if(temp.next.getNum() == num){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.println("did't find the corresponding position");
            return false;
        }
        return true;
    }

    public static int getLength(Node head){
        if(null == head.next){
           return 0;
        }
        int length = 1;
        Node cursor = head.next;
        while(null != cursor.next){
            cursor = cursor.next;
            length++;
        }
        return length;
    }

    public static Node getKth(int k, Node head){
        if(null == head.next){
            throw new RuntimeException("this list is already empty");
        }
        Node cur1 = head.next;
        Node cur2 = head.next;
        if(k > getLength(head)){
            throw new RuntimeException("index outof bound");
        }
        for(int i=0; i<k; i++){
            cur1 = cur1.next;
        }
        while(null != cur1){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur2;
    }

    public static void reverse(Node head){
        if(null == head.next){
            return;
        }
        Node newHead = new Node(0,null,null);
        Node cur = head.next;
        Node next;
        while(null != cur){
            next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = next;
        }
        head.next = newHead.next;
    }

    public static void reversePrint(Node head){
        Stack<Node> stack = new Stack();
        if(null == head.next){
            throw new RuntimeException("lisk is already empty!");
        }
        Node cur = head.next;
        while(null != cur){
            stack.add(cur);
            cur = cur.next;
        }
        while(stack.size() > 0){
            System.out.println((stack.pop()).toString());
        }
    }
}

class Node{
    private int num;
    private String name;
    private String alias;
    public Node next;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Node(){
        this.next = null;
    }
    public Node(int num, String name, String alias) {
        this();
        this.num = num;
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
