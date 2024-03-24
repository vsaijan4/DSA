package books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sort array maintaining original indices
 * <p>
 * uses Comparable
 */

class Element implements Comparable<Element> {
    int index, value;

    Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int compareTo(Element e) {
        return this.value - e.value;
    }

    public static void main(String[] args) {
        int[] array = {17, 10, 8, 13, 5, 7, 8, 30};
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            elements.add(new Element(i, array[i]));
        }

        Collections.sort(elements);
        Collections.reverse(elements);
        for (Element element : elements) {
            System.out.println(element.value + " " + element.index);
        }
    }
}
