package tree;

import java.util.Arrays;
import java.util.Random;

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return root;
    }

    public BST() {
        this.root = null;
    }

    public static void main(String[] args) {
        int[] arr = new int[5];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            arr[i] = rand.nextInt((50));
        }
//        System.out.println(Arrays.toString(arr));
//        BST tree = new BST();
//        for (int i = 0; i < 5; i++) {
//            tree.add(new BSTNode(arr[i]));
//        }
//        tree.inorder(tree.getRoot());
        BST tree = new BST();
        int[] arr2 = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr2.length; i++) {
            tree.add(new BSTNode(arr2[i]));
        }
        tree.delete(3);
        tree.inorder(tree.getRoot());

    }

    public void add(BSTNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void inorder(BSTNode node) {
        if (null == node) {
            System.out.println("empty tree!");
        } else {
            if (null != node.getLeft()) {
                inorder(node.getLeft());
            }
            System.out.println(node.toString());
            if (null != node.getRight()) {
                inorder(node.getRight());
            }
        }
    }

    private BSTNode current(int value) {
        if (null == this.root) {
            return null;
        } else {
            return this.root.currentNode(value);
        }
    }

    private BSTNode parent(int value) {
        if (null == this.root) {
            return null;
        } else {
            return this.root.parentNode(value);
        }
    }

    private int delRightMin(BSTNode node) {
        while (null != node.getLeft()) {
            node = node.getLeft();
        }
        delete(node.getNum());
        return node.getNum();
    }

    public void delete(int value) {
        if (null == this.root) {
            return;
        } else {
            BSTNode current = current(value);
            if (null == current) {
                return;
            }
            if (null == this.root.getLeft() && null == this.root.getRight()) {
                root = null;
                return;
            }
            BSTNode parent = parent(value);
            //1.leaf node
            if (null == current.getLeft() && null == current.getRight()) {
                if (null != parent.getLeft() && parent.getLeft() == current) {
                    parent.setLeft(null);
                }
                if (null != parent.getRight() && parent.getRight() == current) {
                    parent.setRight(null);
                }
            }
            //3. has both left and right leaf node
            else if (null != current.getLeft() && null != current.getRight()) {
                int res = delRightMin(current.getRight());
                current.setNum(res);
            }
            //2. has only one leaf node
            else{
                if (null != current.getLeft()) {
                    if (current == parent.getLeft()) {
                        parent.setLeft(current.getLeft());
                    }
                    if (current == parent.getRight()) {
                        parent.setRight(current.getLeft());
                    }
                }
                if (null != current.getRight()) {
                    if (current == parent.getLeft()) {
                        parent.setLeft(current.getRight());
                    }
                    if (current == parent.getRight()) {
                        parent.setRight(current.getRight());
                    }
                }
            }
        }
    }
}

class BSTNode {
    private int num;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int num) {
        this.num = num;
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

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }

    public void add(BSTNode node) {
        if (node.getNum() < this.getNum()) {
            if (null == this.getLeft()) {
                this.setLeft(node);
                return;
            }
            this.getLeft().add(node);
        }
        if (node.getNum() > this.getNum()) {
            if (null == this.getRight()) {
                this.setRight(node);
                return;
            }
            this.getRight().add(node);
        }
        if (node.getNum() == this.getNum()) {
            return;
        }
    }

    protected BSTNode currentNode(int value) {
        if (null != this.getLeft() && this.getNum() > value) {
            return this.getLeft().currentNode(value);
        } else if (null != this.getRight() && this.getNum() < value) {
            return this.getRight().currentNode(value);
        } else if (this.getNum() == value) {
            return this;
        } else {
            return null;
        }
    }

    protected BSTNode parentNode(int value) {
        if ((null != this.getLeft() && this.getLeft().getNum() == value) ||
                (null != this.getRight() && this.getRight().getNum() == value)) {
            return this;
        } else {
            if (null != this.getLeft() && this.getNum() > value) {
                return this.getLeft().parentNode(value);
            } else if (null != this.getRight() && this.getNum() < value) {
                return this.getRight().parentNode(value);
            } else {
                return null;
            }
        }
    }
}
