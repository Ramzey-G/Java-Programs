
/**
 * This class creates characteristics of an animal
 * 
 * @author Ramzey Ghanaim 
 * @version 1/30/14
 */
public abstract class Animal
{
    private boolean wings = false;
    private boolean legs = false;
    protected Animal(boolean wings, boolean legs)
    {
        wings = wings;
        legs = legs;
    }
        
    protected abstract void sound();
    
    protected abstract void hair();
    
    
    protected abstract void eat();
    
    
    
}
