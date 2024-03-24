package books;

public class ComparableImpl {
    static class Person implements Comparable<Person> {
        int height, weight;

        public Person(int h, int w) {
            height = h;
            weight = w;
        }

        public int compareTo(Person p) {
            return Integer.compare(height, p.height);
        }
    }
}
