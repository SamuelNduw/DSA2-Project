import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        AVLTree avl = new AVLTree();
        RedBlackTree rbt = new RedBlackTree();
        BTree btree = new BTree(3); // minimum degree t = 3
        
        int[] elements = {7, 5, 9, 4, 6, 8, 13, 2};
        for (int el : elements) {
            bst.insert(el);
        }

        while (true) {
            System.out.println("\n===================TREE IMPLEMENTATION MENU===================");
            System.out.println("");
            System.out.println("1. Binary Search Tree (Inserting Node 3)");
            System.out.println("2. Binary Search Tree (Postorder transversal)");
            System.out.println("3. AVL (Insert the nine elements)");
            System.out.println("4. Red Black Tree (Insert the nine elements and display Postorder transversal)");
            System.out.println("5. B-trees (Implementation and search for key 8)");
            System.out.println("6. Exit");
            System.out.print("Enter choice: "); 

            int choice = scanner.nextInt();
            System.out.println("");
            switch (choice) {
                case 1:
                    System.out.println("");
                    System.out.println("Inserting 3 into BST...");
                    bst.insert(3);
                    System.out.println("Inserted.");
                    break;
                case 2:
                System.out.println("");
                    System.out.println("BST Postorder Traversal:");
                    bst.postOrder();
                    break;
                case 3:
                    for (int el : elements) {
                        avl.insert(el);
                    }
                    avl.insert(3);
                    System.out.println("");
                    System.out.println("Inserted all elements into AVL.");
                    break;
                case 4:
                    for (int el : elements) {
                        rbt.insert(el);
                    }
                    rbt.insert(3);
                    System.out.println("");
                    System.out.println("RBT Postorder Traversal:");
                    rbt.postOrder();
                    break;
                case 5:
                    for (int el : elements) {
                        btree.insert(el);
                    }
                    btree.insert(3);
                    System.out.println("");
                    System.out.println("Searching for key 8 in B-tree:");
                    btree.search(8);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("");
                    System.out.println("Invalid choice.");
            }
         
        }
    }
}

// Placeholder classes for AVL, RBT, and BTree
class BinarySearchTree {
    class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }
    Node root;

    void insert(int key) { root = insertRec(root, key); }
    Node insertRec(Node root, int key) {
        if (root == null) return new Node(key);
        if (key < root.key) root.left = insertRec(root.left, key);
        else root.right = insertRec(root.right, key);
        return root;
    }

    void postOrder() { postOrderRec(root); System.out.println(); }
    void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}

class AVLTree {
    // Simplified placeholder
    void insert(int key) {
        System.out.println("AVL insert: " + key);
        System.out.println("AVL (Found (The logic is incomplete, person responsible for this part has to add their logic)");
    }
}

class RedBlackTree {
    // Simplified placeholder
    void insert(int key) {
        System.out.println("RBT insert: " + key);
    }
    void postOrder() {
        System.out.println("RBT postorder traversal (Found (The logic is incomplete, person responsible for this part has to add their logic)");
    }
}

class BTree {
    // Simplified placeholder
    int t;
    BTree(int t) { this.t = t; }
    void insert(int key) {
        System.out.println("Inserted into B-tree: " + key);
    }
    void search(int key) {
        System.out.println("Search result for " + key + ": Found (The logic is incomplete, person responsible for this part has to add their logic)");
    }
}
