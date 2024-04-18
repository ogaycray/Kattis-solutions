// Ho Ern See A0259624W

import java.util.*;
import java.io.*;

public class nicknames {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        int numNames = io.getInt();

        // creating the data structures
        AVLTree tree = new AVLTree();
        HashMap<String, Integer> matches = new HashMap<>();

        for (int i = 0; i < numNames; i++) {
            tree.insert(io.getWord());
        }

        // starting to find the matching names
        int numNicknames = io.getInt();
        for (int j = 0; j < numNicknames; j++) {
            String nickname = io.getWord();
            int namecount = 0;
            if (matches.containsKey(nickname)) { // if it is a replicate nickname just get the count
                namecount = matches.get(nickname);
            } else {
                namecount = tree.count(nickname); // count if not entered yet
                matches.put(nickname, namecount);
            }
            io.println(namecount);
        }
        io.close();
    }
}

class Vertex {
    public Vertex parent;
    public Vertex left;
    public Vertex right;
    public String name;
    public int height;
    public int size;

    Vertex(String name) {
        this.name = name;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.height = 0;
        this.size = 1;
    }
}

class AVLTree {
    Vertex root;

    AVLTree() {
        this.root = null;
    }

    void inOrder() {
        this.inOrder(this.root);
    }

    void inOrder(Vertex v) {
        if (v == null) {
            return;
        }
        inOrder(v.left);
        System.out.println(v.name + " ");
        inOrder(v.right);
    }

    void insert(String name) {
        this.root = insert(this.root, name);
    }

    Vertex insert(Vertex v, String name) {
        if (v == null) {
            return new Vertex(name);
        } else { 
            if (name.compareTo(v.name) > 0) {
                v.right = insert(v.right, name);
                v.right.parent = v;
            } else {
                v.left = insert(v.left, name);
                v.left.parent = v;
            }
        }

        updateSize(v);
        updateHeight(v);
        return balance(v);
    }

    Vertex balance(Vertex v) {
        if (balanceFactor(v) == 2) {
            if (balanceFactor(v.left) == -1) {
                v.left = leftRotate(v.left);
            }
            v = rightRotate(v);
        } else if (balanceFactor(v) == -2) {
            if (balanceFactor(v.right) == 1) {
                v.right = rightRotate(v.right);
            }
            v = leftRotate(v);
        }
        return v;
    }

    int balanceFactor(Vertex v) {
        if (v == null) {
            return 0;
        } else {
            return height(v.left) - height(v.right);
        }
    }

    Vertex leftRotate(Vertex v) {
        Vertex w = v.right;
        w.parent = v.parent;
        v.parent = w;
        v.right = w.left;
        if (w.left != null) {
            w.left.parent = v;
        }
        w.left = v;

        updateSize(v);
        updateHeight(v);

        updateSize(w);
        updateHeight(w);
        
        return w;
    }

    Vertex rightRotate(Vertex v) {
        Vertex w = v.left;
        w.parent = v.parent;
        v.parent = w;
        v.left = w.right;
        if (w.right != null) {
            w.right.parent = v;
        }
        w.right = v;

        updateSize(v);
        updateHeight(v);

        updateSize(w);
        updateHeight(w);
        
        return w;
    }

    int count(String nickname) { // returns the number of names the nicknames match
        Vertex match = search(this.root, nickname);
        if (match == null) {
            return 0;
        } else {
            return goLeft(match.left, nickname) + goRight(match.right, nickname) + 1;
        }
    }

    Vertex search(Vertex v, String nickname) { // finding a matching name to the nickname
        if (v == null) {
            return null;
        } 

        int namelength = v.name.length();
        int nicknameLength = nickname.length();

        if (namelength >= nicknameLength) {
            String compareQuery = v.name.substring(0, nicknameLength); 
            if (nickname.compareTo(compareQuery) == 0) {
                return v;
            }
        }

        if (nickname.compareTo(v.name) > 0) {
            return search(v.right, nickname);
        } else {
            return search(v.left, nickname);
        }
    }

    int goLeft(Vertex v, String nickname) { // counting the names matching (< than the searched vertex)
        if (v == null) {
            return 0;
        } 

        int namelength = v.name.length();
        int nicknameLength = nickname.length();

        if (namelength >= nicknameLength) {
            String compareQuery = v.name.substring(0, nicknameLength); 
            if (nickname.compareTo(compareQuery) == 0) {
                return goLeft(v.left, nickname) + size(v.right) + 1;
            }
        }
        return goLeft(v.right, nickname);
    }

    int goRight(Vertex v, String nickname) { // counting the names matching (> than the searched vertex)
        if (v == null) {
            return 0;
        } 

        int namelength = v.name.length();
        int nicknameLength = nickname.length();

        if (namelength >= nicknameLength) {
            String compareQuery = v.name.substring(0, nicknameLength); 
            if (nickname.compareTo(compareQuery) == 0) {
                return goRight(v.right, nickname) + size(v.left) + 1;
            }
        }
        return goRight(v.left, nickname);
    }

    int size(Vertex v) {
        if (v != null) {
            return v.size;
        } else {
            return 0;
        }
    }

    void updateSize(Vertex T) {
        if (T != null) {
            T.size = size(T.left) + size(T.right) + 1;
        }
    }

    int height(Vertex v) {
        if (v != null) {
            return v.height;
        } else {
            return -1;
        }
    }

    void updateHeight(Vertex T) {
        if (T != null) {
            T.height = Math.max(height(T.left), height(T.right)) + 1;
        }
    }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
