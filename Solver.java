import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private final boolean Solvable;
    private class Node implements Comparable<Node> {
        private final Board board;
        private final Node parent;
        private final int moves;
        private final int d;
        private final int manhattan;
        Node(Board board, Node parent, int moves) {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
            this.manhattan = board.manhattan();
            this.d = moves+this.manhattan;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.d, o.d);
        }
    }
    private Node goal;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<Node> solution= new MinPQ<>();;
        MinPQ<Node> twin_solution = new MinPQ<>();
        twin_solution.insert(new Node(initial.twin(), null, 0));
        MinPQ<Node> manhattan_solution;
            Node first=new Node(initial,null,0);
            solution.insert(first);
            Node min=solution.delMin();
        manhattan_solution=solution;
            while(!min.board.isGoal()) {
                for (Board r : min.board.neighbors()) {
                    if (min.parent==null || !r.equals(min.parent.board)) {
                        Node second = new Node(r, min, min.moves + 1);
                        manhattan_solution.insert(second);
                    }
                }
                manhattan_solution=manhattan_solution==solution?twin_solution:solution;
                min=manhattan_solution.delMin();
            }
        Solvable=manhattan_solution==solution;
        if(Solvable){
            goal=min;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return Solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if(!Solvable) return -1;
        return goal.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(!Solvable) return null;
        Stack<Board> solution= new Stack<>();
        //Node current = new Node(goal.board,goal.parent,goal.moves);
        Node current =goal;
        while(current!=null){
            solution.push(current.board);
            current = current.parent;
        }
        ArrayList<Board> result= new ArrayList<>();
        Board item=solution.pop();
        result.add(item);
        while(!solution.isEmpty()){
            item=solution.pop();
            result.add(item);
        }
        return result;
    }

    // test client (see below)
    public static void main(String[] args){
        int n=3;
        int[][] tiles = {{0,1,3},{4,2,5},{7,8,6}};
        //int[][] tiles = {{0,1,3},{4,2,5},{8,7,6}};
        Board initial = new Board(tiles);
        System.out.println(initial.dimension());

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
