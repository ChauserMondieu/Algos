package tree.practice;

public class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public TreeNode deleteNode(int no) {
        TreeNode res = null;
        if (null != this.root) {
            if (root.getNo() == no) {
                this.root = null;
            } else {
                res = delete(no, root);
            }
            return res;        }
        System.out.println("this tree is already empty!");
        return null;
    }

    public TreeNode delete(int no, TreeNode node) {
        if (null != node.getLeft() && node.getLeft().getNo() == no) {
            node.setLeft(null);
            return node.getLeft();
        }
        if (null != node.getRight() && node.getRight().getNo() == no) {
            node.setRight(null);
            return node.getRight();
        }
        if (null != node.getLeft()) {
            delete(no, node.getLeft());
        }
        if (null != node.getRight()) {
            delete(no, node.getRight());
        }
        return null;
    }

}

class TreeNode {
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
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

}


