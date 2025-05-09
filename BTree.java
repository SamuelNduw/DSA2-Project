import java.util.ArrayList;
import java.util.List;

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

public class BTree{
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

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        List<BTreeNode> level = new ArrayList<>();
        List<BTreeNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (BTreeNode n : level) {
                if (n == null) {
                    line.add(null);
                    for (int i = 0; i <= 2 * t; i++) {
                        next.add(null);
                    }
                } else {
                    // Build the node representation
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (int i = 0; i < n.numKeys; i++) {
                        if (i > 0) sb.append(",");
                        sb.append(n.keys[i]);
                    }
                    sb.append("]");
                    String nodeStr = sb.toString();
                    line.add(nodeStr);
                    if (nodeStr.length() > widest) widest = nodeStr.length();

                    // Add children to next level
                    if (!n.leaf) {
                        for (int i = 0; i <= n.numKeys; i++) {
                            next.add(n.children[i]);
                            if (n.children[i] != null) nn++;
                        }
                    } else {
                        for (int i = 0; i <= n.numKeys; i++) {
                            next.add(null);
                        }
                    }
                }
            }

            lines.add(line);

            List<BTreeNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            // Print connecting lines
            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // Space between nodes
                    char c = ' ';
                    if (j > 0) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (line.get(j) != null) c = '└';
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
                            System.out.print("─");
                        }
                        System.out.print("┌");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print("─");
                        }
                    }
                }
                System.out.println();
            }

            // Print nodes
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