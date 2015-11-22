
//Greet everyone listed in people.txt

import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
class greetings {
    public static void main( String[] args ) throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("people.txt"));
        while(true) {
            String name = in.readLine();
            if (name == null) break;
            String[] names = name.split(",");//splits name from number when it sees a coma
            System.out.println("Hello, " + names[0] + "."); //prints the first element in the array.
            //the first element in the array will be any text left of the coma (the name in this case)
                   }
                          in.close();
                      }
                           }
