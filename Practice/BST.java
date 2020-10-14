package Practice;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class BST {
    private BSTNode root;

    public static void main(String[] args) {
        BST tree = new BST();
//        int[] arr = new int[]{10,5,4,7,13,18,12};
//        String[] sarr = new String[]{"s","d","t","y","u","i","q"};
//        for(int i=0; i<arr.length;i++){
//            tree.add(new BSTNode(arr[i], sarr[i]));
//        }
//        tree.inorder(tree.root);
//        tree.delete(10);
//        tree.inorder(tree.root);
        Scanner scanner = new Scanner(System.in);
        char key;
        boolean flag = true;
        while(flag){
            System.out.println("a - add");
            System.out.println("d - delete");
            System.out.println("s - show");
            System.out.println("p - search");
            System.out.println("e - exit");
            key = scanner.next().charAt(0);
            switch(key){
                case 'a':
                    try{
                        System.out.println("please input number:");
                        int num = scanner.nextInt();
                        System.out.println("please input value:");
                        String value = scanner.next();
                        BSTNode node = new BSTNode(num, value);
                        tree.add(node);
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                case 'd':
                    try{
                        System.out.println("please input number:");
                        int del = scanner.nextInt();
                        tree.delete(del);
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                case 's':
                    try{
                        tree.inorder(tree.root);
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                case 'p':
                    try{
                        System.out.println("please input number");
                        int ser = scanner.nextInt();
                        BSTNode serNode = tree.search(ser);
                        System.out.println(serNode.toString());
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                case 'e':
                    flag = false;
                    scanner.close();
                    break;
            }
        }
        System.out.println("program closed");
    }

    public BST() {
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void add(BSTNode node) {
        if (isEmpty()) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public BSTNode search(int num) {
        if (isEmpty()) {
            return null;
        } else {
            return this.root.search(num);
        }
    }

    private BSTNode searchParent(int num) {
        if (isEmpty()) {
            return null;
        } else {
            return this.root.searchParent(num);
        }
    }

    private Map<String, Object> delLeftMax(BSTNode node) {
        Map<String, Object> res = new HashMap<>();
        while (null != node.getRight()) {
            node = node.getRight();
        }
        delete(node.getNum());
        res.put("key1", node.getNum());
        res.put("key2", node.getValue());
        return res;
    }

    public void delete(int num) {
        if (isEmpty()) {
            System.out.println("empty tree!");
        } else {
            BSTNode current = search(num);
            if (null == current) {
                return;
            } else if (null == this.root.getLeft() && null == this.root.getRight()) {
                this.root = null;
            } else {
                BSTNode parent = searchParent(num);
                if (null == current.getLeft() && null == current.getRight()) { //1
                    if (current == parent.getLeft()) {
                        parent.setLeft(null);
                    } else if (current == parent.getRight()) {
                        parent.setRight(null);
                    }
                } else if (null != current.getLeft() && null != current.getRight()) { //2
                    Map<String, Object> res = delLeftMax(current.getLeft());
                    current.setNum((Integer) res.get("key1"));
                    current.setValue((String) res.get("key2"));
                } else { //3
                    if (null != current.getLeft()) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getLeft());
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getLeft());
                            } else {
                                return;
                            }
                        } else {
                            this.root = current.getLeft();
                        }
                    } else if (null != current.getRight()) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getRight());
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getRight());
                            } else {
                                return;
                            }
                        } else {
                            this.root = current.getRight();
                        }
                    }
                }
            }
        }
    }

    public void inorder(BSTNode node){
        if(isEmpty()){
            System.out.println("Empty tree!");
        }else{
            if(null!=node.getLeft()){
                inorder(node.getLeft());
            }
            System.out.println(node.toString());
            if(null!= node.getRight()){
                inorder(node.getRight());
            }
        }
    }

}

class BSTNode {
    private int num;
    private String value;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int num, String value) {
        this.num = num;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    protected BSTNode search(int num) {
        if (num < this.num) {
            if (null != this.left) {
                return this.left.search(num);
            } else {
                return null;
            }
        } else if (num > this.num) {
            if (null != this.right) {
                return this.right.search(num);
            } else {
                return null;
            }
        } else {
            return this;
        }
    }

    protected BSTNode searchParent(int num) {
        if ((null != this.left && num == this.left.num) ||
                (null != this.right && num == this.right.num)) {
            return this;
        } else {
            if (null != this.left) {
                return this.left.searchParent(num);
            } else if (null != this.right) {
                return this.right.searchParent(num);
            } else {
                return null;
            }
        }
    }

    protected void add(BSTNode node) {
        if (node.num < this.num) {
            if (null == this.left) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else if (node.num > this.num) {
            if (null == this.right) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        } else {
            System.out.println("node already exist!");
        }
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "num=" + num +
                ", value='" + value + '\'' +
                '}';
    }
}
