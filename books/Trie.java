package books;

import java.util.HashMap;
import java.util.Map;

class Trie {
    private boolean isWord;
    private Map<Character, Trie> children;

    Trie() {
        isWord = false;
        children = new HashMap<>();
    }

    public void insert(String key) {
        Trie curr = this;
        for (char c : key.toCharArray()) {
            curr.children.putIfAbsent(c, new Trie());
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    public boolean search(String key) {
        Trie curr = this;
        for (char c : key.toCharArray()) {
            curr = curr.children.get(c);
            if (curr == null) {
                return false;
            }
        }
        return curr.isWord;
    }
}

class Main {
    public static void main(String[] args) {
        Trie head = new Trie();

        head.insert("techie");
        head.insert("techi");
        head.insert("tech");

        System.out.println(head.search("tech"));            // true
        System.out.println(head.search("techi"));           // true
        System.out.println(head.search("techie"));          // true
        System.out.println(head.search("techiedelight"));   // false

        head.insert("techiedelight");

        System.out.println(head.search("tech"));            // true
        System.out.println(head.search("techi"));           // true
        System.out.println(head.search("techie"));          // true
        System.out.println(head.search("techiedelight"));   // true
    }
}