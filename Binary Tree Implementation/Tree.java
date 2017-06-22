//Tree.java
//this program creates, builds, and outputs the tree
import static java.lang.System.*;
import java.io.*;
import java.util.*; //for stack class

class Tree {

    private class Node {
        String key;
        Queue<Integer> value;
        Node left;
        Node right;
    }
    private Node root;
    private void debugHelper(Node tree, int depth) {
        // Your code here might be recursive
        //throw new UnsupportedOperationException();
        //in order tansverse:
       
        if(tree != null)
         {
          int temp = depth+1;
         debugHelper(tree.left,temp);
         for(int i = depth; i>=0;i--){
            System.out.print("  ");
            }
          System.out.print(depth+" "+tree.key);
          System.out.println();
          debugHelper(tree.right, temp);
         }

    }
    
  
    private void outputHelper(Node tree) {
     boolean first = true;
        if(tree != null){
   Queue<Integer>.Itor iterator = (Queue<Integer>.Itor)tree.value.iterator();
            outputHelper(tree.left);
            System.out.print(tree.key+": ");
            while(iterator.hasNext()){
               if(first ==true){
                 iterator.next();
                 first = false;
                }
               //System.out.print("HERE");
               System.out.print(iterator.next()+" ");
            }
            System.out.println();
            outputHelper(tree.right);
        }
    }

    public void insert(String key,Integer linenum) {
        //Insert a word into the tree
        Node newNode = new Node();
        newNode.value =new Queue<Integer>();
        newNode.key = key;
        newNode.value.insert(linenum);
        if(root==null){
           root = newNode;
          // root.key = key;
          // root.value.insert(linenum);
        }
       else{
           Node current = root;
           Node parent;
           while(true){
              parent = current;
              if(key.compareTo(current.key)<0){ //go left
                  current = current.left;// = newNode;
                  if(current == null){ //if at end of line insert on left
                     parent.left = newNode;
                     return;
                     }
               } //end of if go left
               else if(key.compareTo(current.key)==0){
                     //print debugging information at each comparison
                     current.value.insert(linenum); //add linenum to the queue of values
                      return;
                     }
                else{//go right
                   current = current.right;
                   if(current == null){
                      parent.right = newNode;
                      return; 
                       }
                   }
       }//end else
    }}
     //code bellow does not need to be modified
    public void debug() {
        debugHelper(root, 0);
    }

    public void output() {
       //Show sorted words with lines where each word appears
        outputHelper(root);
    }
   
}
