package books;

import java.util.ArrayList;
import java.util.Iterator;

class IteratorArrayList {

    ArrayList<Integer> list = new ArrayList<>();
    public void iterate() {
        for (Integer ele : list) {
            System.out.println(ele);
        }
    }

    ArrayList<Integer>[] edgeList;
    public void remove(int i) {
        Iterator<Integer> iter = edgeList[i].iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
    }
}
