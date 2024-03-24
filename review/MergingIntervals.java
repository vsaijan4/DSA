package review;

import java.util.ArrayList;
import java.util.Collections;

class Point implements Comparable<Point> {
    int start;
    int end;

    public Point() {
    }

    public Point(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Point point) {
        return Integer.compare(this.start, point.start);
    }
}

public class MergingIntervals {
    public static void main(String[] args) {
        ArrayList<Point> pointsGroup = new ArrayList<>();
        ArrayList<Point> ans = new ArrayList<>();

        Point g = new Point(1, 3);
        Point b = new Point(2, 3);
        Point a = new Point(4, 5);
        Point d = new Point(6, 7);
        Point c = new Point(8, 10);
        Point f = new Point(11, 12);
        pointsGroup.add(a);
        pointsGroup.add(b);
        pointsGroup.add(c);
        pointsGroup.add(d);
        pointsGroup.add(f);
        pointsGroup.add(g);
        Collections.sort(pointsGroup);
        for (Point p : pointsGroup) {
            System.out.println(p.start + "," + p.end);
        }
        int s = pointsGroup.get(0).start;
        int e = pointsGroup.get(0).end;
        for (Point p : pointsGroup) {
            if (p.start > e) {
                ans.add(new Point(s, e));
                s = p.start;
                e = p.end;
            } else {
                e = Math.max(e, p.end);
            }
        }
        ans.add(new Point(s, e));
        for (Point p : ans) {
            System.out.println(p.start + "," + p.end);
        }
    }
}
