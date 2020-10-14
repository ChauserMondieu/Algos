package Practice;

public class ThreadingTree {
    private ThreadingNode root;
    private ThreadingNode pre;

    public ThreadingTree() {
    }

    public boolean isEmpty() {
        return null == this.root;
    }

    public void add(int num) {
        ThreadingNode node = new ThreadingNode(num);
        add(node);
    }

    public void add(ThreadingNode node) {
        if (isEmpty()) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 5, 4, 7, 8, 2, 6, 13, 18, 16, 12, 21};
        ThreadingTree tree = new ThreadingTree();
        for (int i : arr) {
            tree.add(new ThreadingNode(i));
        }
        tree.threading(tree.root);
        tree.traverse(tree.root);
        tree.delete(10);
        System.out.println("===============");
        tree.traverse(tree.root);
//        System.out.println("===============");
//        tree.inorder(tree.root);
    }

    public void threading(ThreadingNode node) {
        if (null != node) {
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
    }

    public void inorder(ThreadingNode node) {
        if (null != node.getLeft()) {
            inorder(node.getLeft());
        }
        System.out.println(node.toString());
        if (null != node.getRight()) {
            inorder(node.getRight());
        }
    }

    public void traverse(ThreadingNode node) {
        while (null != node) {
            while (node.getLeftTag() == 0) {
                node = node.getLeft();
            }
            System.out.println(node.toString());
            while (node.getRightTag() == 1) {
                node = node.getRight();
                System.out.println(node.toString());
            }
            node = node.getRight();
        }
    }

    public int delLeftMax(ThreadingNode node) {
        while (node.getRightTag() == 0) {
            node = node.getRight();
        }
        delete(node.getNum());
        return node.getNum();
    }

    public ThreadingNode search(int num) {
        if (isEmpty()) {
            return null;
        } else {
            return this.root.search(num);
        }
    }

    public ThreadingNode searchParent(int num) {
        if (isEmpty()) {
            return null;
        } else {
            return this.root.searchParent(num);
        }
    }

    public boolean delete(int num) {
        ThreadingNode current;
        if (isEmpty()) {
            return false;
        } else {
            current = search(num);
            if (null == current) {
                return false;
            } else if (null == current.getLeft() && null == current.getRight()) {
                this.root = null;
                return true;
            } else {
                ThreadingNode parent = searchParent(num);
                if ((current.getRightTag() == 1 && current.getLeftTag() == 1) || null == current.getRight()) {
                    if (current == parent.getLeft()) {
                        parent.setLeft(null);
                        parent.setLeftTag(1);
                        parent.setLeft(current.getLeft());
                        return true;
                    } else if (current == parent.getRight()) {
                        parent.setRight(null);
                        parent.setRightTag(1);
                        parent.setRight(current.getRight());
                        return true;
                    } else {
                        return false;
                    }
                } else if (current.getLeftTag() == 0 && current.getRightTag() == 0) {
                    int value = delLeftMax(current.getLeft());
                    current.setNum(value);
                    return true;
                } else {
                    if (current.getLeftTag() == 0) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getLeft());
                                current.getLeft().setRight(parent);
                                return true;
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getLeft());
                                current.getLeft().setRight(parent);
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            this.root = current.getLeft();
                            return true;
                        }
                    } else if (current.getRightTag() == 0 && null != current.getRight()) {
                        if (null != parent) {
                            if (current == parent.getLeft()) {
                                parent.setLeft(current.getRight());
                                current.getRight().setLeft(parent);
                                return true;
                            } else if (current == parent.getRight()) {
                                parent.setRight(current.getRight());
                                current.getRight().setLeft(parent);
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            this.root = current.getRight();
                            return true;
                        }
                    } else {
                        return false;
                    }

                }
            }
        }
    }
}

class ThreadingNode {
    private int num;
    private ThreadingNode left;
    private ThreadingNode right;
    private int leftTag;
    private int rightTag;

    public ThreadingNode(int num) {
        this.num = num;
        this.leftTag = 0;
        this.rightTag = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ThreadingNode getLeft() {
        return left;
    }

    public void setLeft(ThreadingNode left) {
        this.left = left;
    }

    public ThreadingNode getRight() {
        return right;
    }

    public void setRight(ThreadingNode right) {
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
        return "ThreadingNode{" +
                "num=" + num +
                '}';
    }

    protected void add(ThreadingNode node) {
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
    }

    protected ThreadingNode search(int num) {
        if (num < this.num) {
            if (this.leftTag == 1) {
                return null;
            } else {
                return this.left.search(num);
            }
        } else if (num > this.num) {
            if (this.rightTag == 1) {
                return null;
            } else {
                return this.right.search(num);
            }
        } else {
            return this;
        }
    }

    protected ThreadingNode searchParent(int num) {
        if ((this.leftTag == 0 && this.left.num == num) ||
                (this.rightTag == 0 && this.right.num == num)) {
            return this;
        } else {
            if (this.leftTag == 0 && this.num > num) {
                return this.left.searchParent(num);
            } else if (this.rightTag == 0 && this.num < num) {
                return this.right.searchParent(num);
            } else {
                return null;
            }
        }
    }


}
