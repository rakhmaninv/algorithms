package hw04;

public class RedBlackTree {
    Node root;
    private class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }
    public void add(int value) {
        if (root == null){
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
        }
        else {
            add(root, value);
            root = recalc(root);
            root.color = Color.BLACK;
        }
    }

    private void add(Node node, int value){
            if (node.value > value){
                if (node.left != null){
                    add(node.left, value);
                    node.left = recalc(node.left);
                }
                else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;

                }
            } else {
                if (node.right != null){
                    add(node.right, value);
                    node.right = recalc(node.right);
                }
                else {
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                }
            }
            return;
    }
    private enum Color{
        BLACK,
        RED
    }


    private Node recalc(Node node) {
        Node res = node;
        boolean need_recalc;
        do {
            need_recalc = false;

            // если справа дочерняя красная, то правый поворот
            if (res.right != null && res.right.color == Color.RED &&
                    (res.left == null || res.left.color == Color.BLACK)) {
                res = rightTurn(res);
                need_recalc = true;
            }
            // если левая дочерняя красная и родитель красный, то правый поворот
            if (res.left != null && res.left.color == Color.RED &&
            res.left.left != null && res.left.left.color == Color.RED) {
                res = leftTurn(res);
                need_recalc = true;
            }
            // если два дочерних красные, то свайп цвета
            if (res.left != null && res.right != null &&
                    res.left.color == Color.RED && res.right.color == Color.RED) {
                swap(res);
                need_recalc = true;
            }
        } while (need_recalc);
        return res;
    }
    private Node leftTurn (Node parent){
        Node left = parent.left;
        Node temp = left.right;
        left.right = parent;
        parent.left = temp;
        left.color = parent.color;
        parent.color = Color.RED;
        return left;
    }
    private Node rightTurn (Node parent){
        Node right = parent.right;
        Node temp = right.left;
        right.left = parent;
        parent.right = temp;
        right.color = parent.color;
        parent.color = Color.RED;
        return right;
    }
    private void swap(Node parent){
        parent.color = Color.RED;
        parent.left.color = Color.BLACK;
        parent.right.color = Color.BLACK;

    }

    public void printTree(Node node) {
        if (node != null) {
            if (node.color == Color.BLACK) {
                System.out.printf(" %d-B" , node.value);
            }
            else {
                System.out.printf(" %d-R ", node.value);
            }
            printTree(node.left);
            printTree(node.right);
        } else System.out.print("");
}

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        for (int i = 1; i <= 6 ; i++) {
            tree.add(i);
        }
        tree.printTree(tree.root);
    }
}