package Part2;

// Main.java
// Menu-driven program to perform BST operations.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BST bst = new BST();

        // Insert the eight given elements into the BST.
        int[] elements = {7, 5, 9, 4, 6, 8, 13, 2};
        for (int element : elements) {
            bst.insert(element);
        }

        // Display the initial tree (in-order traversal).
        System.out.println("Initial BST (In-order Traversal):");
        bst.inOrder();

        // Display the menu.
        System.out.println("===================TREE IMPLEMENTATION MENU===================");
        System.out.println("1. Binary Search Tree (Insert Node 3)");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Option 1: Insert node with value 3 into the BST.
                System.out.println("\nInserting node with value 3 into the BST...");
                bst.insert(3);
                // Display the updated BST after insertion.
                System.out.println("Updated BST (In-order Traversal):");
                bst.inOrder();
                break;
            case 2:
                System.out.println("Exiting program.");
                break;
            default:
                System.out.println("Invalid option. Exiting.");
        }

        scanner.close();
    }
}
