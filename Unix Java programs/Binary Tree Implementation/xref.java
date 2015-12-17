//xref.java
//main application that runs the program
import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

class xref {

    static void processFile(String filename, boolean debug) throws IOException {
        Scanner scan = new Scanner (new File(filename));
        Tree tree = new Tree();
        for (int linenr = 1; scan.hasNextLine (); ++linenr) {
            for (String word: scan.nextLine().split ("\\W+")) {
               // out.printf ("%s: %d: %s%n", filename, linenr, word);
                Integer Integerline = new Integer(linenr);
               // System.out.print(Integerline);
                tree.insert(word,Integerline);
            }
        }
        scan.close();
        if (debug) {
          //  System.out.print("DEBUG + ture");
             tree.debug();
        } else {
           tree.output();
        }
    }

    public static void main(String[] args) {
        boolean debug = false;
        String filename= "";
       if(args[0].equals("-d")){
          debug = true;
          //System.out.print("DEBUG = TRUE");
           if(args.length ==2)
              filename = args[1];
            else{
                System.out.println("No file name");
                System.exit(1);
               }     
        }
      else{
        if(args.length == 1)
             filename = args[0];
        else {
             System.out.println("No file name");
             System.exit(1);
        }}
        try {
            processFile(filename, debug);
        }catch (IOException error) {
            
            auxlib.warn (error.getMessage());
        }   
    
}
}
