
/**
 * This class creates a monkey with monkey properties
 * 
 * @author Ramzey Ghanaim
 * @version 1/30/14
 */
public class Monkey extends Animal implements NonFlyer
{
    public Monkey(String type)
    {
        super(false,true);
    }
    
    public void eat()
    {
        System.out.println("fruit");
    }
    
    public void hair()
    {
        System.out.println("fur");
        
    }
    
    public void sound()
    {
        System.out.println("chatters");
    }
    
    public void movement()
    {
        System.out.println("jumps");
    }
}
