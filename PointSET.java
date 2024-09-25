import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;
    //private ArrayList<Point2D> range;
    public         PointSET() {
        points = new TreeSet<>();
    }                              // construct an empty set of points
    public           boolean isEmpty()  {
        return points.isEmpty();
    }                    // is the set empty?
    public               int size() {
        return points.size();
    }                        // number of points in the set
    public              void insert(Point2D p) {
        if (p == null) {throw new IllegalArgumentException();}
        points.add(p);
    }             // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p)  {
        if (p == null) {throw new IllegalArgumentException();}
        return points.contains(p);
    }          // does the set contain point p?
    public              void draw() {
    for (Point2D p : points) {
        p.draw();
    }
    }                        // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)  {
        if (rect == null) {throw new IllegalArgumentException();}
        ArrayList<Point2D> range=new ArrayList<>();
    for (Point2D p : points) {
        if (rect.contains(p)) {range.add(p);}
    }
    return range;
    }           // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p){
        if (p == null) {throw new IllegalArgumentException();}
        if(isEmpty()){return null;}
        Double min=Double.POSITIVE_INFINITY;
        double d=0.0;
        Point2D nearest=null;
        for (Point2D point : points) {
            d=point.distanceTo(p);
            if(d<min){min=d;
            nearest=point;}
        }
        return nearest;
    }             // a nearest neighbor in the set to point p; null if the set is empty

    //public static void main(String[] args)                  // unit testing of the methods (optional)
}