package leetcode;

import java.io.*;
import java.util.*;

class WordLadder {

    final static OutputWriter out = new OutputWriter(System.out);

    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> a = new ArrayList<>(Arrays.asList("hot", "dot", "tog", "cog"));
        System.out.println(wl.ladderLength(beginWord, endWord, a));
        out.close();
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        if (!wordList.contains(beginWord)) wordList.add(beginWord);

        int size = wordList.size();
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        HashMap<String, Integer> wordIndexMap = new HashMap<>();
        int idx = 0;
        for (String word : wordList) {
            adjList.put(idx, new ArrayList<>());
            wordIndexMap.put(word, idx++);
        }

        for (int i = 0; i < size; i++) {
            String word = wordList.get(i);
            idx = wordIndexMap.get(word);
            for (int j = i + 1; j < size; j++) {
                if (isNeighbour(word, wordList.get(j))) {
                    int nbIdx = wordIndexMap.get(wordList.get(j));
                    adjList.get(idx).add(nbIdx);
                    adjList.get(nbIdx).add(idx);
                }
            }
        }

        int beginIdx = wordIndexMap.get(beginWord);
        int endIdx = wordIndexMap.get(endWord);

        boolean[] visited = new boolean[size];
        Queue<Element> queue = new LinkedList<>();
        queue.add(new Element(beginIdx, 0));
        visited[beginIdx] = true;
        while (!queue.isEmpty()) {
            Element element = queue.remove();
            int depth = element.depth;
            if (element.index == endIdx) {
                return depth + 1;
            }
            ArrayList<Integer> neighborList = adjList.get(element.index);
            for (Integer neighbor : neighborList)
                if (!visited[neighbor]) {
                    queue.add(new Element(neighbor, depth + 1));
                    visited[neighbor] = true;
                }
        }
        return 0;
    }

    class Element {
        int index;
        int depth;

        public Element(int index, int depth) {
            this.index = index;
            this.depth = depth;
        }
    }

    public boolean isNeighbour(String a, String b) {
        int len = a.length();
        int diff = 0;
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (diff == 1) return false;
                diff = 1;
            }
        }
        return true;
    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    outputStream)));
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }
    }
}