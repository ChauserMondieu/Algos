package tree;

public class Search {
    private TreeNode root;

    public Search(TreeNode root) {
        this.root = root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1, "a");
        TreeNode node2 = new TreeNode(2, "b");
        TreeNode node3 = new TreeNode(3, "c");
        TreeNode node4 = new TreeNode(4, "d");
        TreeNode node5 = new TreeNode(5, "e");
        Search tree = new Search(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
        TreeNode resNode = tree.preorderSearch(node1, 2);
        System.out.println(resNode.toString());
    }

    public TreeNode preorderSearch(TreeNode node, int no) {
        TreeNode resNode = null;
        if (node.getNo() == no) {
            return node;
        }
        if (null != node.getLeft()) {
            resNode = preorderSearch(node.getLeft(), no);
        }
        if (null != resNode) {
            return resNode;
        }
        if (null != node.getRight()) {
            resNode = preorderSearch(node.getRight(), no);
        }
        return resNode;
    }
}

