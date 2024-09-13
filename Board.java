import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
//import java.util.Arrays;

public class Board {
    private final int[][] tiles;
    private final int n;
    private int hamming;
    private int manhattan;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        n=tiles.length;
        this.tiles=new int[n][n];
        hamming=0;
        manhattan=0;
        int c,r;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                this.tiles[i][j] = tiles[i][j];
                if(this.tiles[i][j]!=this.n*i+j+1){
                    if(i!=this.n-1 || j!=this.n-1){hamming++;}
                    //if(i==this.n-1 && j==this.n-1 &&this.tiles[i][j]!=0){hamming++;}
                }
                if(this.tiles[i][j]!=0){
                    c=(this.tiles[i][j]-1)%this.n;
                    r=(this.tiles[i][j]-1)/this.n;
                    manhattan+=Math.abs(r-i)+Math.abs(c-j);}
            }
        }
    }

    // string representation of this board
    public String toString(){
        StringBuilder s=new StringBuilder();
        s.append(this.n+"\n");
        for(int i=0;i<this.n;i++) {
            for (int j = 0; j < this.n;  j++) {
                s. append(String.format(" %d",this.tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension(){
        return n;//(int)Math.sqrt(this.tiles.length)
    }

    // number of tiles out of place
    public int hamming(){
        /*int hamming=0;
        for(int i=0;i<this.n;i++){
            for(int j=0;j<this.n;j++){
                if(this.tiles[i][j]!=this.n*i+j+1){
                    if(i!=this.n-1 || j!=this.n-1){hamming++;}
                    //if(i==this.n-1 && j==this.n-1 &&this.tiles[i][j]!=0){hamming++;}
                }
            }
        }*/
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        /*int manhattan=0;
        int r,c;
        for(int i=0;i<this.n;i++){
            for(int j=0;j<this.n;j++){
                if(this.tiles[i][j]!=0){
                c=(this.tiles[i][j]-1)%this.n;
                r=(this.tiles[i][j]-1)/this.n;
                manhattan+=Math.abs(r-i)+Math.abs(c-j);}
                //if(this.tiles[i][j]==0){manhattan+=Math.abs(this.n-1-i)+Math.abs(this.n-1-j);;}
            }
        }*/
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return this.hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(!(y instanceof Board)) return false;
        if(this.n!=((Board)y).n) return false;
        for(int i=0;i<this.n;i++){
            for(int j=0;j<this.n;j++){
                if(this.tiles[i][j]!=((Board)y).tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        ArrayList<Board> neighbors=new ArrayList<>();
        int i=0, j=0;
        while(i<this.n && this.tiles[i][j]!=0){
            if(j==this.n-1){i++;j=0;}
            else{j++;}
        }
        if (this.n==1){return neighbors;}
        int[][] copy= new int[this.n][this.n];
        for(int s=0;s<this.n;s++){
            for(int e=0;e<this.n;e++){
                copy[s][e]=this.tiles[s][e];
            }
        }
        int swap;
        if(i==0 && j==0 ){
            swap=copy[0][1];
            copy[0][0]=copy[0][1];
            copy[0][1]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[0][1]=swap;
            swap=copy[1][0];
            copy[0][0]=copy[1][0];
            copy[1][0]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i==0 && j==this.n-1){
            swap=copy[0][this.n-2];
            copy[0][this.n-1]=copy[0][this.n-2];
            copy[0][this.n-2]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[0][this.n-2]=swap;
            copy[0][this.n-1]=copy[1][this.n-1];
            copy[1][this.n-1]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i==0 && j<this.n-1 && j>0){
            swap=copy[0][j-1];
            copy[0][j]=swap;
            copy[0][j-1]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[0][j-1]=swap;
            swap=copy[1][j];
            copy[0][j]=swap;
            copy[1][j]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[1][j]=swap;
            swap=copy[0][j+1];
            copy[0][j]=swap;
            copy[0][j+1]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i==this.n-1 && j==this.n-1){
            swap=copy[this.n-1][this.n-2];
            copy[this.n-1][this.n-1]=copy[this.n-1][this.n-2];
            copy[this.n-1][this.n-2]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[this.n-1][this.n-2]=swap;
            copy[this.n-1][this.n-1]=copy[this.n-2][this.n-1];//up
            copy[this.n-2][this.n-1]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i<this.n-1 && j==this.n-1 && i>0){
            swap=copy[i][this.n-2];
            copy[i][this.n-1]=swap;
            copy[i][this.n-2]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[i][this.n-2]=swap;
            swap=copy[i-1][this.n-1];
            copy[i][this.n-1]=swap;
            copy[i-1][this.n-1]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[i-1][this.n-1]=swap;
            swap=copy[i+1][this.n-1];
            copy[i][this.n-1]=swap;
            copy[i+1][this.n-1]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i==this.n-1 && j<this.n-1 && j>0){
            swap=copy[this.n-1][j-1];
            copy[this.n-1][j]=swap;
            copy[this.n-1][j-1]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[this.n-1][j-1]=swap;
            swap=copy[this.n-1][j+1];
            copy[this.n-1][j]=swap;
            copy[this.n-1][j+1]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[this.n-1][j+1]=swap;
            swap=copy[this.n-2][j];
            copy[this.n-1][j]=swap;
            copy[this.n-2][j]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i==this.n-1 && j==0){
            swap=copy[this.n-2][0];
            copy[this.n-1][0]=copy[this.n-2][0];
            copy[this.n-2][0]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[this.n-2][0]=swap;
            copy[this.n-1][0]=copy[this.n-1][1];
            copy[this.n-1][1]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        if(i>0 && i<this.n-1 && j==0){
            swap=copy[i-1][0];
            copy[i][0]=copy[i-1][0];
            copy[i-1][0]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[i-1][0]=swap;
            swap=copy[i][1];
            copy[i][0]=copy[i][1];
            copy[i][1]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[i][1]=swap;
            swap=copy[i+1][0];
            copy[i][0]=swap;
            copy[i+1][0]=0;
            neighbors.add(new Board(copy));
            return neighbors;
        }
        else{
            swap=copy[i-1][j];
            copy[i][j]=copy[i-1][j];
            copy[i-1][j]=0;
            int[][] copy1= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy1[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy1));
            copy[i-1][j]=swap;
            swap=copy[i+1][j];
            copy[i][j]=copy[i+1][j];
            copy[i+1][j]=0;
            int[][] copy2= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy2[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy2));
            copy[i+1][j]=swap;
            swap=copy[i][j+1];
            copy[i][j]=copy[i][j+1];
            copy[i][j+1]=0;
            int[][] copy3= new int[this.n][this.n];
            for(int s=0;s<this.n;s++){
                for(int e=0;e<this.n;e++){
                    copy3[s][e]=copy[s][e];
                }
            }
            neighbors.add(new Board(copy3));
            copy[i][j]=copy[i][j-1];
            copy[i][j-1]=0;
            copy[i][j+1]=swap;
            neighbors.add(new Board(copy));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int i1=0, j1=0;
        while(i1<this.n && this.tiles[i1][j1]!=0){
            if(j1==this.n-1){i1++;j1=0;}
            else{j1++;}
        }
        int i = StdRandom.uniformInt(this.n);
        int j = StdRandom.uniformInt(this.n);
        int k = StdRandom.uniformInt(this.n);
        int m= StdRandom.uniformInt(this.n);
        if(i==i1){while(j==j1){j = StdRandom.uniformInt(this.n);}}
        if(i==k){
        while(m==j || (k==i1 && m==j1)){m = StdRandom.uniformInt(this.n);}}
        if(i!=k){while(k==i1 && m==j1){m = StdRandom.uniformInt(this.n);}}
        int[][] copy= new int[this.n][this.n];
        for(int s=0;s<this.n;s++){
            for(int e=0;e<this.n;e++){
                copy[s][e]=this.tiles[s][e];
            }
        }
        int swap=copy[i][j];
        copy[i][j]=copy[k][m];
        copy[k][m]=swap;
        return new Board(copy);
    }

    // unit testing (not graded)
    public static void main(String[] args){
        Board b=new Board(new int[][]{{1,0,3},{4,2,5},{7,8,6}});
        Board c=new Board(new int[][]{{1,0,3},{4,2,5},{7,8,6}});
        System.out.println(b);
        System.out.println(b.manhattan());
        System.out.println(b.hamming());
        System.out.println(b.twin());
        for(Board r:b.neighbors()){
            System.out.println(r);
        }
        System.out.println(b.equals(c));
    }

}
