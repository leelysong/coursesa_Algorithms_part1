import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private int size;
    private Node root;
    //ArrayList<Point2D> points;

    private static class Node {
        private Point2D point;
        private double xmin;
        private double xmax;
        private double ymin;
        private double ymax;
        private boolean is_x_partition;
        Node left;
        Node right;

        public Node(Point2D point, double xmin, double xmax, double ymin, double ymax, boolean is_x_partition) {
            this.point = point;
            this.xmin = xmin;
            this.xmax = xmax;
            this.ymin = ymin;
            this.ymax = ymax;
            this.is_x_partition = is_x_partition;
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return this.size == 0;
    }                     // is the set empty?

    public int size() {
        return this.size;
    }                       // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;
        root = put(root, p, 0.0, 1.0, 0.0, 1.0, true);
    }            // add the point to the set (if it is not already in the set)

    private Node put(Node root, Point2D point, double xmin, double xmax, double ymin, double ymax, boolean is_x_partition) {
        if (root == null) {
            this.size++;
            return new Node(point, xmin, xmax, ymin, ymax, is_x_partition);
        }
        int cmp = is_x_partition ? Double.compare(point.x(), root.point.x()) : Double.compare(point.y(), root.point.y());
        if (cmp < 0) {
            if (is_x_partition)
                root.left = put(root.left, point, xmin, root.point.x(), ymin, ymax, !is_x_partition);
            else if (!is_x_partition)
                root.left = put(root.left, point, xmin, xmax, ymin, root.point.y(), !is_x_partition);
        } else {
            if (is_x_partition) root.right = put(root.right, point, root.point.x(), xmax, ymin, ymax, !is_x_partition);
            else if (!is_x_partition)
                root.right = put(root.right, point, xmin, xmax, root.point.y(), ymax, !is_x_partition);
        }
        return root;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        else return get(root, p);
    }

    private boolean get(Node root, Point2D point) {
        if (root == null) return false;
        else if (root.point.compareTo(point) == 0) return true;
        int cmp = root.is_x_partition ? Double.compare(point.x(), root.point.x()) : Double.compare(point.y(), root.point.y());
        if (cmp < 0) {
            return get(root.left, point);
        } else return get(root.right, point);
    }

    // does the set contain point p?
    public void draw() {
        if (!isEmpty()) {
            draw(root);
        }
    }                        // draw all points to standard draw

    private void draw(Node root) {
        if (root != null) {
            root.point.draw();
            if (root.is_x_partition) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(root.point.x(), root.ymin, root.point.x(), root.ymax);
            } else if (!root.is_x_partition) {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(root.xmin, root.point.y(), root.xmax, root.point.y());
            }
            draw(root.left);
            draw(root.right);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        range(root, rect, points);
        return points;
    }

    private void range(Node root, RectHV rect, ArrayList<Point2D> points) {
        if(rect == null) throw new IllegalArgumentException();
        if (root == null) return;
        RectHV rectHV = new RectHV(root.xmin, root.ymin, root.xmax, root.ymax);
        if (rectHV.intersects(rect)) {
            if (rect.contains(root.point)) {
                points.add(root.point);
            }
            range(root.left, rect,points);
            range(root.right, rect,points);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    private class Champion {
        Point2D point;
        double d;

        Champion(Point2D point, double d) {
            this.point = point;
            this.d = d;
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        //double d=p.distanceSquaredTo(root.point);
        Champion re = new Champion(root.point, Double.POSITIVE_INFINITY);
        nearest(root, p, re);
        Point2D point = re.point;
        return point;
    }            // a nearest neighbor in the set to point p; null if the set is empty

    private void nearest(Node root, Point2D p, Champion re) {
        double d1 = p.distanceSquaredTo(root.point);
        if (Double.compare(d1, re.d) < 0) {
            re.point = root.point;
            re.d = d1;
        }
        boolean cmp = (root.is_x_partition ? Double.compare(p.x(), root.point.x()) : Double.compare(p.y(), root.point.y())) < 0;
        if (cmp) {
            if (root.left != null) {
                RectHV hv = new RectHV(root.left.xmin, root.left.ymin, root.left.xmax, root.left.ymax);
                if (hv.distanceSquaredTo(p) < re.d) nearest(root.left, p, re);
            }
            if (root.right != null) {
                RectHV hv = new RectHV(root.right.xmin, root.right.ymin, root.right.xmax, root.right.ymax);
                if (hv.distanceSquaredTo(p) < re.d) nearest(root.right, p, re);
            }
        } else if (!cmp) {
            if (root.right != null) {
                RectHV hv = new RectHV(root.right.xmin, root.right.ymin, root.right.xmax, root.right.ymax);
                if (hv.distanceSquaredTo(p) < re.d) nearest(root.right, p, re);
            }
            if (root.left != null) {
                RectHV hv = new RectHV(root.left.xmin, root.left.ymin, root.left.xmax, root.left.ymax);
                if (hv.distanceSquaredTo(p) < re.d) nearest(root.left, p, re);
            }
        }
    }

    public static void main(String[] args) {

    }                 // unit testing of the methods (optional)
}