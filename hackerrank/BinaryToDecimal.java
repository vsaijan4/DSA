package hackerrank;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}


class Result {

    /*
     * Complete the 'getNumber' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_SINGLY_LINKED_LIST binary as parameter.
     */

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */

    public static long getNumber(SinglyLinkedListNode binary) {

        //Created this LinkedList copy only to reverse the LinkedList
        LinkedList<Integer> binLinked = new LinkedList<>();
        while (binary != null) {
            binLinked.add(binary.data);
            binary = binary.next;
        }
        Collections.reverse(binLinked);
        long ans = 0L;
        Long mulFactor = 1L;
        for (Integer bit : binLinked) {
            ans += bit * mulFactor;
            mulFactor <<= 1;
        }
        return ans;
    }
}


public class BinaryToDecimal {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        SinglyLinkedList binary = new SinglyLinkedList();
        int binaryCount = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, binaryCount).forEach(i -> {
            try {
                int binaryItem = Integer.parseInt(bufferedReader.readLine().trim());
                binary.insertNode(binaryItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.getNumber(binary.head);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}