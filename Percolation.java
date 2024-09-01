
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//import java.util.Arrays;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private int[] op;
    private int num;
    private WeightedQuickUnionUF grid;
    private int dimention;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        /* grid= new int[n][n];
        num=0;
        int k=0;
        int j=0;
        for(;k<n;k++){
            for(;j<n;j++){
                grid[k][j]=k*n+j;
            }
        } */
        grid=new WeightedQuickUnionUF(n*n+2);
        op=new int[n*n+2];
        //Arrays.fill(op,0);default by 0
        //op[0]=1;
        //op[n*n+1]=1; these two don't matter
        dimention=n;
        num=0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if((row<=0) || (col<=0) || (row>dimention) || (col>dimention)){
            throw new IllegalArgumentException("index " + row +" or index "+ col + " is not between 1 and " + dimention);
        }
        if(! isOpen(row, col)){
            op[(row-1) * dimention + col]=1;//open
            if((row>1) && (col>1) && (row<dimention) && (col<dimention)){
                if (isOpen(row,col-1)){
                grid.union((row-1) * dimention + col - 1, (row-1) * dimention + col);}//connect to the left
                if(isOpen(row,col+1)){
                    grid.union((row - 1) * dimention + col+1, (row-1) * dimention + col);//connect to the right
                }
                if(isOpen(row-1,col)){
                grid.union((row - 2) * dimention + col, (row-1) * dimention + col);}//connect to the upper level
                if(isOpen(row+1,col)){
                    grid.union(row * dimention + col, (row-1) * dimention + col);//connect to the lower level
                }
            }
            if(row==1 ){
                grid.union(0, (row-1) * dimention + col);//connect to the top
                if(isOpen(row+1,col)){
                    grid.union(row * dimention + col, (row-1) * dimention + col);//connect to the lower level
                }
            }
            if(((row==1)||(row==dimention))&& (col!=1) && isOpen(row,col-1)){
                grid.union((row-1) * dimention + col - 1, (row-1) * dimention + col);//connect to the left
                //grid.union(0, (row-1) * dimention + col);
            }
            if(((row==1)||(row==dimention))&& (col!=dimention) && isOpen(row,col+1)){
                grid.union((row-1) * dimention + col + 1, (row-1) * dimention + col);//connect to the right
            }
            /*if(row>1&& col==1&&row<=dimention){
                grid.union((row - 2) * dimention + col, (row-1) * dimention + col);//connect to the top
            }*/
            if(row==dimention ){
                grid.union(dimention*dimention+1, (row-1) * dimention + col);//connect to bottom
                if(isOpen(row-1,col)){
                    grid.union((row - 2) * dimention + col, (row-1) * dimention + col);}//connect to the upper level

            }
            if((col==dimention) && isOpen(row,col-1)){
                grid.union((row-1) * dimention + col - 1, (row-1) * dimention + col);//connect to the left
            }
            if((col==1) && isOpen(row,col+1)){
                grid.union((row-1) * dimention + col + 1, (row-1) * dimention + col);//connect to the right
            }
            if(((col==1)||(col==dimention)) && row>1 && isOpen(row-1,col) ){
                grid.union((row - 2) * dimention + col, (row-1) * dimention + col);}//connect to the upper level
            if(((col==1)||(col==dimention)) && row<dimention && isOpen(row+1,col) ){
                grid.union(row * dimention + col, (row-1) * dimention + col);//connect to the lower level
            }
            num++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row<=0 || col<=0 || row>dimention || col>dimention){
            throw new IllegalArgumentException("index " + row +" or index"+col+ " is not between 1 and " + dimention);
        }
        /*if (col > 1&& col<=dimention) {
            return grid.find((row-1) * dimention + col - 1)==grid.find( (row-1) * dimention + col);//connected to the left
        }
        if (row > 1&& row<=dimention) {
            return grid.find((row - 2) * dimention + col)==grid.find( (row-1) * dimention + col);//connected to the top
        }
        if(row==1){
            return grid.find((row-1) * dimention + col)==grid.find(0);}//top line connected to the source
        return grid.find((row-1) * dimention + col)==grid.find(dimention*dimention+1);//bottom line connect to the bottom
    */
        return op[(row-1) * dimention + col]==1;
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row<=0 || col<=0 || row>dimention || col>dimention){
            throw new IllegalArgumentException("index " + row +" or index"+col+ " is not between 1 and " + dimention);
        }
    return grid.find((row-1) * dimention + col)==grid.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return num;
    }

    // does the system percolate?
    public boolean percolates(){
    return grid.find(0)==grid.find(dimention*dimention+1);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation g=new Percolation(3);
        System.out.println(g.numberOfOpenSites());
        System.out.println(g.percolates());
        g.open(1, 1);
        g.open(2, 3);
        g.open(3, 3);
        //g.open(1, 3);
        System.out.println(g.numberOfOpenSites());
        System.out.println(g.percolates());
        System.out.println(g.isFull(1,1));
        System.out.println(g.isFull(3,3));
    }
}
