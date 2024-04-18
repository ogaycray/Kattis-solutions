import java.io.*;
import java.util.*;

public class joinstrings {
    public static void main(String[] args) {

        Kattio io = new Kattio(System.in);
        int n = io.getInt();
        String[] arrStrings = new String[n];
        for (int i = 0; i < n; i++) {
            arrStrings[i] = io.getWord();
        }

        if (n != 1) {

            int a = 0;
            int b = 0;
            TailedLinkedList[] listArr = new TailedLinkedList[n];
            for (int k = 0; k < n - 1; k++) {
                a = io.getInt() - 1;
                b = io.getInt() - 1;
                String wordA = arrStrings[a];
                String wordB = arrStrings[b];

                if (listArr[a] == null && listArr[b] == null) {
                    listArr[a] = new TailedLinkedList();
                    listArr[a].addFront(wordA);
                    listArr[a].addBack(wordB);
                } else if (listArr[a] == null && listArr[b] != null) {
                    listArr[a] = new TailedLinkedList();
                    listArr[a].addFront(wordA);
                    listArr[a].concat(listArr[b]);
                } else if (listArr[a] != null && listArr[b] == null) {
                    listArr[a].addBack(wordB);
                } else {
                    listArr[a].concat(listArr[b]);
                }
            }

            while (listArr[a].head != null) {
                io.print(listArr[a].removeFront());
            }

        } else {
            io.print(arrStrings[0]);
        }
        io.close();
    }
}

class TailedLinkedList {
    // attributes
    public Word head;
    public Word tail;
    public int num_nodes;

    // interface methods

    // Return true if list is empty; otherwise return false.
    public boolean isEmpty() {
        return (num_nodes == 0);
    }

    // Return number of items in list
    public int size() {
        return num_nodes;
    }

    // return index of item if item is found in the list, otherwise return -1
    public int indexOf(String item) {
        int index = 0;

        for (Word cur = head; cur != null; cur = cur.getNext()) {
            if (cur.getItem() == item)
                return index;
            else
                index++;
        }
        return -1;
    }

    // return true if item is in the list false otherwise
    public boolean contains(String item) {
        if (indexOf(item) != -1)
            return true;
        return false;
    }

    // get item at index
    public String getItemAtIndex(int index) {
        int counter = 0;
        String item = "";

        if (index < 0 || index > size() - 1) {
            System.out.println("invalid index");
            System.exit(1);
        }
        if (index == size() - 1)
            item = tail.getItem();
        else {
            for (Word cur = head; cur != null; cur = cur.getNext(), counter++) {
                if (counter == index) {
                    item = cur.getItem();
                    break;
                }
            }
        }
        return item;
    }

    // Return first item
    public String getFirst() {
        return getItemAtIndex(0);
    }

    // Return last item
    public String getLast() {
        return getItemAtIndex(size() - 1);
    }

    // add item at position index, shifting all current items from index onwards to
    // the right by 1
    // pre: 0 <= index <= size()
    public void addAtIndex(int index, String item) {
        Word cur;
        Word newNode = new Word(item);

        if (index >= 0 && index <= size()) {
            if (index == 0) // insert in front
                insert(null, newNode);
            else if (index == size()) // insert at the back, don't have to move all the way to the back
                insert(tail, newNode);
            else {
                cur = getNodeAtIndex(index - 1); // access node at index-1
                insert(cur, newNode);
            }
        } else { // index out of bounds
            System.out.println("invalid index");
            System.exit(1);
        }
    }

    // Add item to front of list
    public void addFront(String item) {
        addAtIndex(0, item);
    }

    // Add item to back of list
    public void addBack(String item) {
        addAtIndex(size(), item);
    }

    // remove item at index and return it
    // pre: 0 <= index < size()
    public String removeAtIndex(int index) {
        Word cur;
        String item = "";

        // index within bounds and list is not empty
        if (index >= 0 && index < size()) {
            if (index == 0) // remove 1st item
                item = remove(null);
            else {
                cur = getNodeAtIndex(index - 1); // access node at index-1
                item = remove(cur);
            }
        } else { // index out of bounds
            System.out.println("invalid index or list is empty");
            System.exit(1);
        }
        return item;
    }

    // Remove first node of list
    public String removeFront() {
        return removeAtIndex(0);
    }

    // Remove last node of list
    public String removeBack() {
        return removeAtIndex(size() - 1);
    }

    // Print values of nodes in list.
    public void print() {
        if (head == null)
            System.out.println("Nothing to print...");
        else {
            Word cur = head;
            System.out.print("List is: " + cur.getItem());
            for (int i = 1; i < num_nodes; i++) {
                cur = cur.getNext();
                System.out.print(", " + cur.getItem());
            }
            System.out.println(".");
        }
    }

    // non-interface helper methods
    public Word getHead() {
        return head;
    }

    public Word getTail() {
        return tail;
    }

    /* return the Word at index */
    public Word getNodeAtIndex(int index) {
        int counter = 0;
        Word node = null;

        if (index < 0 || index > size() - 1) {
            System.out.println("invalid index");
            System.exit(1);
        }
        if (index == size() - 1) // return tail if index == size()-1
            return tail;
        for (Word cur = head; cur != null; cur = cur.getNext()) {
            if (counter == index) {
                node = cur;
                break;
            }
            counter++;
        }
        return node;
    }

    // insert newNode after the node referenced by cur
    public void insert(Word cur, Word n) {
        // insert in front
        if (cur == null) {
            n.setNext(head);
            head = n; // update head
            if (tail == null) // update tail if list originally empty
                tail = head;
        } else { // insert anywhere else
            n.setNext(cur.getNext());
            cur.setNext(n);
            if (cur == tail) // update tail if inserted new last item
                tail = tail.getNext();
        }
        num_nodes++;
    }

    // remove the node referenced by cur.next, and return the item in the node
    // if cur == null, remove the first node
    public String remove(Word cur) {
        String value;

        if (cur == null) { // remove 1st node
            value = head.getItem();
            head = head.getNext(); // update head
            if (num_nodes == 1) // update tail to null if only item in list is removed
                tail = null;
        } else { // remove any other node
            value = cur.getNext().getItem();
            cur.setNext(cur.getNext().getNext());
            if (cur.getNext() == null) // update tail if last item is removed
                tail = cur;
        }
        num_nodes--;

        return value;
    }

    public void concat(TailedLinkedList list) {
        this.tail.setNext(list.head);
        this.tail = list.tail;
        this.tail.setNext(null); 
        this.num_nodes += list.size();
    }
}

class Word {
    public String item;
    public Word next;

    public Word(String val) {
        this(val, null);
    }

    public Word(String val, Word n) {
        item = val;
        next = n;
    }

    public Word getNext() {
        return next;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String val) {
        item = val;
    }

    public void setNext(Word n) {
        next = n;
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
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
