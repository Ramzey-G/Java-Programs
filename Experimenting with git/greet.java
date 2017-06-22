
//greet.java
//Ask user to type their name, then says hello unitil
//CTRL+ c is typed

import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
class greet {
    public static void main( String[] args )throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
	while(true){        
            System.out.println("What is your name?");
            String name = in.readLine();
            System.out.println("Hello, " + name + ".");
        } 
        }
    }
