import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private Percolation p;
    private double[] ratio;
    private int[] num;
    public PercolationStats(int n, int trials){
        //p = new Percolation[trials];
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n and trials must be positive intergers.");
        }
        num=new int[trials];
        ratio=new double[trials];
        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            num[i]=0;
            while(!p.percolates()) {
                int row = StdRandom.uniformInt(n)+1;
                int col = StdRandom.uniformInt(n)+1;
                p.open(row,col);
            }
            num[i]=p.numberOfOpenSites();
            ratio[i] = (double)num[i]/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(ratio);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(ratio);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return (StdStats.mean(ratio)-1.96*stddev()/Math.sqrt(ratio.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return (StdStats.mean(ratio)+1.96*stddev()/Math.sqrt(ratio.length));
    }

    // test client (see below)
    public static void main(String[] args){
        int n=Integer.parseInt(args[0]);
        int T=Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, T);
        System.out.println("mean = "+ps.mean());
        System.out.println("stddev = "+ps.stddev());
        System.out.println("95% confidence interval = "+"["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
        //System.out.println(ps.confidenceHi());
    }
}
