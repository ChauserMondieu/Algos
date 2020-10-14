package Practice;

import java.util.Scanner;

public class HashMap<I extends Number, S> {
    private HashList[] nodeList;
    private int hashLimit;

    public HashMap(int hashLimit) {
        this.hashLimit = hashLimit;
        this.nodeList = new HashList[hashLimit];
        for (int i = 0; i < hashLimit; i++) {
            this.nodeList[i] = new HashList();
        }
    }

    public HashMap() {
        this(13);
    }

    private int hash(String value) {
        return value.hashCode() % hashLimit;
    }

    public void add(String value) {
        int hash = hash(value);
        Node node = new Node(hash, value);
        this.nodeList[hash].add(node);
    }

    public void sortAdd(String value) {
        int hash = hash(value);
        Node node = new Node(hash, value);
        nodeList[hash].sortAdd(node);
    }

    public void delete(String value) {
        int hash = hash(value);
        this.nodeList[hash].delete(value);
    }

    public int[] search(String value) {
        int[] result = new int[2];
        int hash = hash(value);
        result[0] = hash;
        result[1] = this.nodeList[hash].search(value);
        return result;
    }

    public void traverse() {
        for (int i = 0; i < this.hashLimit; i++) {
            System.out.printf("arr %s: \n", i);
            this.nodeList[i].traverse();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap map = new HashMap(13);
        char key;
        boolean flag = true;
        while (flag) {
            System.out.println("a: add");
            System.out.println("d: delete");
            System.out.println("s: search");
            System.out.println("t: traverse");
            System.out.println("e: exit");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("please input value");
                    String value = scanner.next();
                    try {
                        map.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'd':
                    System.out.println("please input value");
                    String deleteValue = scanner.next();
                    try {
                        map.delete(deleteValue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    System.out.println("please input value");
                    String searchValue = scanner.next();
                    try {
                        int[] result = map.search(searchValue);
                        System.out.printf("this value is in %s list %s position \n.", result[0], result[1]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 't':
                    try {
                        map.traverse();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    flag = false;
                    scanner.close();
            }
        }
    }
}

class HashList {
    private Node head;

    public HashList() {
        this.head = new Node(-1, "");
    }

    protected void add(Node node) {
        Node cursor;
        if (null == this.head.getNext()) {
            this.head.setNext(node);
        } else {
            cursor = this.head.getNext();
            while (null != cursor.getNext()) {
                cursor = cursor.getNext();
            }
            cursor.setNext(node);
        }
    }

    protected void sortAdd(Node node) {
        Node cursor;
        if (null == this.head.getNext()) {
            this.head.setNext(node);
        } else {
            cursor = this.head;
            while (null != cursor.getNext() && node.getNum() > cursor.getNum()) {
                cursor = cursor.getNext();
            }
            cursor.setNext(node);
        }
    }

    protected void delete(String value) {
        Node cursor;
        if (null == this.head.getNext()) {
            System.out.println("no value found");
        } else {
            cursor = this.head;
            while (null != cursor.getNext()) {
                if (value.equals(cursor.getNext().getValue())) {
                    cursor.setNext(cursor.getNext().getNext());
                } else {
                    cursor = cursor.getNext();
                }
            }
        }
    }

    protected int search(String value) {
        Node cursor;
        int pos;
        if (isEmpty()) {
            System.out.println("no such element!");
            return -1;
        } else {
            cursor = this.head.getNext();
            pos = 1;
            while (null != cursor) {
                if (value.equals(cursor.getValue())) {
                    return pos;
                } else {
                    cursor = cursor.getNext();
                    pos++;
                }
            }
        }
        return -1;
    }

    protected void traverse() {
        Node cursor;
        if (isEmpty()) {
            System.out.println("no  element in this list");
        } else {
            cursor = this.head.getNext();
            while (null != cursor) {
                System.out.println(cursor.toString());
                cursor = cursor.getNext();
            }
        }
    }

    private boolean isEmpty() {
        return this.head.getNext() == null;
    }
}

class Node {
    private int num;
    private String value;
    private Node next;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node(int num, String value) {
        this.num = num;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "hashcode=" + num +
                ", value='" + value + '\'' +
                '}';
    }
}
