
/**
 * This is a tester class that runs the program. It creates new animals
 * (Bat, Hawk, Monkey) and displays the animals characteristics
 * 
 * @author Ramzey Ghanaim
 * @version 1/30/2014
 */
public class AnimalTest
{
    public static void main (String [] args)
    {
        Snake s = new Snake("cobra"); 

Bat b = new Bat("fox");

Hawk h = new Hawk("redtail"); 

Monkey m = new Monkey("spider"); 

Animal a = new Animal(true,false); 
//You cannot instantiate an absttract class or method
Animal z = new Hawk();

NonFlying f = new Monkey();

s.movement();

System.out.println("Snakes have legs? "+s.legs+" Snakes have wings? "+s.wings);

b.takeoff(); 

z.eat(); 

z.hair(); 

z.sound();

f.movement();
    }
}
