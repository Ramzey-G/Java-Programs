// edfile.java
// Template for a line-oriented text editor inspired by ed.
import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

class edfile extends dllist{

    public static void main (String[] args) {
        System.out.println("TEXT EDITOR");
        dllist lines = new dllist ();
        boolean want_echo = false;
        if(args.length == 0) {//checking default arguments
                //do nothing
        }
        else if(args.length == 1){//checkin for a filename
            try{
                Scanner input = new Scanner(new File(args[0]));
                while(input.hasNextLine()){
                    lines.insert(input.nextLine(), position.FOLLOWING);
                }
            }
	    catch(FileNotFoundException e){//check for "-e"
	        if(args[0].equals("-e")){
                    want_echo = true;
                }else{
                    System.out.println("File not found");
                    System.out.println("USAGE: edfile [-e] [filename]");
                    System.exit(1);
	        }
	    }
        }
        else if(args.length ==2){//check for "-e"
            if(args[0].equals("-e") || args[1].equals("-e")){
                want_echo = true;
            }
            else{//error. user entered multiple file names
                System.out.println("Please only enter one file at a time");
                System.out.println("USAGE: edfile [-e] [filename]");
                System.exit(1);
            }
            try{
	        String name = args[0];
	        Scanner input = new Scanner(new File(name));
                while(input.hasNextLine()){
                    lines.insert(input.nextLine(), position.FOLLOWING);
                }	    
	    }
            catch(FileNotFoundException e1){
                try{
                    String name = args[1];
                    Scanner input2 = new Scanner(new File(name));
                    while(input2.hasNextLine()){
                        lines.insert(input2.nextLine(), position.FOLLOWING);
                    }
                }
                catch(FileNotFoundException e2){
                    System.out.println("FILE WAS NOT FOUND");
                    System.exit(1);
                }
            }
        }
        else{
            System.out.println("USAGE: edfile [-e] [filename]");
	    System.exit(1);
        }
        System.out.println("Edit your texts here, and read and write to others");
        System.out.println("To exit the program, type in 'eof' and enter");
        System.out.println("Please always use spaces after the command character, if necessary.");
	Scanner stdin = new Scanner (System.in);
        for (;;) {//infinite loop that checks for user input
            if (! stdin.hasNextLine()){ break; }
            String inputline = stdin.nextLine();
            if (want_echo){ out.printf ("%s%n", inputline);}
            if (inputline.matches ("^\\s*$")){ continue; }
            char command = inputline.charAt(0);//getting the command of a user's input
            if(inputline.equals("eof"))//check if user wants to exit
                System.exit(0);
            switch (command) {//checking for the command
                case '#': break;//do nothing (ignore user input)
                case '$': //current line is set to last line. new current line is displayed
                    try{
	                lines.setPosition(position.LAST);
                        System.out.println(lines.getItem()); 
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
                    break;
                case '*'://all lines are displayed. current line becomes last line
                    int count = 0;
                    try{
                        lines.setPosition(position.LAST);
                        int max = lines.getPosition() + 1;
		        lines.setPosition(position.FIRST);
		        while(count < max){
		            System.out.println(lines.getItem());
		            lines.setPosition(position.FOLLOWING);
                            count++;
                        }
                        lines.setPosition(position.LAST);
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case '.'://current line is displayed
                    try{ 	
		        System.out.println(lines.getItem());
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case '0'://current line is set to first line of list. current line is displayed
                    try{
		        lines.setPosition(position.FIRST);
		        System.out.println(lines.getItem());
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case '<'://current line is set to prvious line. this line is displayed
	 	    try{
                        lines.setPosition(position.PREVIOUS);
		        System.out.println(lines.getItem());
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case '>'://current line is set to next line. this line is displayed
                    try{
                        lines.setPosition(position.FOLLOWING);
		        System.out.println(lines.getItem());
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case 'a'://new text after "a " is inserted... NEED TO HAVE A SPACE
                    try{
                        String[] text = inputline.split(" ", 2);
	                lines.insert(text[1], position.FOLLOWING);
                        System.out.println(lines.getItem());
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Please use spaces after command");
                    }
		    break;
                case 'd'://current line is deleted. the next line becomes current line
                    try{
		        lines.delete();
                    }catch(UnsupportedOperationException e){
                        System.out.println("The list is empty");
                    }
		    break;
                case 'i':// new text after the "i " chracter  is inserted before current line in format
                    try{
                        String[] before = inputline.split(" ", 2);
                        lines.insert(before[1], position.PREVIOUS);
                        System.out.println(lines.getItem());
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Please use spaces after command");
                    }
		    break;
                case 'r':// takes in file name to read file into list... needs a space after command
                    int counter = 0;
                    try{
                        String[] tortuga = inputline.split(" ",2);
                        String file = tortuga[1];
                        Scanner Nfile = new Scanner(new File(file));
                        while(Nfile.hasNextLine()){
                            lines.insert(Nfile.nextLine(), position.FOLLOWING);
                            counter++;
                        }
                        System.out.println("Number of lines inserted is "+ counter);
                    }catch(FileNotFoundException e){
                        System.out.println("Sorry, the file you are trying to read from does not exist");
                    }catch(ArrayIndexOutOfBoundsException e1){
                        System.out.println("USAGE: r filename");
                    }
                    break;
                case 'w'://writes the list to a text file with a file name after the "w " character (need a spcae after w)
                    try{
                        int c = 0;
                        lines.setPosition(position.LAST);
                        int m = lines.getPosition();
                        m = m + 1;
                        lines.setPosition(position.FIRST);    
                        String[] turtle = inputline.split(" ", 2);
                        PrintWriter out = new PrintWriter(new FileWriter(turtle[1]));
                        while(c < m){
                            out.println(lines.getItem());
                            lines.setPosition(position.FOLLOWING);
                            c++;
                        }
                        out.close();
                        System.out.println("Number of lines written: " + c);
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("USAGE: w filename");
                    }catch(IOException e1){
                        System.out.println("Can't write to file");
                    }catch(UnsupportedOperationException e2){
                        System.out.println("No items to write");
                    }
		    break; 
                default ://otherwise tell user they entered an invalid command
                    System.out.println("Invalid command");
                    System.out.println("Commands are: $,*,.,0,<,>,a text,d,i text,r filename,w filename");
                    break;
            }//end of switch
        }//end of for
    }//end of main
}//end of class
