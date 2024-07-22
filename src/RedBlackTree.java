public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node {
        int key;
        Node left, right, parent;
        boolean color;

        Node(int key) {
            this.key = key;
            this.color = RED;
        }
    }

    private Node root;

    public RedBlackTree() {
        root = null;
    }

    private Node getParent(Node n) {
        return n == null ? null : n.parent;
    }

    private Node getGrandparent(Node n) {
        return getParent(getParent(n));
    }

    private Node getSibling(Node n) {
        Node p = getParent(n);
        if (p == null) return null;
        if (n == p.left)
            return p.right;
        else
            return p.left;
    }

    private Node getUncle(Node n) {
        Node p = getParent(n);
        return getSibling(p);
    }

    public void insert(int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            root.color = BLACK;
        } else {
            insertRec(root, node);
            fixInsert(node);
        }
    }

    private void insertRec(Node root, Node node) {
        if (node.key < root.key) {
            if (root.left == null) {
                root.left = node;
                node.parent = root;
            } else {
                insertRec(root.left, node);
            }
        } else if (node.key > root.key) {
            if (root.right == null) {
                root.right = node;
                node.parent = root;
            } else {
                insertRec(root.right, node);
            }
        }
    }

    private void rotateLeft(Node n) {
        Node r = n.right;
        replaceNode(n, r);
        n.right = r.left;
        if (r.left != null) r.left.parent = n;
        r.left = n;
        n.parent = r;
    }

    private void rotateRight(Node n) {
        Node l = n.left;
        replaceNode(n, l);
        n.left = l.right;
        if (l.right != null) l.right.parent = n;
        l.right = n;
        n.parent = l;
    }

    private void replaceNode(Node oldn, Node newn) {
        if (oldn.parent == null) {
            root = newn;
        } else {
            if (oldn == oldn.parent.left) {
                oldn.parent.left = newn;
            } else {
                oldn.parent.right = newn;
            }
        }
        if (newn != null) {
            newn.parent = oldn.parent;
        }
    }

    private void fixInsert(Node n) {
        while (n != null && n != root && n.parent.color == RED) {
            if (n.parent == getGrandparent(n).left) {
                Node uncle = getUncle(n);
                if (uncle != null && uncle.color == RED) {
                    n.parent.color = BLACK;
                    uncle.color = BLACK;
                    getGrandparent(n).color = RED;
                    n = getGrandparent(n);
                } else {
                    if (n == n.parent.right) {
                        n = n.parent;
                        rotateLeft(n);
                    }
                    n.parent.color = BLACK;
                    getGrandparent(n).color = RED;
                    rotateRight(getGrandparent(n));
                }
            } else {
                Node uncle = getUncle(n);
                if (uncle != null && uncle.color == RED) {
                    n.parent.color = BLACK;
                    uncle.color = BLACK;
                    getGrandparent(n).color = RED;
                    n = getGrandparent(n);
                } else {
                    if (n == n.parent.left) {
                        n = n.parent;
                        rotateRight(n);
                    }
                    n.parent.color = BLACK;
                    getGrandparent(n).color = RED;
                    rotateLeft(getGrandparent(n));
                }
            }
        }
        root.color = BLACK;
    }

    public boolean search(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    // Inorder traversal for verification
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
}

