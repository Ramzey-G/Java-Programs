import javax.swing.JOptionPane;
/**
 * Part 5: Write a program to simulate a login process for a website. 
 * Allows the user to input a String for the username and an 
 * int for a password
 * 
 * @author Ramzey Ghanaim
 * @version 2/6/2014
 */
public class PasswordDataBase
{
    public static void main(String [] args)
    {
    String [] username= {"Google", "Pirates", "Doctor","Walter White"};//first username is matched with the first password. EX: the password for Google is 412
    int [] password = {412, 123, 1, 5};
    String user = JOptionPane.showInputDialog("Enter your username (not case sensitive)");
    int pass = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter your password pin"));
    int i = 0;
    while(i<username.length)
    {
        while(i<username.length)
        {
        /**
         * i will be the username/password combo since the correct
         * username and password combination are stored in the same locations in the arrays
         */
        if(username[i].equalsIgnoreCase(user) && password[i] == pass) 
        {
            System.out.println(username[i] + ", You have successfully logged in");
            return;
        }
        else {
         i ++;
        }
    }
        i = 0;
        while(i<username.length)
        {
        
          if(!username[i].equalsIgnoreCase(user) || password[i]!=pass)
          {
            System.out.println("Login unsuccessful...try again");
            return;
          }
          i++;
       }
    }
    
}
}