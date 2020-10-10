package tree;

public class ThreadedBinaryTree {
    private TreeNode2 root;
    private TreeNode2 pre;

    public TreeNode2 getRoot() {
        return root;
    }

    public ThreadedBinaryTree(TreeNode2 root) {
        this.root = root;
        this.pre = null;
    }

    public static void main(String[] args) {
        TreeNode2 root = new TreeNode2(1, "tom");
        TreeNode2 node1 = new TreeNode2(1, "lucy");
        TreeNode2 node2 = new TreeNode2(3, "jack");
        TreeNode2 node3 = new TreeNode2(6, "mary");
        TreeNode2 node4 = new TreeNode2(8, "king");
        TreeNode2 node5 = new TreeNode2(14, "dim");

        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setLeft(node3);
        node2.setRight(node4);
        node3.setLeft(node5);

        tree.threading(tree.getRoot());
        TreeNode2 leftNode = node4.getLeft();
//        System.out.println(leftNode);
        tree.traverseThreading(tree.getRoot());
    }

    public void threading(TreeNode2 node) {
        if (null == node) {
            return;
        }
        threading(node.getLeft());
        if (null == node.getLeft()) {
            node.setLeft(pre);
            node.setLeftTag(1);
        }
        if (null != pre && null == pre.getRight()) {
            pre.setRight(node);
            pre.setRightTag(1);
        }
        pre = node;
        threading(node.getRight());
    }

    public void traverseThreading(TreeNode2 node) {
        while (null != node) {
            while (node.getLeftTag() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightTag() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
}

class TreeNode2 {
    private int no;
    private String name;
    private TreeNode2 left;
    private TreeNode2 right;
    private int leftTag;
    private int rightTag;

    public TreeNode2(int no, String name) {
        this.no = no;
        this.name = name;
        this.leftTag = 0;
        this.rightTag = 0;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode2 getLeft() {
        return left;
    }

    public void setLeft(TreeNode2 left) {
        this.left = left;
    }

    public TreeNode2 getRight() {
        return right;
    }

    public void setRight(TreeNode2 right) {
        this.right = right;
    }

    public int getLeftTag() {
        return leftTag;
    }

    public void setLeftTag(int leftTag) {
        this.leftTag = leftTag;
    }

    public int getRightTag() {
        return rightTag;
    }

    public void setRightTag(int rightTag) {
        this.rightTag = rightTag;
    }

    @Override
    public String toString() {
        return "TreeNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

