package io.github.jiangdequan;

class Node{
    int key, h;
    boolean color;
    Node left, right,parent;

    public Node(int item){
        key = item;
        left=right=null;
        h = 1;
    }
 }

public class RedBlackTree {
        Node root;
        Node parent = null;
    
         static final boolean RED = false;
         static final boolean BLACK = true;
    
        // searching for the node
        Node searchNode(int key){
            Node node = root;
            while (node!=null){
                if (key==node.key){
                    return node;
                } else if (key<node.key){
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return null;
        }
    
        // inserting the node
         void insertNode(int key){
            Node node = root;
            Node parent = null;
            // traverse the tree to left / right depending on the key
             while (node!=null){
                 parent = node;
                 if (key < node.key){
                     node = node.left;
                 } else if (key > node.key){
                     node = node.right;
                 } else {
                     throw new IllegalArgumentException("BST already contains a node with key " + key);
                 }
             }
    
             // insert new node
             Node newNode = new Node(key);
             newNode.color = RED;
             if (parent==null){
                 root = newNode;
             } else if (key < parent.key) {
                 parent.left = newNode;
             } else {
                 parent.right = newNode;
             }
             newNode.parent = parent;
              fRBPAI(newNode);
         }
    
         // fix Red Black Properties After Insert
         void fRBPAI(Node node){
            parent = node.parent;
    
            // case 1: Parent is null, we've reached the root, the end of the recursion
             if (parent==null){
                 node.color = BLACK; // enforce black roots (rule 2)
                 return;
             }
             // Parent is black --> nothing to do
             if (parent.color == BLACK){
                 return;
             }
             // From here on, parent is red
             Node grandparent = parent.parent;
             /* Case 2:
             Not having a grandparent means that parent is the root. If we enforce black roots
             (rule 2), grandparent will never be null, and the following if-then block can be
             removed.
             * */
             if (grandparent==null){
                 /* As this method is only called on red nodes (either on newly inserted ones - or -
                 recursively on red grandparents), all we have to do is to recolor the root black.
                 * */
                 parent.color = BLACK;
                 return;
             }
             // Get the uncle (maybe null/nil, in which case its color is BLACK)
             Node uncle = getUncle(parent);
    
             // Case 3: Uncle is red -> recolor parent, grandparent and uncle
             if (uncle!=null && uncle.color==RED){
                 parent.color = BLACK;
                 grandparent.color = RED;
                 uncle.color = BLACK;
                 /* Call recursively for grandparent, which is now red.
                 It might be root or have a red parent, in which case we need to fix more...
                 * */
                 fRBPAI(grandparent);
             }
             // parent is left child of grandparent
             else if (parent==grandparent.left) {
                 // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
                 if (node == parent.right){
                     rotateLeft(parent);
    
                     // Let "parent" point to the new root node of the rotated sub-tree.
                     // It will be recolored in the next step, which we're going to fall-through to.
                     parent = node;
                 }
                 // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
                  rotateRight(grandparent);
    
                 // Recolor original parent and grandparent
                 parent.color = BLACK;
                 grandparent.color = RED;
             }
             // Parent is right child of grandparent
             else {
                 // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
                 if (node==parent.left){
                      rotateRight(parent);
    
                     // Let "parent" point to the new root node of the rotated sub-tree.
                     // It will be recolored in the next step, which we're going to fall-through to.
                     parent = node;
                 }
                 // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
                  rotateLeft(grandparent);
    
                 // Recolor original parent and grandparent
                 parent.color = BLACK;
                 grandparent.color = RED;
             }
         }
    
         // get uncle
         Node getUncle(Node parent){
            Node grandparent = parent.parent;
            if (grandparent.left==parent){
                return grandparent.left;
            } else if (grandparent.right==parent) {
                return grandparent.right;
            } else {
                throw new IllegalStateException("Parent is not a child of its grandparent");
            }
         }
    
         // deleting a node
         void deleteNode(int key){
            Node node = root;
    
            // find the node to delete
             while (node!=null && node.key!=key){
                 // Traverse the tree to the left or right depending on the key
                 if (key < node.key){
                     node = node.left;
                 } else {
                     node = node.right;
                 }
             }
    
             // node not found
             if (node==null){
                 return;
             }
    
             /* At this point, "node" is the node to be deleted
             In this variable, we'll store the node at which we're going to start to fix the R-B
             properties after deleting a node.
             * */
             Node moveUpNode;
             boolean deletedNodeColor;
    
             // Node has zero or one child
             if (node.left==null && node.right == null){
                 moveUpNode = dNWZOOC(node);
                 deletedNodeColor = node.color;
             }
             // Node has two children
             else {
                 // Find minimum node of right subtree ("inorder successor" of current node)
                  Node inOrderSuccessor = findMinimum(node.right);
    
                 // Copy inorder successor's data to current node (keep its color!)
                 node.key = inOrderSuccessor.key;
    
                 // Delete inorder successor just as we would delete a node with 0 or 1 child
                  moveUpNode = dNWZOOC(inOrderSuccessor);
                  deletedNodeColor = inOrderSuccessor.color;
             }
             if (deletedNodeColor == BLACK){
                 fRBPAD(moveUpNode);
    
                 // Remove the temporary NIL node
                 if (moveUpNode.getClass() == NilNode.class){
                     rPC(moveUpNode.parent,moveUpNode,null);
                 }
             }
         }
    
         // delete Node With Zero Or One Child
         Node dNWZOOC(Node node){
            // Node has ONLY a left child --> replace by its left child
             if (node.left!=null){
                 rPC(node.parent,node,node.left);
                 return  node.left; // moved-up node
             }
             // Node has ONLY a right child --> replace by its right child
             else if (node.right!=null) {
                 rPC(node.parent,node,node.right);
                 return  node.right; // moved-up node
             }
             /* Node has no children -->
             * node is red --> just remove it
             * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)*/
             else {
                 Node newChild = node.color == BLACK ? new NilNode() : null;
                 rPC(node.parent, node, newChild);
                 return newChild;
             }
         }
    
         // find Minimum
         Node findMinimum(Node node){
             while (node.left!=null){
                 node = node.left;
             }
             return node;
         }
    
         // fix Red Black Properties After Delete
         void fRBPAD(Node node){
            // Case 1: Examined node is root, end of recursion
             if (node == root) {
                 node.color=BLACK; // enforce black roots (rule 2)
                 return;
             }
    
             Node sibling = getSibling(node);
    
             // Case 2: Red sibling
             if (sibling.color == RED){
                 hRS(node,sibling);
                 sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
             }
    
             // Cases 3+4: Black sibling with two black children
             if (isBlack(sibling.left) && isBlack(sibling.right)){
                 sibling.color =RED;
    
                 // Case 3: Black sibling with two black children + red parent
                 if (node.parent.color == RED){
                     node.parent.color =BLACK;
                 }
                 // Case 4: Black sibling with two black children + black parent
                 else {
                     fRBPAD(node.parent);
                 }
             }
             // Case 5+6: Black sibling with at least one red child
             else {
                 hBSWALORC(node, sibling);
             }
         }
    
         // handle Red Sibling
         void hRS(Node node, Node sibling){
            // recolor
             sibling.color = BLACK;
             node.parent.color = RED;
         }
    
    
         // handle Black Sibling With At Least One Red Child
         void hBSWALORC(Node node, Node sibling){
            boolean nodeIsLeftChild = node == node.parent.left;
    
            /* Case 5: Black sibling with at least one red child  + "outer nephew" is black
            * --> Recolor sibling and its child, and rotate around sibling*/
             if (nodeIsLeftChild && isBlack(sibling.right)){
                 sibling.left.color = BLACK;
                 sibling.color = RED;
                 rotateRight(sibling);
                 sibling = node.parent.right;
             } else if (!nodeIsLeftChild && isBlack(sibling.left)){
                 sibling.right.color =BLACK;
                 sibling.color = RED;
                 rotateLeft(sibling);
                 sibling = node.parent.left;
             }
             /* Case 6: Black sibling with at least one red child + "outer nephew" is red
             * --> Recolor sibling + parent + sibling's child, and rotate around parent*/
             sibling.color = node.parent.color;
             node.parent.color = BLACK;
             if (nodeIsLeftChild){
                 sibling.right.color = BLACK;
                 rotateLeft(node.parent);
             } else {
                 sibling.left.color = BLACK;
                 rotateRight(node.parent);
             }
         }
    
         // get the get Sibling
         Node getSibling(Node node){
            Node parent = node.parent;
    
            if (node==parent.left){
                return parent.right;
            } else if (node==parent.right){
                return parent.left;
            } else {
                throw new IllegalStateException("Parent is not a child of its grandparent");
            }
         }
    
         // check if is black
         boolean isBlack(Node node){
            return node == null || node.color == BLACK;
         }
    
         // To be able to navigate from this NIL leaf to its parent node later, we need a special placeholder.
         static class NilNode extends Node{
            private NilNode(){
                super(0);
                this.color = BLACK;
            }
         }
    
         // replace Parents Child
         void rPC(Node parent, Node oldChild, Node newChild){
            if (parent==null){
                root = newChild;
            } else if (parent.left==oldChild){
                parent.left =newChild;
            } else if (parent.right==oldChild){
                parent.right = newChild;
            } else {
                throw new IllegalStateException("Node is not a child of its parent");
            }
    
            if (newChild!=null){
                newChild.parent = parent;
            }
         }
    
         // rotate Right
         void rotateRight(Node node){
            parent = node.parent;
            Node leftChild = node.left;
    
            node.left = leftChild.right;
            if (leftChild.right!=null){
                leftChild.right.parent = node;
            }
    
            leftChild.right = node;
            node.parent = leftChild;
    
            // call replace Parents Child, to replace the order of the nodes
            rPC(parent,node,leftChild);
         }
    
         // rotate Left
         void rotateLeft(Node node){
            parent = node.parent;
            Node rightChild = node.right;
    
            node.right = rightChild.left;
            if (rightChild.left!=null){
                rightChild.left.parent = node;
            }
    
            rightChild.left = node;
            node.parent = rightChild;
    
             // call replace Parents Child, to replace the order of the nodes
             rPC(parent,node,rightChild);
         }
    
         // post order
         void postO(Node node){
             if (node!=null){
                 postO(node.left);
                 postO(node.right);
                 System.out.print(node.key+"  ");
             }
         }
         // pre order
         void preO(Node node){
             if (node!=null){
                 System.out.print(node.key+"  ");
                 preO(node.left);
                 preO(node.right);
             }
         }
         // in order
         void inO(Node node){
             if (node!=null){
                 inO(node.left);
                 System.out.print(node.key+"  ");
                 inO(node.right);
             }
         }
     }