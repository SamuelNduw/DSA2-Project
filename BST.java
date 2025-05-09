// Class for the Binary Search Tree implementation.
import java.util.*;
public class BST {
    BSTNode root;

    // Constructor initializes an empty BST.
    public BST() {
        root = null;
    }

    // Public method to insert a value into the BST.
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive helper method for insertion.
    private BSTNode insertRec(BSTNode root, int value) {
        // If the tree is empty, return a new node.
        if (root == null) {
            root = new BSTNode(value);
            return root;
        }
        // Recur down the tree: go left if value is less, right if greater.
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        // Return the unchanged node pointer.
        return root;
    }

    // Public method to perform an in-order traversal of the BST.
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    // Recursive helper method for in-order traversal.
    private void inOrderRec(BSTNode root) {
        if (root != null) {
            inOrderRec(root.left);              // Traverse left subtree.
            System.out.print(root.value + " "); // Visit node.
            inOrderRec(root.right);             // Traverse right subtree.
        }
    }

    // Public method to perform post-order traversal of the BST.
    public void postOrder() {
        postOrderRec(root);
        System.out.println();
    }

    // Recursive helper method for post-order traversal.
    private void postOrderRec(BSTNode root) {
        if (root != null) {
            postOrderRec(root.left);              // Traverse left subtree.
            postOrderRec(root.right);             // Traverse right subtree.
            System.out.print(root.value + " ");   // Visit node last.
        }
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        List<BSTNode> level = new ArrayList<>();
        List<BSTNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (BSTNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = Integer.toString(n.value);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;
            lines.add(line);

            List<BSTNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // Split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // Lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // Print line of numbers
            for (String f : line) {
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

}