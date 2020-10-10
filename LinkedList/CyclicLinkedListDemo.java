package LinkedList;

import java.util.concurrent.CyclicBarrier;

public class CyclicLinkedListDemo {
    public static void main(String[] args) {
        CyclicLinkedList list = new CyclicLinkedList();
        list.autoAdd(5);
        list.traverse();
        list.outOfCircle(1,2);
    }
}

class CyclicLinkedList{
    private CylicNode first;
    private CylicNode cur;
    private CylicNode news;
    private CylicNode temp;
    private int size;

    public CyclicLinkedList() {
        this.first = new CylicNode(1);
        first.setNext(first);
    }

    public CyclicLinkedList(CylicNode first) {
        this.first = first;
        first.setNext(first);
    }

    public void autoAdd(int num){
        if(num <1){
            System.out.println("invalid input");
            return;
        }
        for(int i=2; i<num+1; i++){
            news = new CylicNode(i);
            if(i == 2){
                first.setNext(news);
                news.setNext(first);
            }else{
                cur.setNext(news);
                news.setNext(first);
            }
            cur = news;
        }
        this.size = num;
        this.cur = null;
        this.news = null;
    }

    public void add(CylicNode node){

    }

    public void traverse(){
        temp = first;
        while(temp.getNext() != first){
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
        // only one node exists in this list
        // or cursor at the last element
        System.out.println(temp.toString());
        temp = null;
    }

    public void outOfCircle(int startNo, int step){
        CylicNode pre = first;
        if(startNo > this.size || startNo < 1 || first == null){
            System.out.println("invalid input!");
            return;
        }
        // move first to the start position
        while(first.getNum() != startNo){
            first = first.getNext();
        }
        // move pre to the previous node of first
        while(pre.getNext() != first){
            pre = pre.getNext();
        }
        while(pre != first){
            for(int i=0; i<step-1; i++){
                pre = pre.getNext();
                first = first.getNext();
            }
            // break the link
            pre.setNext(first.getNext());
            System.out.printf("%dth node leave the list\n",first.getNum());
            first = pre.getNext();
        }
        System.out.printf("%dth node leave the list\n",first.getNum());
    }
}

class CylicNode{
    private int num;
    private CylicNode next;

    public CylicNode() {
        this.next = null;
    }

    public CylicNode(int num) {
        this();
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public CylicNode getNext() {
        return next;
    }

    public void setNext(CylicNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "CylicNode{" +
                "num=" + num +
                '}';
    }
}
