public class BruteCollinearPoints {
    private int num;
    private Point[] a;
    private Point[] b;
    private LineSegment[] c;
    public BruteCollinearPoints(Point[] points)// finds all line segments containing 4 points
    {
        int n = points.length;
        num=0;
        int x=n/2;
        a=new Point[x];
        b=new Point[x];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0) {break;}
                for (int k = j + 1; k < n; k++) {
                    if(points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) {break;}
                    if(points[k].compareTo(points[j]) == 0) {break;}
                    if(points[i].compareTo(points[k]) == 0) {break;}
                    for (int l = k + 1; l < n; l++) {
                        if(points[i].slopeTo(points[l]) != points[i].slopeTo(points[k])){break;}
                        if(points[i].compareTo(points[l]) == 0) {break;}
                        if(points[l].compareTo(points[k]) == 0) {break;}
                        if(points[j].compareTo(points[l]) == 0) {break;}
                        if(points[i].compareTo(points[j])==-1)
                        {a[num]=points[i];b[num]=points[j];}
                        else{a[num]=points[j];b[num]=points[i];}
                        if(a[num].compareTo(points[k])==-1){if(b[num].compareTo(points[k])==-1){b[num]=points[k];}}
                        else{a[num] =points[k];}
                        if(a[num].compareTo(points[l])==-1){if(b[num].compareTo(points[l])==-1){b[num]=points[l];}}
                        else{a[num] =points[l];}
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
        c=new LineSegment[num];
        for(int i=0;i<num;i++){
            c[i]=new LineSegment(a[i],b[i]);
        }
        return c;
    }             // the line segments
}