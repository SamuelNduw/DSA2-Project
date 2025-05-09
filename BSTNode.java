// Class representing a node in a Binary Search Tree.
public class BSTNode {
    int value;
    BSTNode left, right;

    // Constructor to initialize the node with a given value.
    public BSTNode(int value) {
        this.value = value;
        left = right = null;
    }
}