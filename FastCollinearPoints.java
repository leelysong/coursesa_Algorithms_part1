import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Comparator;

import static java.lang.Double.NEGATIVE_INFINITY;

public class FastCollinearPoints {
    private int num;
    private Point[] a;
    private Point[] b;
    private ArrayList <LineSegment> segments;
    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        if(n<4){return;}
        Point min;
        Point max;
        num=0;
        Point[] copy= Arrays.copyOf(points,n);
        for (int i = 0; i < n; i++) {
        Arrays.sort(copy,points[i].slopeOrder());
            min = points[i];
            max = points[i];
        for (int j = 1, k=1; j <= n; j++) {if(j==n || Double.compare(points[i].slopeTo(copy[j]),points[i].slopeTo(copy[j+1]))!=0){
            if(j-k>=3 && min.compareTo(points[i])==0){
                segments.add(new LineSegment(min,max));
                min = points[i];
                max = points[i];
                k=j;
                num++;
            }

        if(j<n){
            if(copy[j].compareTo(max)>0){max=copy[j];}
            if(copy[j].compareTo(min)<0){min=copy[j];}}
        }

        }
        }
    }    // finds all line segments containing 4 or more points
    public           int numberOfSegments() {return num;}       // the number of line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }               // the line segments
}