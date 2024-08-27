import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;*/
public class RandomWord {
    public static void main(String[] args) {
        String current;
        String champion=null;
        Integer i=0;
        while (!(StdIn.isEmpty())){
            current=StdIn.readString();
            i++;
            if (StdRandom.bernoulli(1/i)){
                champion=current;
            }
        }
        /*if (StdIn.isEmpty()){*/
        StdOut.println(champion);

    }
}
