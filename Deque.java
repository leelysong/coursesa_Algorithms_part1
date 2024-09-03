import java.util.Iterator;
import java.util.NoSuchElementException;
//import edu.princeton.cs.algs4.StdRandom;

public class Deque <Item> implements Iterable<Item>{
    // construct an empty deque
    private Node first;
    private Node last;
    //private Item item;
    //private Deque next;
    private class Node{
        private Item item;
        private Node next;
    }
    public Deque(){
        //Item item;
        //Deque next;
        first = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first==null;
    }

    // return the number of items on the deque
    public int size(){
        if(isEmpty()){return 0;}
        int i=0;
        Node temp=first;
        while (temp!=null) {
        i++;
        temp=temp.next;
        }
        return i;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null){throw new IllegalArgumentException();}
        Node first_new= first;
        first=new Node();
        first.item=item;
        first.next=first_new;
        if(first_new==null){last=first;}
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){throw new IllegalArgumentException();}
        Node last_new= last;
        last =new Node();
        last.item=item;
        last.next=null;
        if(isEmpty()){first=last;}
        else{last_new.next=last;}
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){throw new NoSuchElementException();}
        Item item;
        item= (Item) first.item;
        first=first.next;
        if(isEmpty()){last=null;}
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){throw new NoSuchElementException();}
        Item item;
        item= (Item) last.item;
        if(size()==1){last=new Node();//instead of null
        first=new Node();//instead of null
        return item;}
        Deque first_new= new Deque();
        int w=size();
        for(int i=0;i<w-2;i++){
            Item t=removeFirst();
            first_new.addLast(t);//reestablish a list
        }
        Item t=removeFirst();
        first_new.addLast(t);//reestablish a list
        //System.out.println(t);///
        first=first_new.first;
        last=new Node();
        last.item=t;
        last.next=null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
    Node current=first;
    public boolean hasNext(){return current!=null;}
        public Item next(){
        if(!hasNext()){throw new NoSuchElementException();}
        Item item= (Item) current.item;
        current=current.next;
        return item;}
        public void remove(){throw new UnsupportedOperationException();}
    }

    // unit testing (required)
    public static void main(String[] args){
    int [] a= {1,4,2,6,9};
    Deque<Integer> s=new Deque<Integer>();
        /*s.addFirst(a[0]);
        s.addLast(a[1]);
        s.addFirst(a[2]);
        s.addLast(a[3]);
        int w=s.removeLast();
        s.removeFirst();*/
    System.out.println(s.isEmpty());
    s.addFirst(a[0]);
    System.out.println(s.isEmpty());
    s.addLast(a[1]);
    s.addFirst(a[2]);
    s.addLast(a[3]);
    System.out.println(s.size());
    int k=s.removeFirst();
    System.out.println(k);
    System.out.println(s.size());
    int w=s.removeLast();
    System.out.println(w);
    int p=s.size();
    System.out.println(p);
    for(int i=0;i<p;i++){
        System.out.println(a[i]);
    }
    for(int r:s){System.out.println(r);}
    }

}
