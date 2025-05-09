import java.util.List;
import java.util.ArrayList;

public class RedBlackTree {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    /** RB‑node */
    static class Node {
        int key;
        boolean color;            // true = RED, false = BLACK
        Node left, right, parent;

        Node(int k, boolean color) { this.key = k; this.color = color; }
        Node(int k)               { this(k, RED); } // default new nodes are red
    }

    /** special immutable NIL placeholder (single instance) */
    private static final Node NIL = new Node(0, BLACK);

    private Node root = NIL;

    /* ----------  INSERT  ---------- */

    public void insert(int key) {
        Node y = NIL, x = root;
        while (x != NIL) {                     // BST search
            y = x;
            if (key == x.key) throw new IllegalArgumentException("duplicate key " + key);
            x = (key < x.key) ? x.left : x.right;
        }
        Node z = new Node(key);                // z is red
        z.parent = y;

        if (y == NIL)              root = z;
        else if (key < y.key)      y.left  = z;
        else                       y.right = z;

        z.left = z.right = NIL;
        fixAfterInsert(z);
    }

    private void fixAfterInsert(Node z) {
        while (z.parent.color == RED) {        // parent is always non‑root here
            Node gp = z.parent.parent;
            if (z.parent == gp.left) {
                Node y = gp.right;             // uncle
                /* Case 3 */
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color        = BLACK;
                    gp.color       = RED;
                    z = gp;
                } else {
                    /* Case 4 → 5 */
                    if (z == z.parent.right) { // inner
                        z = z.parent;
                        rotateLeft(z);
                    }
                    z.parent.color = BLACK;    // outer
                    gp.color       = RED;
                    rotateRight(gp);
                }
            } else {                           // mirror
                Node y = gp.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color        = BLACK;
                    gp.color       = RED;
                    z = gp;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rotateRight(z);
                    }
                    z.parent.color = BLACK;
                    gp.color       = RED;
                    rotateLeft(gp);
                }
            }
        }
        root.color = BLACK;                    // guarantee property 2
    }

    /* ----------  ROTATIONS  ---------- */

    private void rotateLeft(Node x) {
        Node y = x.right;                      // y becomes root of the rotated subtree
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;

        transplant(x, y);
        y.left   = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) y.right.parent = x;

        transplant(x, y);
        y.right  = x;
        x.parent = y;
    }

    /** replaces u by v in the parent link */
    private void transplant(Node u, Node v) {
        if (u.parent == NIL)        root = v;
        else if (u == u.parent.left) u.parent.left  = v;
        else                         u.parent.right = v;
        v.parent = u.parent;
    }

    /* ----------  TRAVERSALS ---------- */

    public void inorder()  { in(root);  System.out.println(); }
    public void preorder() { pre(root); System.out.println(); }
    public void postorder(){ post(root);System.out.println(); }

    private void in (Node n){ if(n!=NIL){ in(n.left);  System.out.print(n.key+" "); in(n.right);} }
    private void pre(Node n){ if(n!=NIL){ System.out.print(n.key+" "); pre(n.left); pre(n.right);} }
    private void post(Node n){if(n!=NIL){ post(n.left); post(n.right); System.out.print(n.key+" ");}}

    public void display() {
        if (root == NIL) {
            System.out.println("Tree is empty");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();

        level.add(root);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (Node n : level) {
                if (n == NIL) {
                    line.add("NIL");
                    next.add(NIL);
                    next.add(NIL);
                } else {
                    String aa = n.key + (n.color == RED ? "(R)" : "(B)");
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left != NIL ? n.left : NIL);
                    next.add(n.right != NIL ? n.right : NIL);

                    if (n.left != NIL || n.right != NIL) nn++;
                }
            }

            if (widest % 2 == 1) widest++;
            lines.add(line);

            List<Node> tmp = level;
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
                        if (!line.get(j-1).equals("NIL")) {
                            c = line.get(j).equals("NIL") ? '┘' : '┴';
                        } else if (!line.get(j).equals("NIL")) {
                            c = '└';
                        }
                    }
                    System.out.print(c);

                    // Lines and spaces
                    if (line.get(j).equals("NIL")) {
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
