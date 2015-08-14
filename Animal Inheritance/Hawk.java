
/**
 * This Class creates a hawk and gives it properties of hawks
 * 
 * @author Ramzey Ghanaim
 * @version 1/30/14
 */
public class Hawk extends Animal implements Flyer
{
    public Hawk (String type)
    {
        super(true,true);
    }
    
    public void eat()
    {
        System.out.println("fruit and inscets");
        
    }
    
    public void hair()
    {
         System.out.println("feathers");
    }
    
    public void sound()
    {
        System.out.println("Screeches");
    }
    
    public void takeoff()
    {
        System.out.println("glides");
    }
    
    public void land()
    {
        System.out.println("perches on a tree top");
    }
}
