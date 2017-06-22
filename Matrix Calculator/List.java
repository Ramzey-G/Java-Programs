
/**
 *          List.java
 * List is an ADT that represents a list of Objects
 * This is a linked lists of node objects
 *
 * @author Ramzey Ghanaim
 */
public class List
{
    //create Node class
    private class Node{
        Object data;
        Node next;
        Node prev;
        //constructor
        Node(Object data){
            this.data = data;
            prev = null;
            next = null;
        }
        public String toString(){//convert data to a string
            return data.toString();
        }
        public boolean equals(Object x){
            if(x instanceof Node){
                Node temp = (Node) x; //cast into a node
            if(data.equals(temp.data)){
                return true;
            }
           }
           return false;
        }
    }
    //initialize useful nodes
    private Node front;
    private Node back;
    private Node cursor; //current
    int length; //number of elements
    int index; //ranging from 0 (front) to n-1 (back)

    public List(){ //constructs a list
        front = back = cursor = null;
        length = 0;
        index = -1;
    }
    public int length(){ //returns the length of the list
        return length;
    }
    public int index(){ //returns the index of the cursor, -1 if cursor is not defined
         if(cursor == null){
             return -1;
            }
           return index;
    }
    /**
     * front() returns the data of the front node
     * 
     */
    public Object front(){ //Returns the data if list is not empty
        if(length() > 0)
            return front.data;
        else
             throw new RuntimeException("Error on front(). Called an empty List");
    }
    public Object back(){ //returns data in back of list if list is not empty
        if(length() > 0)
            return back.data;
        else
            throw new RuntimeException("Error on back(). Called an empty List");
    }
    public Object get(){//returns data of curser node
        if(length() > 0 && index()>=0) //second paramter should be && index>=0 
            return cursor.data;
        else
            throw new RuntimeException("Error on get(). Called an empty List");
    }
    /**
     * list() returns true if this List and L are the same integer
     * in the sequence. The cursor is ignored in both lists.
     * 
     */
    public boolean equals(Object x){
               
        if (!(x instanceof List)) return false;
		List L = (List) x;
        if(this.length != L.length())
            return false;
        else{
            Node og = this.front;
            Node nnode = L.front;
            while(og != null){
                if(og.equals(nnode)){
                    og = og.next;
                    nnode = nnode.next;
                }
                else {
		//			System.out.println("\tEntry " + og + "\n\tis not the same as" + nnode);
                    return false;
				}
            }
            return true;
        }

    }

