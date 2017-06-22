// dllist.java
// Template code for doubly-linked list of strings.
import java.util.*;
public class dllist {

   public enum position {FIRST, PREVIOUS, FOLLOWING, LAST};
//crating the node
   private class node {
      String item;
      node prev;
      node next;
       }
//declaration of variables
   private node first = null;
   private node current = null;
   private node last = null;
   private int currentPosition = 0;
   private int counter = 0;
//sets the position based on the position parameter
    public void setPosition (position pos) {
        if(isEmpty()== true){//first check if list is empty and throw exception
            throw new UnsupportedOperationException();
        }
        else if(pos == position.PREVIOUS && current.prev!=null){//set position previous
            current = current.prev;
            currentPosition--;
        }
        else if(pos == position.FIRST){//set position to beginning of list
	    current = first;
	    currentPosition = 0;
        }
        else if(pos == position.FOLLOWING && current.next != null){//set position to next line in list
	    current = current.next;
	    currentPosition++;
        }
        else if(pos == position.LAST){//sets position to the last line in the list
	    current = last;
            currentPosition = counter - 1;
	}
        else{
        }
    }


    public boolean isEmpty(){//check to see if it is empty
        if(first == null && last == null){
            return true;
        }
        else{
            return false;
        }
    }

    public String getItem (){//get the item at current position
        if(isEmpty()==true){
            throw new UnsupportedOperationException();
        }
        return current.item;
    }

    public int getPosition (){ //get the current position
        if(isEmpty()==true){
            throw new UnsupportedOperationException();
        }
        return currentPosition;
    }

    public void delete (){//delete current line
        if(isEmpty()==true){
            throw new UnsupportedOperationException();
        }
        counter--;
        if(counter==0){
            first = null;
            last = null;
            current = null;
        }
        else if(current.equals(first)){
            current = current.next;
            current.prev = null;
            first = current;
            currentPosition = 0;
        }
        else if(current.equals(last)){
            current = current.prev;
            current.next = null;
            last = current;
            currentPosition--;
        }
        else{
            current = current.next;
            current.prev = current.prev.prev;
            current.prev.next = current;
        }
    }

    public void insert (String item, position pos){//insert item at a specific pos
       node newLink = new node();
       newLink.item = item;
       counter++;
       if( isEmpty()==true){//what to insert if empty
           first = newLink;
           last = newLink;
           current = newLink;
           currentPosition = 0;
       }
       else if( current.next == null && pos == position.FOLLOWING){//insert as the last position
            last.next= newLink;
            newLink.prev= last;
            last = newLink;
            current = last;
            currentPosition = counter - 1;
       }
       else if( current.prev == null && pos == position.PREVIOUS){//inserting as previous to front of list
            first.prev = newLink;
            newLink.next = first;
            first = newLink;
            current = first;
            currentPosition = 0;
       }
       else if( pos == position.PREVIOUS){//insert as previoussomewhere in the array (not front) 
            newLink.prev = current.prev;
            current.prev.next = newLink;
            newLink.next = current;
            current.prev = newLink;
	    current = newLink;
       }
       else if( pos == position.FIRST){//insert in front
            first.prev = newLink;
	    newLink.next = first;
	    first = newLink;
	    current = first;
            currentPosition = 0;
       }
       else if( pos == position.FOLLOWING){//insert following
            newLink.next = current.next;
            current.next.prev = newLink;
            newLink.prev = current;
            current.next = newLink;
            current = newLink;
            currentPosition++;
       }
       else if( pos == position.LAST){//inserting at the very end of list
            last.next= newLink;
            newLink.prev= last;
	    last = newLink;
	    current = last;
            currentPosition = counter - 1;
       }                 
   }
}
