package Part2;

// BST.java
// Class for the Binary Search Tree implementation.
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
}
