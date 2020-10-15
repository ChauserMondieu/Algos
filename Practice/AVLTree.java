package Practice;

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

    public boolean isEmpty() {
        return null == root;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,10,6,4,2,7};
        Practice.AVLTree tree = new Practice.AVLTree();
        for(int i=0; i<arr.length; i++){
            tree.add(new Practice.AVLNode(arr[i]));
        }
        System.out.println(tree.root.height());
        tree.inorder(tree.root);
        System.out.printf("left=%s, right=%s \n", tree.root.leftHeight(),tree.root.rightHeight());
    }

    public void add(AVLNode node) {
        if (isEmpty()) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void inorder(AVLNode node) {
        if (isEmpty()) {
            System.out.println("Empty tree!");
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

    public AVLNode search(int value) {
        if (isEmpty()) {
            System.out.println("Empty tree!");
            return null;
        } else {
            return root.search(value);
        }
    }

    private int delmaxLeft(AVLNode node) {
        while (null != node.getRight()) {
            node = node.getRight();
        }
        delete(node.getNum());
        return node.getNum();
    }

    public void delete(int value) {
        if (isEmpty()) {
            System.out.println("empty tree!");
        } else {
            AVLNode current = root.search(value);
            if (null == current) {
                return;
            } else if (null == current.getLeft() && null == current.getRight()) {
                root = null;
            } else {
                AVLNode parent = root.searchParent(value);
                if (null == current.getLeft() && null == current.getRight()) {
                    if (current == parent.getLeft()) {
                        parent.setLeft(null);
                    } else if (current == parent.getRight()) {
                        parent.setRight(null);
                    }
                } else if (null != current.getLeft() && null != current.getRight()) {
                    int res = delmaxLeft(parent.getLeft());
                    current.setNum(res);
                } else {
                    if (null != current.getLeft()) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getLeft());
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getLeft());
                            }
                        } else {
                            root.setLeft(null);
                        }
                    } else if (null != current.getRight()) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getRight());
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getRight());
                            }
                        } else {
                            root.setRight(null);
                        }
                    }
                }
            }
        }
    }


}

class AVLNode {
    private int num;
    private AVLNode left;
    private AVLNode right;

    public AVLNode(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "num=" + num +
                '}';
    }

    protected void add(AVLNode node) {
        if (node.num < num) {
            if (null == left) {
                left = node;
            } else {
                left.add(node);
            }
        } else if (node.num > num) {
            if (null == right) {
                right = node;
            } else {
                right.add(node);
            }
        } else {
            System.out.println("already exist!");
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left.leftHeight() < left.rightHeight()) {
                left.leftRotate();
            }
            rightRotate();
        } else if (rightHeight() - leftHeight() > 1) {
            if (right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
        }
    }

    protected AVLNode search(int value) {
        if (null != left && value < num) {
            return left.search(value);
        } else if (null != right && value > num) {
            return right.search(value);
        } else if (value == num) {
            return this;
        } else {
            return null;
        }
    }

    protected AVLNode searchParent(int value) {
        if ((null != left && left.num == num) ||
                (null != right && right.num == num)) {
            return this;
        } else if (left != null && num < value) {
            return left.searchParent(value);
        } else if (right != null && num > value) {
            return right.searchParent(value);
        } else {
            return null;
        }
    }

    protected int height() {
        return Math.max(left == null ? 0 : left.height(),
                right == null ? 0 : right.height()) + 1;
    }

    protected int leftHeight() {
        if (null == left) {
            return 0;
        } else {
            return left.height();
        }
    }

    protected int rightHeight() {
        if (null == right) {
            return 0;
        } else {
            return right.height();
        }
    }

    protected void leftRotate() {
        AVLNode node = new AVLNode(num);
        node.left = left;
        node.right = right.left;
        num = right.num;
        right = right.right;
        left = node;
    }

    protected void rightRotate() {
        AVLNode node = new AVLNode(num);
        node.right = right;
        node.left = left.right;
        num = left.num;
        left = left.left;
        right = node;
    }
}
