import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        Scanner sc;
        while (true) {
            sc = new Scanner(System.in);
            System.out.println();
            System.out.println("===================TREE IMPLEMENTATION MENU===================");
            System.out.println("1. Binary Search Tree (Inserting Node 3)");
            System.out.println("2. Binary Search Tree (Postorder traversal)");
            System.out.println("3. AVL (Insert the nine elements)");
            System.out.println("4. Red Black Tree (Insert the nine elements and display Postorder traversal)");
            System.out.println("5. B-trees (Implementation and search for key 8)");
            System.out.println("6. Exit");
            System.out.println();

            int option = sc.nextInt();

            switch (option) {
                case 1 -> {
                    BST root = new BST();
                    root.insert(7);
                    root.insert(5);
                    root.insert(9);
                    root.insert(4);
                    root.insert(6);
                    root.insert(8);
                    root.insert(13);
                    root.insert(2);
                    root.insert(3);

                    root.display();
                }
                case 2 -> {
                    BST root = new BST();
                    root.insert(7);
                    root.insert(5);
                    root.insert(9);
                    root.insert(4);
                    root.insert(6);
                    root.insert(8);
                    root.insert(13);
                    root.insert(2);
                    root.insert(3);

                    System.out.println("Postorder traversal");
                    root.postOrder();
                    System.out.println();
                    root.display();
                }
                case 3 -> {
                    AVLTree tree = new AVLTree();

                    tree.insert(7);
                    tree.insert(5);
                    tree.insert(9);
                    tree.insert(4);
                    tree.insert(6);
                    tree.insert(8);
                    tree.insert(13);
                    tree.insert(2);
                    tree.insert(3);

                    tree.display();
                }
                case 4 -> {
                    RedBlackTree tree = new RedBlackTree();
                    tree.insert(7);
                    tree.insert(5);
                    tree.insert(9);
                    tree.insert(4);
                    tree.insert(6);
                    tree.insert(8);
                    tree.insert(13);
                    tree.insert(2);
                    tree.insert(3);

                    System.out.println("Postorder traversal");
                    tree.postorder();
                    System.out.println();
                    tree.display();
                }
                case 5 -> {
                    BTree tree = new BTree(2);
                    tree.insert(7);
                    tree.insert(5);
                    tree.insert(9);
                    tree.insert(4);
                    tree.insert(6);
                    tree.insert(8);
                    tree.insert(13);
                    tree.insert(2);
                    tree.insert(3);

                    tree.display();

                    System.out.println();

                    // Search for key 8
                    int keyToSearch = 8;
                    BTreeNode result = tree.search(keyToSearch);

                    if (result != null) {
                        System.out.println("Key " + keyToSearch + " found in the B-Tree.");
                    } else {
                        System.out.println("Key " + keyToSearch + " not found in the B-Tree.");
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }
}