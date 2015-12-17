//Queue.java
//This file is hwere the queue is created. a queue is the atribut of
//each node in the treeimport java.util.Iterator;
import java.util.NoSuchElementException;

class Queue <Item> implements Iterable <Item> {

   private class Node {
      Item item;
      Node next;
   }
   private Node head = new Node();
   private Node tail = new Node();
   
    public boolean isempty() {
      if(head ==null && tail ==null)
           return true;
      return false;
   }

   public void insert(Item newitem) {
     //new Queue<Integer>();
     Node newNode=new Node();
     if(isempty()){
         head.item = newitem;
     }
     else{
          if(head.next==null){
              newNode.item = newitem;
              head.next = newNode;
              tail = newNode;
           }
          else{
               newNode.item = newitem;
               tail.next = newNode;
               tail= newNode;
             }
       }
     // throw new RuntimeException("Replace this with working code");
   }

   public Iterator <Item> iterator() {
      return new Itor ();
   }

   class Itor implements Iterator <Item> {
      Node current = head;
      public boolean hasNext() {
         return current != null;
      }
      public Item next() {
         if (! hasNext ()) throw new NoSuchElementException();
         Item result = current.item;
         current = current.next;
         return result;
      }
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

}
