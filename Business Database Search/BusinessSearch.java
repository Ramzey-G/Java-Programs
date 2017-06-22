import java.io.*;
import java.util.Scanner;

/**
 * This program runs with one argument: File name.extention
 * To make an executable program type 'make myBusiness'
 * to run the program type 'BusinessSearch filename.extention'
 * Then the program will run smoothly
 * 
 * The program searches a database (txt file) for phone numbers
 * Search Alogritm: Binary search.
 * @author myName 
 * @version 1.0
 */
public class BusinessSearch
{
   
    public static void main(String [] args) throws IOException{
        if(args.length==0){ //if there are no command line arguments
            System.out.println("To run program type: BusinessSearch DBname.extention");
            System.exit(0);
        }
        
        System.out.println("Help Menu: Type 'E' to Exit, 'S' to start the program");
        Scanner begin = new Scanner(System.in);
        String startOrEnd = begin.nextLine();
         int nFound =0;
         int counter = 0;
         int end = 0;
        
        if(startOrEnd.compareToIgnoreCase("E")==0){
            System.out.println("\n"+ counter+" total queries, "+ nFound+" not found.");
            System.exit(0);
        }
       else if(startOrEnd.compareToIgnoreCase("S")==0){
            String file = args[0];
            String[] data = fillArray(file); //fills the array
            String [] namenums =trimArray(data,file);//puts names and nums in seperate indicies in one array
            String[] bnames = getNames(namenums);//puts names in their own array called bnames
            String[] bnumbers =getNumbers(namenums);//puts numbers in their own array called bnumbers
            mergeSort(bnames, bnumbers); //sorts business names with mergeSort algorithm and numbers
             while (end ==0){ //makes an infinite loop to keep program going
            System.out.println("Please enter a business or 'E' to Exit: ");
            Scanner input = new Scanner(System.in);
            String nameSearch = input.nextLine();
               if(nameSearch.compareToIgnoreCase("E")==0){ //check for exit command
                   System.out.println("\n"+ counter+" total queries, "+ nFound+" not found."); //prints results
                 System.exit(0); //exits program
                }
            int x= findBusiness(nameSearch, bnames); //Binary Search, int x is the index for the name and corresponding number
            if(x>=0 && x< bnames.length){ //if x is a valid index...
                System.out.println(bnumbers[x]);//...print out the number at that index
                counter++;// increase counter
            }
            else{// if user input does not match anything in the DB...
             System.out.println("NOT FOUND"); //tell the user
             counter++; //increase counter
             nFound++;//increase number of not found elements
            }
          }// ends "S" loop      
        }
          else{ //if the user didn't enter a valid command to end or start the program...
            System.out.println("You entered an incorrect command. Please try again");//tell user
            main(args);// re-run the program
        }
    }
    /**
         *  fillArray() method Scanns the file with stdin and feeds each line into 
         *  an array called data and returns it.
         *     
         *     @param names, numbers
         *     @return data
         */
    public static String[] fillArray(String file) throws IOException{
        Scanner s1 = new Scanner(new File (file));//loads file
        int size = s1.nextInt(); //reades first line (number of contents in file)
        s1.nextLine();//skips first line so it is not put in array
        String[] data = new String[size];//creates a new array of data
        //FIlls the array:
        for( int i =0;i<size;i++){
            data[i] = s1.nextLine();
        }
        return data;
    }
    /**
         *  display() just takes in two arrays and prints them on the screen
         *  
         *  THIS METHOD IS USED FOR DEBUGGING PURPOESES ONLY
         *     
         *     @param names, numbers
         */
    public static void display(String[] names, String[] numbers){
        for(int i =0; i<names.length;i++)
        {
            System.out.print(i+1 +": "); //debugging purposes
            System.out.println(names[i]);//each name
            System.out.println(numbers[i]); //each number
        }
    }
    /** trimArray seperates names and numbes into seperet indexies.
         * EX: trimArray[0] = Google, trimArray[1] = Google's number
         *     trimArray[2]= Intel, trimArray[3]= Intel's number
         *     
         *     @param data, file
         *     @return trimmed array
         */
    public static String[] trimArray(String []data, String file)throws IOException{ //gets business names by themselves
       String[] ar = new String[data.length*2];
       Scanner s2 = new Scanner(new File (file));
       int name =0; //keep strack of business name
       int numbers =1;//keeps track of business number
       s2.nextLine();//skips first line so it is not put in array
        for(int i =0; i< data.length; i++){
           String temp = s2.nextLine();
           String artemp[] = temp.split(",", 2);
           ar[name] = artemp[0];
           ar[numbers] = artemp[1];
           name+=2;
           numbers +=2;
        }
        return ar;
    }
    /**
     * findBusiness() is a binary search algorithm which takes the string
     * of ordered names of businesses and a user input to search if the user's
     * input exits in the array of names. If so, it returns the index the name
     * is at.
     * 
     * @param key, names []
     * @return index
     */
       public static int findBusiness(String key, String[]names){
       int lowerBound = 0;
       int upperBound  = names.length;
 
       while (lowerBound < upperBound) {
        int mid = (lowerBound + upperBound) >>> 1; //same thing as (lower+upper )/2, but more effecient in java language
        if (key.compareToIgnoreCase(names[mid]) < 0) { //if user input is in the lower half
            upperBound = mid; //change the middle to the upper bouand and search again
        } else if (key.compareToIgnoreCase(names[mid]) > 0) {//if user input is in the upper half of the array
            lowerBound = mid + 1; //change the lower bound to the middle+1
        } else { //if all else fails, the middle is the answer!!
            return mid;
        }
     }
     return -(lowerBound + 1);// if nothing works, return a neative number
    }
    
