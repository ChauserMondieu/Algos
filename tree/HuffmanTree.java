package tree;

import java.util.*;

public class HuffmanTree {
    private List<Node> huff = new ArrayList();
    private int[] arr;
    private int size;
    private Node root;

    public Node getRoot() {
        return root;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HuffmanTree(int size) {
        this.size = size;
        this.arr = new int[this.size];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.size; i++) {
            arr[i] = rand.nextInt(10);
        }
    }

    public HuffmanTree() {
        this(5);
    }

    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree();
        System.out.println(Arrays.toString(tree.getArr()));
        tree.generate(tree.getArr());
        tree.preorderTraverse(tree.getRoot());
    }

    public void generate(int[] arr) {
        for (int i = 0; i < this.size; i++) {
            huff.add(new Node(arr[i]));
        }
        Collections.sort(huff);
        while (huff.size() > 1) {
            Collections.sort(huff);
            Node left = huff.get(0);
            Node right = huff.get(1);
            Node parent = new Node(left.getNum() + right.getNum());
            parent.setLeft(left);
            parent.setRight(right);
            huff.remove(left);
            huff.remove(right);
            huff.add(parent);
        }
        this.root = huff.get(0);
    }

    public void preorderTraverse(Node node) {
        if (null == root) {
            System.out.println("empty tree!");
        }
        if (null != node.getLeft()) {
            preorderTraverse(node.getLeft());
        }
        System.out.println(node);
        if (null != node.getRight()) {
            preorderTraverse(node.getRight());
        }
    }
}

class Node implements Comparable<Node> {
    private int num;
    private Node left;
    private Node right;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public int compareTo(Node o) {
        return this.getNum() - o.getNum();
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}

class test{
    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>(5);
        for(int i=0; i<5;i++){
            test.add(i);
        }
        System.out.println(test.get(1));
        test.remove(1);
        System.out.println(test.get(1));
    }
}
