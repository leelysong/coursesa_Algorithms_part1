import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first,last;
    private class Node{
        Item item;
        Node next;
    }
    // construct an empty randomized queue
    public RandomizedQueue(){
        first = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size(){
        if(isEmpty()){return 0;}
        int size = 0;
        Node current = first;
        while (current!=null){
            size++;
            current = current.next;
        }
        return size;
    }

    // add the item
    /*public void enqueue(Item item){
        if(item == null){throw new IllegalArgumentException();}
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if(oldFirst == null){last = first;}
    }*/
    //add last
    public void enqueue(Item item){
        if(item == null){throw new IllegalArgumentException();}
        Node last_new= last;
        last =new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()){first=last;}
        else{last_new.next=last;}
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){{throw new NoSuchElementException();}}
        int size= size();
        int k = StdRandom.uniformInt(0,size);
        int m=0;
        Item item;
        Node oldFirst = first;
        RandomizedQueue q= new RandomizedQueue();
        while(m!=k){
            item=oldFirst.item;
            q.enqueue(item);
            m++;
            oldFirst = oldFirst.next;
        }
        Item item1=oldFirst.item;
        while(m!=size-1){
            oldFirst=oldFirst.next;
            item=oldFirst.item;
            q.enqueue(item);
            m++;
        }
        first=q.first;
        last=q.last;
        //firstnew.item=null;
        return item1;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){throw new NoSuchElementException();}
            int k = StdRandom.uniformInt(0,size());
            int m=0;
            Node oldFirst = first;
            while(m!=k){
                m++;
                oldFirst = oldFirst.next;
            }
            return oldFirst.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
            return new RandomizedQueueIterator();
        }
    private class RandomizedQueueIterator implements Iterator<Item> {
        RandomizedQueue.Node current=first;
        public boolean hasNext(){return !(isEmpty());}//size()!=0
        public Item next(){
            if(!hasNext()){throw new NoSuchElementException();}
            //Item item= (Item) current.item;
            //current=current.next;
            return dequeue();}
        public void remove(){throw new UnsupportedOperationException();}
        }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue s=new RandomizedQueue();
        System.out.println(s.isEmpty());
        s.enqueue(23);
        System.out.println(s.isEmpty());
        s.enqueue(24);
        s.enqueue(25);
        int k=(int)s.dequeue();
        System.out.println(k);
        System.out.println(s.size());
        int m=(int)s.sample();
        System.out.println(m);
        System.out.println(s.size());
        int p=(int)s.dequeue();
        System.out.println(p);
        System.out.println(s.size());
        int e=(int)s.dequeue();
        System.out.println(e);
        System.out.println(s.size());
        s.enqueue(57);
        s.enqueue(52);
        s.enqueue(48);
        for(Object r:s){System.out.println(r);}
    }

}