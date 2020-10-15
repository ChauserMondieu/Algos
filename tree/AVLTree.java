package tree;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,10,6,4,2,7};
        AVLTree tree = new AVLTree();
        for(int i=0; i<arr.length; i++){
           tree.add(new AVLNode(arr[i]));
        }
        System.out.println(tree.root.height());
        tree.inorder(tree.root);
        System.out.printf("left=%s, right=%s \n", tree.root.leftHeight(),tree.root.rightHeight());
    }

    public void add(AVLNode node) {
        if (isEmpty()) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void inorder(AVLNode node) {
        if (isEmpty()) {
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

    public boolean isEmpty() {
        return null == this.root;
    }
}

class AVLNode {
    private int num;
    private AVLNode left;
    private AVLNode right;

    public AVLNode getLeft() {
        return left;
    }

    public AVLNode getRight() {
        return right;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public AVLNode(int num) {
        this.num = num;
    }

    protected void add(AVLNode node) {
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
            System.out.println("already exist!");
        }
        if (rightHeight() - leftHeight() > 1) {
            if (right!= null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    protected int height() {
        return Math.max(this.left == null ? 0 : this.left.height(),
                this.right == null ? 0 : this.right.height()) + 1;
    }

    protected int leftHeight() {
        if (null == this.left) {
            return 0;
        } else {
            return this.left.height();
        }
    }

    protected int rightHeight() {
        if (null == this.right) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    protected void leftRotate() {
        AVLNode node = new AVLNode(this.num);
        node.left = this.left;
        node.right = this.right.left;
        this.num = this.right.num;
        this.right = this.right.right;
        this.left = node;
    }

    protected void rightRotate() {
        AVLNode node = new AVLNode(this.num);
        node.right = this.right;
        node.left = this.left.right;
        this.num = this.left.num;
        this.left = this.left.left;
        this.right = node;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "num=" + num +
                '}';
    }
}
