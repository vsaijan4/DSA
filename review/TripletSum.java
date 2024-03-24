package review;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/*
Given an array and a value, find if there is a triplet in array whose sum is
equal to the given value. If there is such a triplet present in array, then
print the triplet and return true. Else return false.

Input: array = {12, 3, 4, 1, 6, 9}, sum = 24;
Output: 12, 3, 9
 */

class TripletSum {

  public static void main(String[] args) {
    final OutputWriter out = new OutputWriter(System.out);
    int[] arr = {12, 3, 4, 1, 6, 9};
    int sum = 16;
    Set<Integer> set = new HashSet<>();
    for (int value : arr) {
      set.add(value);
    }
    boolean isFound = false;
    for (int a : arr) {
      set.remove(a);
      int target = sum - a;
      for (Integer elem : set) {
        if (set.contains(target - elem)) {
          out.printLine(a + " " + elem + " " + (target - elem));
          isFound = true;
          break;
        }
      }
      set.add(a);
        if (isFound) {
            break;
        }
    }
    out.printLine(isFound);
    out.close();
  }

  static class OutputWriter {

    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
      writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
          outputStream)));
    }

    public void print(Object... objects) {
      for (int i = 0; i < objects.length; i++) {
          if (i != 0) {
              writer.print(' ');
          }
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
