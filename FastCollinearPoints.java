//import edu.princeton.cs.algs4.StdDraw;
//import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;



public class FastCollinearPoints {
    private int num;
    private Point[] a;
    private Point[] b;
    private ArrayList <LineSegment> segments;
    public FastCollinearPoints(Point[] points) {
        if (points== null){throw new IllegalArgumentException();}
        int n = points.length;
        if(n<4){return;}
        Point min;
        Point max;
        num=0;
        segments = new ArrayList<>();
        Point[] copy= Arrays.copyOf(points,n);
        for (int i = 0; i < n; i++) {
            if (points[i] == null){throw new IllegalArgumentException();}
            Arrays.sort(copy,points[i].slopeOrder());
            min = points[i];
            max = points[i];
        for (int j = 1, k=1; j < n+1; j++) {
            if(j<n && points[i].compareTo(copy[j]) == 0){throw new IllegalArgumentException();}
            if( j==n || Double.compare(points[i].slopeTo(copy[j]),points[i].slopeTo(copy[k]))!=0 ) {
                if (((j - k >= 3)) && min.compareTo(points[i]) == 0) {
                    segments.add(new LineSegment(min, max));
                    num++;
                }
                min = points[i];
                max = points[i];
                k = j;
            }
        if(j<n){
            if(copy[j].compareTo(max)>0){max=copy[j];}
            if(copy[j].compareTo(min)<0){min=copy[j];}}


        }
        }
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments() {return num;}       // the number of line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }               // the line segments



    /*public static void main(String[] args) {
        // read the n points from a file

        int n = 8;
        Point[] points = new Point[n];
        int[] a={10000,0,3000,7000,20000,14000,3000,6000 };
        int[] b={0,10000,7000,3000,21000,15000,4000,7000};
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }*/
}