    public static String[] getNames(String [] namenums){
        String[] namesOnly= new String[namenums.length/2];
        int nameCounter=0;//keeps track of names in parameter's indicies
        for(int i=0; i<namesOnly.length;i++){
           namesOnly[i] = namenums[nameCounter];
           nameCounter+=2;
        }
        return namesOnly;
    }
    public static String[] getNumbers(String [] namenums){
        String[] numbersOnly= new String[namenums.length/2];
        int numberCounter=1;//keeps track of numbers in parameter's indicies
        for(int i=0; i<numbersOnly.length;i++){
           numbersOnly[i] = namenums[numberCounter];
           numberCounter+=2;
        }
        return numbersOnly;
    }
    
    /**
     * The two methods below: mergeSort and merge was from Stack Overflow
     * http://stackoverflow.com/questions/20795158/sorting-names-using-merge-sort
     * 
     * I modfied the code form this link to suit my needs for this project and studied
     * how the code works for my own knowledge.
     * 
     */
     
    public static void mergeSort(String[] names, String []numbers) {
        if (names.length >= 2) { //if the array is greater thant two..
            String[] leftnames = new String[names.length / 2];//seperate left of names array
            String[] leftnumbers = new String[names.length/2];// seperate left of numbers array
            String[] rightnames = new String[names.length - names.length / 2];//seperates right of names array
            String[] rightnumbers=new String[names.length - names.length /2];//seperates right of numbers array

            for (int i = 0; i < leftnames.length; i++) { //filling left name and numbers array
                leftnames[i] = names[i]; 
                leftnumbers[i]=numbers[i];
            }

            for (int i = 0; i < rightnames.length; i++) { //filling right names and numbers array
                rightnames[i] = names[i + names.length / 2];
                rightnumbers[i] = numbers[i+names.length/2];
            }

            mergeSort(leftnames,leftnumbers); //sort left of the arrays
            mergeSort(rightnames,rightnumbers);//sort right of the arrays
            merge(names, leftnames, rightnames,numbers,leftnumbers, rightnumbers);// merge them together
        }
    }
    public static void merge(String[] names, String[] leftnames, String[] rightnames, String [] numbers, String[] leftnumbers, String[] rightnumbers) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < names.length; i++) {
            if (b >= rightnames.length || (a < leftnames.length && leftnames[a].compareToIgnoreCase(rightnames[b]) < 0)) {//checks which index is higher
                names[i] = leftnames[a];
                numbers[i]= leftnumbers[a];
                a++;
            } else {
                names[i] = rightnames[b];
                numbers[i] = rightnumbers[b];
                b++;
            }
        }
    }
    
}
