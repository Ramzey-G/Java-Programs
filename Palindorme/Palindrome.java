import java.util.Scanner;
/**
 * This class checks to see if a word is a palindrome
 * 
 * @author Ramzey Ghanaim 
 * @version 1/14/2014
 */
public class Palindrome
{
    
   public static void reverse()
   {
   Scanner input = new Scanner(System.in);
   System.out.println("Enter a word ");
   String name= input.nextLine();
   String s1= new String(name);
   
   String trimmed = s1.replace(" ","");
   String trimmed1 = trimmed.replace(".","");
   String trimmed2=trimmed1.replace("!","");
   String trimmed3=trimmed2.replace(",","");
   String trimmed4=trimmed3.replace("'","");
   String trimmed5 = trimmed4.toLowerCase();
   int length = trimmed5.length();
   int start = 0;
   if(trimmed5.charAt(start) == trimmed5.charAt(length-1))
   {
        start ++;
        length--;
        if(start==length);
        {
            System.out.println("This is a palindrome");
        }
        
    }
    else
    {
        System.out.println("This is not a palindorme");
    }
  }
}
