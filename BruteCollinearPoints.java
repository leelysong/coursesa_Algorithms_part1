//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private int num;
    private Point a;
    private Point b;
    private ArrayList <LineSegment> segments;
    public BruteCollinearPoints(Point[] points)// finds all line segments containing 4 points
    {
        if (points == null){throw new IllegalArgumentException();}
        int n = points.length;
        num=0;
        segments = new ArrayList<>();
        for (int i = 0; i < n-3; i++) {
            if (points[i] == null){throw new IllegalArgumentException();}
            for (int j = i + 1; j < n-2; j++) {
                if (points[i].compareTo(points[j]) == 0) {throw new IllegalArgumentException();}
                for (int k = j + 1; k < n-1; k++) {
                    if(points[k].compareTo(points[j]) == 0) {throw new IllegalArgumentException();}
                    if(points[i].compareTo(points[k]) == 0) {throw new IllegalArgumentException();}
                    if(points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) {break;}
                    for (int m = k + 1; m < n; m++) {
                        if(points[i].compareTo(points[m]) == 0) {throw new IllegalArgumentException();}
                        if(points[m].compareTo(points[k]) == 0) {throw new IllegalArgumentException();}
                        if(points[j].compareTo(points[m]) == 0) {throw new IllegalArgumentException();}
                        if(points[i].slopeTo(points[m]) != points[i].slopeTo(points[k])){break;}
                        a=points[i];//max
                        b=points[i];
                        if(a.compareTo(points[j])<0){a=points[j];}
                        if(b.compareTo(points[j])>0){b=points[j];}
                        if(a.compareTo(points[k])<0){a=points[k];}
                        if(b.compareTo(points[k])>0){b=points[k];}
                        if(a.compareTo(points[m])<0){a=points[m];}
                        if(b.compareTo(points[m])>0){b=points[m];}
                        segments.add(new LineSegment(b, a));
                        num++;
                    }
                }
            }
        }
    }
    public           int numberOfSegments(){
        return num;
    }        // the number of line segments
    public LineSegment[] segments()   {
        return segments.toArray(new LineSegment[segments.size()]);
    }             // the line segments



    /*public static void main(String[] args) {
        // read the n points from a file

        int n = 8;
        Point[] points = new Point[n];
        int[] a={10000,0,3000,7000,20000,14000,3100,6000 };//3100 to 3000
        //int[] b={0,10000,7000,3000,21000,15000,4000,7000};
        int[] b={10000,10000,10000,10000,10000,10000,10000,10000};
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int y = b[i];
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        System.out.println(collinear.numberOfSegments());
        StdDraw.show();
    }*/
}