    public void clear(){// Resets this List to its original empty state
        front = null;
        back = null;
        length = 0;
        index = -1;
    }
    public void moveFront(){//place cursor at front of list iff list is not empty
        if(length >0){
            cursor = front;
            index = 0; //new
        }
    }
    public void moveBack(){//place cursor at back of list iff list is not empty
        if(length>0){
            cursor = back;
            index = length-1;
        }
    }
    public void movePrev(){//move cursor back if cursor is defined and not at front
        if(cursor != null && cursor != front){ //cursor!=front ==index !=0
            cursor = cursor.prev;
            index--;
        }
        else if(cursor != null && cursor ==front){
            cursor = null;
            index =-1;// moving cursor back from front puts index at -1
        }
        
    }
    public void moveNext(){ //if cursor is defined andnot at back, move cursor to next element
        if(cursor !=null && cursor != back){
            cursor = cursor.next;
            index++;
        }
        else if(cursor == back){ //cursor falls off if at end of list and moveNext() is called
            cursor = null;
            index = -1; // moving cursor forward from back puts index at -1
        }
    }
    //insert data into front of the list
    public void prepend(Object data){
        //Creating a new node
        Node temp = new Node(data);
        temp.prev = null;
        //putting node where it goes
        if(length == 0){ //first time putting in an element
            temp.next = null;
            front = temp;
            back = temp;
        }
        else{ //not first time putting in an element

            temp.next = front;
            front.prev = temp;
            front = temp;
        }
        length++; //always increment length of list
    }
    //adds data to the end of the list
    public void append(Object data){
        Node temp = new Node(data);
        temp.next = null;
        if(length ==0){ //if list is empty..
            temp.next = null;
            front = temp;
            back = temp;
        }
        else{ //list is not empty
            back.next = temp;
            temp.prev = back;
            back = temp;
            back.next = null;
        }
        length++; //always increment length of list
    }
    public void insertBefore(Object data){ //insert new element before cursor's location Pre: length>0, index>=0
        if(length() == 0){
            throw new RuntimeException("ERROR: insertBefore() doesn't work on empty lists");
        }
        else if(index()<0){
            throw new RuntimeException("ERROR: insertBefore(), null cursor");
        }
            Node temp = new Node(data);
            if(cursor.prev == null){//if cursor is in the front
                temp.next = cursor;
                temp.prev = null;
                front = temp;
            }
            else{
                cursor.prev.next = temp; //node before cursor's .next=temp
                temp.prev = cursor.prev;
                temp.next = cursor;
                //cursor.prev = temp;
                
            }
            cursor.prev = temp;
            length++;
    }
    public void insertAfter(Object data){//insert new element after cursor's location Pre: length>0, index>=0
        if(length() == 0){
            throw new RuntimeException("ERROR: insertAfter() doesn't work on empty lists");
        }
        else if(index()<0){
            throw new RuntimeException("ERROR: insertAfter(), null cursor");
        }
        Node temp = new Node(data); //new node

        if(cursor.next == null){//if cursor is in the back
            cursor.next = temp;
            temp.prev = cursor;
            temp.next = null;
            back = temp;
        }
        else{ //cursor is not at the back
            cursor.next.prev = temp;
            temp.prev = cursor;
            temp.next = cursor.next;
            cursor.next = temp;
        }
        length++; //always increment length
    }
    public void deleteFront(){//delete element at front of list if list is defined
        if(length()>0){
            if(cursor.equals(front)){ //if cursor is in the front... null it
                cursor.next = null;
                cursor.prev = null;
                cursor = null;
                index = -1;
            }
            front = front.next;
            front.prev = null;
            length--;//always decrement length
        }
        else
            throw new RuntimeException("Error on deletFront(). Can't delete on an empty list");
    }
    public void deleteBack(){//delete element in back of list if defined
        if(length()>0){
            back = back.prev;
            back.prev = back.prev.prev;
            back.next = null;
            if(cursor.equals(back)){ //if cursor is at back, null it
                cursor = null;
                cursor.next = null;
                cursor.prev = null;
                index = -1;
            }
            length--;//always decrement length when deleting
        }
        else
            throw new RuntimeException("Error on deleteBack(). Can't delete on an empty List");
    }
    public void delete(){//delete current cursor iff list is not empty, and cursor is not null (index>=0)
        if(cursor ==front){
            deleteFront();
            return;
        }
        else if(cursor == back){
               deleteBack();
               return;
        }
        
        else if(length()>0 && index() >=0){ //doesn't take front and back into account
            cursor.prev.next = cursor.next;
            cursor.next.prev = cursor.prev;
            cursor = null;
            length--;
        }
        else
            throw new RuntimeException("Error on delete(). Called an empty list");
    }
    public String toString(){//returns a string representation of the list, ovrriding the object's toString method
        if(length() > 0){ //make sure list is not empty
            String temp ="";
            for(Node t=front;t!=null;t=t.next){
               temp += t.toString()+" ";
            }
            return temp;
        }
        throw new RuntimeException("Error on toString(). Called an empty List");
    }
    /**List copy(){ //make a copy of the list
        List A = new List();
        for (Node temp = this.front; temp !=null; temp=temp.next){
            A.append(temp.data);
        }
        //null the cursor
        A.cursor = null;
        return A;
    } **/
}
