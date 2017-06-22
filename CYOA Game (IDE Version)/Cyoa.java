import java.io.*;
import java.util.Scanner;
import java.util.*;
/** Cyoa.java
 * Create Your Own Adventure
 * 
 * Cyoa is an adventure game You can play with your own file!
 * See Design report for explination on how the file and program
 * work
 * 
 * @author Ramzey Ghanaim
 * @version 1.0
 */
public class Cyoa
{
    public static int numrooms =1;
    private static int historyid=1;
    public static char [] alphabet= {'a', 'b', 'c', 'd', 'e', 'f','g', 'h','i','j','k','l'};
    public static int currentroom =0;
    public static int numoptions;//number of options in current room
    public static boolean firsthist = true;
    public static void main(String [] args) throws IOException{
        //String file = "Walking Dead the game.txt";
        //String file = "file2.txt";
        //COde bellow is for command line arguments of the file name
        if(args.length==0){ //if there are no command line arguments
            System.out.println("To run program type: Cyoa gamefile");
            System.exit(1);
        }

        String file = args[0];
        
         Scanner s1 = new Scanner(new File (file));//loads file
         String temp;
         historyid =0;
         currentroom =0;
         numrooms=1;
          int previousroom;
         while(s1.hasNextLine()){  //this while loop counts the number of rooms
             temp = s1.nextLine();
             if(temp.length() ==0){ //if there is a blank line....
                  numrooms++;       //add to the number of rooms
                }
            }
         node[] rooms = new node[numrooms]; //created the rooms array
         node[] orderedrooms = new node[numrooms]; //created the ordered rooms array
         Scanner s2 = new Scanner(new File(file));
         processfile(s2, rooms[0], 0, rooms);
         Scanner ordered = new Scanner(new File(file));
         processfile(ordered, orderedrooms[0], 0, orderedrooms);
         historyNode history = new historyNode();
         //history.id = 0;
         //history.num=0;
         Scanner stdin = new Scanner (System.in);
         //System.out.println("This is room one.\n");
         printDescription(rooms[0]);
         historyid++;
         int previousaction;
         System.out.println();
         //Debugging: printAll(rooms);
         while(true){
             previousroom = currentroom;
             node maintemp = rooms[currentroom].next;
             
             String input= stdin.nextLine();
             
             if(input.equals("q")){
                 System.exit(0);
                }
             else if(input.equals("a")&& numoptions>=1){
                 printCurrentAction(maintemp, 1);
                 doOption(1, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("b")&& numoptions>=2){
                 printCurrentAction(maintemp, 2);
                 doOption(2, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("c") && numoptions>=3){
                 printCurrentAction(maintemp, 3);
                 doOption(3, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("d") && numoptions>=4){
                 
                 printCurrentAction(maintemp, 4);
                 doOption(4, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("e") && numoptions>=5){
                 printCurrentAction(maintemp, 5);
                 doOption(5, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("f") && numoptions>=6){
                 printCurrentAction(maintemp, 6);
                 doOption(6, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("g") && numoptions>=7){
                 
                 printCurrentAction(maintemp, 7);
                 doOption(7, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("h") && numoptions>=8){
                 printCurrentAction(maintemp, 8);
                 doOption(8, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("i") && numoptions>=9){
                 printCurrentAction(maintemp, 9);
                 doOption(9, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("j") && numoptions>=10){
                 printCurrentAction(maintemp, 10);
                 doOption(10, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("k") && numoptions>=11){
                 printCurrentAction(maintemp, 11);
                 doOption(11, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
             else if(input.equals("l") && numoptions>=12){
                 printCurrentAction(maintemp, 12);
                 doOption(12, currentroom, rooms);
                 addToHistory(currentroom, history);
                }
              else if(input.equals("r")){
                   Cyoa.main(args);//restarts program
                }
             else if(input.equals("y")){
                  //show information about the adventure. Prints one room per line 
                  //including tag of room, then tags for destinations of all possible options
                  //in that room
                  
                  System.out.println("[information]");
                  mergeSort(orderedrooms);
                  System.out.println();
                  printDestinations(orderedrooms);
                  System.out.println();
                   //DEBUG: System.out.println("CURRENT ROOM IS "+ currentroom);
                  printDescription(rooms[currentroom]);
                  //System.out.println();
                }
              else if(input.equals("z")){ //if user types in "z"
                  //historyid = number of elements in list
                   if(historyid>=0 &&history.next!=null){ //if you can go back...
                        historyNode temp2 = history; //copy the history list
                        while(temp2.next.next!=null){ //increment to the second to last node
                            temp2 = temp2.next;
                        }
                        currentroom = temp2.num; //get assign the room in history to be the current room
                        temp2.next = null; //delete the last node (Garbage Collector For The Win!!)
                       //Debugging: System.out.println("CURRENT ROOM IS "+ currentroom);
                        System.out.println("[undo]");
                        printDescription(rooms[currentroom]);//then print this room's description and options to the user
                        historyid--; //decrease the number of elements in the history list
                    }
                    else{
                        System.out.println("Invalid input");
                    }
                }
                else{
                    System.out.println("Invalid input");
                }
              //Debugging: printHistory(history);
            } 
       

    }
   
    public static void printCurrentAction(node maintemp, int x){
                 while(maintemp != null){
                     if(maintemp.command=='o'){
                         x--;
                         if(x==0){
                             System.out.println("["+maintemp.text+"]"+ "\n");
                             break;
                        }
                        }
                       maintemp= maintemp.next;
                    }
    }
    private static class historyNode{
        int id;//number in linked list
        int num; //room number
        historyNode next;
    }
    private static class node{
        char command;//is line a command
        String text;// anyting after the descriptoin
        node next;
    }
    public static void processfile(Scanner s2, node current, int i, node[] rooms)throws IOException{
       while(s2.hasNextLine()){
           int x = 0;
          // while(x< numrooms){
           String temp = s2.nextLine();
           if(temp.length()==0){
                //s1.nextLine();
                //String temp2 = s1.nextLine();
              
                i++;
                //node newNode = new node();
                //rooms[i] = newNode;
                processfile(s2,rooms[i], i, rooms);
                //current = current.next;
                //processfile(s1, current);
            }
           else{
             node newLink = new node();
             char d = temp.charAt(0);//this works
             String []desc = temp.split(" ",2);
            if( isEmpty(current)){
                newLink.command = d;
                newLink.text = desc[1];
                //current = newLink;
                //System.out.println(current.command + "   "+current.text);
                 //current = current.next;
                rooms[i]= newLink;
                processfile(s2,rooms[i], i, rooms);
            }
            else{
              newLink.command = d;
              newLink.text = desc[1];//gets the text that goes with it
              current.next = newLink;
              //current = newLink;
              //System.out.println(current.command + "   "+current.text);
              current = newLink;
               processfile(s2, current, i, rooms);
            }
             }
        }
    }
    
    public static void printDescription(node room){
        node temp = room;
        int i =0;
        while(temp !=null){
            if(temp.command =='d'){
                System.out.println(temp.text);
            }
            else if(temp.command == 'r' || temp.command=='t'){
                //do nothing
            }
            else{
               if(i == 0){
                   System.out.println();
                }
                System.out.println(alphabet[i] +"  -  " + temp.text);
                i++;
            }
            temp = temp.next;
        }
        numoptions = i;
         
    }
    public static void printDestinations(node[] rooms){
        
        for(int i =0;i<rooms.length;i++){
            node temp = rooms[i];
            System.out.print("\n"+ temp.text+": ");
        while(temp !=null){
            if(temp.command =='t'){
                System.out.print(temp.text+" ");
            }
            temp = temp.next;
        }
       }
    }
    public static void addToHistory(int num, historyNode history){
               
                   
                       historyNode temp = history; //create a temp history node
                      historyNode newNode = new historyNode(); //create a brand new history node
                     newNode.id = historyid; // index of the new node in the history list
                   //debugging: System.out.println("historyid = "+ historyid);
                     newNode.num = num; //
                    for(int i = historyid-1; i>0; i-- ){ //gets to end of history linked list
                     temp= temp.next;
                    }
                     temp.next = newNode;
                    // debugging: System.out.print(newNode.id+ "- - - "+ newNode.num);
                    historyid++; //one more node has been added to the list
                
    }
    public static void doOption(int x, int room, node []rooms){//prints the options
        node temp = rooms[room];
        
        while( temp != null){
            if(temp.command == 'o'){
                x--;
                if(x ==0){
                    //find the next room
                    String find = temp.next.text; //find = the room name you want to go to
                    //System.out.println(temp.next.text);
                    for(int i =0; i< numrooms; i++){
                        node temp2 = rooms[i].next;
                            
                           if(rooms[i].text.equals(find)){//found the right room
                               
                               //System.out.println("What I want : " + find+ " What I got "+ temp2.text);
                               printDescription(temp2);
                               currentroom = i;
                              // System.out.println("CURRENT ROOM" + currentroom);
                              System.out.println();
                               return;
                           }
                        
                    }
                    //if destination tag is incorrect, code ends up here:
                     System.out.println("Destination tag does not match any room tags");
                     System.exit(1);//exit with error
                }
            }
            temp = temp.next;
        }
    }
      public static node[] mergeSort(node[] temp) {
          node[]names = temp;
        if (names.length >= 2) {
            node[] left = new node[names.length / 2];
            node[] right = new node[names.length - names.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = names[i];
            }

            for (int i = 0; i < right.length; i++) {
                right[i] = names[i + names.length / 2];
            }

            mergeSort(left);
            mergeSort(right);
            merge(names, left, right);
        }
        return names;
    }

    public static void merge(node[] names, node[] left, node[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            
            if (b >= right.length || (a < left.length && left[a].text.compareToIgnoreCase(right[b].text) < 0)) {
                names[i] = left[a];
                a++;
            } else {
                names[i] = right[b];
                b++;
            }
        }
    }
     public static boolean isEmpty(node n){//check to see if it is empty
        if(n == null){
            return true;
        }
        else{
            return false;
        }
    }
     /**
     * printHistory() is used for debuging of the history linked list
     * 
     */
    public static void printHistory(historyNode history){ 
        System.out.println("There are "+ historyid+ " elements in the history");
        historyNode temp = history;
        while(temp !=null){
            System.out.println("INDEX: "+ temp.id + " ROOM stored: "+ temp.num);
            temp = temp.next;
        }
    }
    /**
     * printAll() is used for debuging of the rooms linked list
     * 
     */
    public static void printAll(node[] room){ //used for debugging purposes
         for(int i =0; i<numrooms;i++){
             System.out.println("ROOM "+ i);
             node temp = room[i];
             while(temp != null){
                 System.out.println(temp.command+ "_____ " +temp.text);
                 temp= temp.next;
                }
        }
    }
}
