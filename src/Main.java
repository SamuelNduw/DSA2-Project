class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] children;
    int numKeys;
    boolean leaf;

    BTreeNode(int t, boolean leaf){
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.numKeys = 0;
    }

    void traverse(){
        for(int i = 0; i < numKeys; i++){
            if(!leaf){
                children[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }
        if (!leaf){
            children[numKeys].traverse();
        }
    }

    BTreeNode search(int key){
        int i = 0;
        while(i < numKeys && key > keys[i]){
            i++;
        }
        if (i < numKeys && keys[i] == key){
            return this;
        }
        if (leaf) return null;
        return children[i].search(key);
    }

    void insertNonFull(int key){
        int i = numKeys - 1;

        if (leaf){
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            numKeys++;
        } else{
            while(i >= 0 && keys[i] >key) i--;
            if(children[i + 1].numKeys == 2 * t - 1){
                splitChild(i + 1, children[i + 1]);

                if(key > keys[i + 1]) i++;
            }
            children[i + 1].insertNonFull(key);
        }
    }

    void splitChild(int i, BTreeNode y){
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.numKeys = t - 1;

        for (int j = 0; j < t -1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if(!y.leaf){
            for(int j = 0; j < t; j++){
                z.children[j] = y.children[j + t];
            }
        }

        y.numKeys = t - 1;

        for(int j = numKeys; j >= i + 1; j--){
            children[j + 1] = children[j];
        }
        children[i + 1] = z;

        for(int j = numKeys - 1; j >= i; j--){
            keys[j + 1] = keys[j];
        }

        keys[i] = y.keys[t - 1];
        numKeys++;
    }
}

class BTree{
    BTreeNode root;
    int t;

    public BTree(int t){
        this.root = null;
        this.t = t;
    }

    public void traverse(){
        if(root != null){
            root.traverse();
        }
    }

    public BTreeNode search(int key){
        return (root == null) ? null : root.search(key);
    }

    public void insert(int key){
        if (root == null){
            root = new BTreeNode(t, true);
            root.keys[0] = key;
            root.numKeys = 1;
        } else{
            if(root.numKeys == 2 * t - 1){
                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);
                int i = (s.keys[0] < key) ? 1 : 0;
                s.children[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }
}

public class Main{
    public static void main(String[] args) {
        BTree tree = new BTree(2);

        int[] keys = {7, 5, 9, 4, 6, 8, 13, 2};
        for(int key : keys){
            tree.insert(key);
        }

        int searchKey = 8;
        System.out.println("\nSearching for " + searchKey + ": " + (tree.search(searchKey) != null ? "Found" : "Not Found"));
    }
}
