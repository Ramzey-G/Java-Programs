
/**
 * This class creates a Bat with a bat's characteristics. Some
 * of these characterstics are inherited from the Animal and Flyer classes
 * 
 * @author Ramzey Ghanaim
 * @version 1/30/14
 */
public class Bat extends Animal implements Flyer
{
    public Bat (String type)
    {
        super(true,true);
    }
    
    public void eat()
    {
        System.out.println("furit and insects");
    }
    
    public void hair()
    {
        System.out.println("fur");
    }
    public void sound()
    {
        System.out.println("none");
    }
    public void takeoff()
    {
        System.out.println("launches from the tree");
    }
    
    public void land()
    {
        System.out.println("hangs on a rafter");
    }
}
