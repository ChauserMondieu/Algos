package tree;

public class Traverse {
    private int height;
    private int size;
    private TreeNode root;

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public TreeNode getRoot() {
        return root;
    }

    public Traverse(TreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        Traverse tree = new Traverse(new TreeNode(1, "a"));
        tree.preorderTraverse(tree.getRoot());
    }

    public void preorderTraverse(TreeNode node){
        System.out.println(node.toString());
        if(null!= node.getLeft()){
            preorderTraverse(node.getLeft());
        }
        if(null!= node.getRight()){
            preorderTraverse(node.getRight());
        }
    }
}

class TreeNode{
    private int no;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
