package LinkedList;

public class DoubledLinkedListDemo {
    public static void main(String[] args) {
        DoubledLinkedList list = new DoubledLinkedList();
        DoubleNode node1 = new DoubleNode(1,"a","aa");
        DoubleNode node2 = new DoubleNode(2,"b","bb");
        DoubleNode node7 = new DoubleNode(7,"g","gg");
        DoubleNode node9 = new DoubleNode(9,"i","ii");
        list.add(node1);
        list.add(node2);
        list.add(node7);
        list.add(node9);
        list.traverse(list.getHead());
        DoubleNode node6 = new DoubleNode(6,"f","ff");
        DoubleNode node4 = new DoubleNode(4,"d","dd");
        list.addByOrder(node6);
        list.addByOrder(node4);
        System.out.println("===========================");
        list.traverse(list.getHead());

    }
}

class DoubledLinkedList{
     private DoubleNode head = new DoubleNode(0," "," ");

    public DoubleNode getHead() {
        return head;
    }

    public boolean isEmpty(){
        return null == head.next;
    }

    public boolean add(DoubleNode node){
        DoubleNode temp = head;
        while(null != temp.next){
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
        return true;
    }

    public boolean addByOrder(DoubleNode node){
        DoubleNode temp = head;
        boolean flag = false;
        while(null != temp){
            if(temp.getNum() < node.getNum()){
                temp = temp.next;
            }else if(temp.getNum() == node.getNum()){
                flag = true;
                break;
            }else{
                break;
            }
        }
        if(flag){
            throw new RuntimeException("this num already exists!");
        }
        temp.pre.next = node;
        node.pre = temp.pre;
        node.next = temp;
        temp.pre = node;
        return true;
    }

    public boolean delete(int num){
        if(isEmpty()){
            throw new RuntimeException("list is already empty");
        }
        DoubleNode temp = head.next;
        boolean flag = true;
        while(temp.getNum() != num){
            temp = temp.next;
            if(null == temp){
                flag = false;
                break;
            }
        }
        if(!flag){
            System.out.println("no qualified nodes found in this list");
            return false;
        }
        temp.pre.next = temp.next;
        if(null != temp.next){
            temp.next.pre = temp.pre;
        }
        return true;
    }

    public void traverse(DoubleNode head){
        if(isEmpty()){
            throw new RuntimeException("list is already empty");
        }
        DoubleNode temp = head.next;
        while(null == temp){
            throw new RuntimeException("list is already empty!");
        }
        while(null != temp){
            System.out.println(temp.toString());
            temp = temp.next;
        }

    }

}

class DoubleNode{
    private int num;
    private String name;
    private String alias;
    public DoubleNode pre;
    public DoubleNode next;

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

    public DoubleNode(){
        this.pre = null;
        this.next = null;
    }

    public DoubleNode(int num, String name, String alias) {
        this();
        this.num = num;
        this.name = name;
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
