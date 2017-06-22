// dllistTest.java
// Unit tests for dllist

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class dllistTest {


    //Test to see if isEmpty is true when there is nothing in it
    @Test
    public void startsEmptyTest() {
        dllist lst = new dllist();
        assertEquals(true, lst.isEmpty());
    }
    //Test to see if insert is working for one element
    @Test
    public void insertOne(){
        dllist thing = new dllist();
        thing.insert("You", dllist.position.FIRST);
        assertEquals("You", thing.getItem());
    }
    //Simple insert LAST test
    @Test
    public void insertTwoLast(){
        dllist thing = new dllist();
        thing.insert("Youre", dllist.position.LAST);
        thing.insert("Awesome", dllist.position.LAST);
        assertEquals("Awesome", thing.getItem());
    }
    //Test to see if insert for any position is working
    @Test
    public void insertMore(){
        dllist thing = new dllist();
        thing.insert("The", dllist.position.FIRST);
        thing.insert("Banana", dllist.position.LAST);
        assertEquals("Banana", thing.getItem());
        thing.insert("Angry", dllist.position.PREVIOUS);
        assertEquals("Angry", thing.getItem());
        thing.insert("Mean", dllist.position.FOLLOWING);
        assertEquals("Mean", thing.getItem());
    }
    //Test to see if my position pointer is pointing to the right spot
    @Test
    public void getPosTest(){
        dllist thing = new dllist();
        thing.insert("The", dllist.position.FIRST);
        assertEquals(0, thing.getPosition());
        thing.insert("Banana", dllist.position.LAST);
        assertEquals(1, thing.getPosition());
        thing.insert("Angry", dllist.position.PREVIOUS);
        assertEquals(1, thing.getPosition());
        thing.insert("Mean", dllist.position.FOLLOWING);
        assertEquals(2, thing.getPosition());
    }
    //Test to see if setPos works for all positions
    @Test
    public void random(){
        dllist thing = new dllist();
        thing.insert("The", dllist.position.FIRST);
        thing.insert("Big", dllist.position.FOLLOWING);
        thing.insert("Banana", dllist.position.LAST);
        thing.insert("Angry", dllist.position.PREVIOUS);
        thing.insert("Mean", dllist.position.FOLLOWING);
        thing.setPosition(dllist.position.FIRST);
        assertEquals(0, thing.getPosition());
        assertEquals("The", thing.getItem());
        thing.setPosition(dllist.position.FOLLOWING);
        assertEquals(1, thing.getPosition());
        assertEquals("Big", thing.getItem());
        thing.setPosition(dllist.position.LAST);
        assertEquals(4, thing.getPosition());
        assertEquals("Banana", thing.getItem());
        thing.setPosition(dllist.position.PREVIOUS);
        assertEquals(3, thing.getPosition());
        assertEquals("Mean", thing.getItem());
        thing.setPosition(dllist.position.PREVIOUS);
        assertEquals(2, thing.getPosition());
        assertEquals("Angry", thing.getItem());
    }
    
    //Test to check if isEmpty is working
    @Test
    public void emptyTest(){
        dllist thing = new dllist();
        assertEquals(true, thing.isEmpty());
        thing.insert("The", dllist.position.PREVIOUS);
        assertEquals(false, thing.isEmpty());
    }
    //test to see if delete is working
    @Test
    public void deleteOneTest(){
        dllist thing = new dllist();
        thing.insert("The", dllist.position.FOLLOWING);
        thing.delete();
        assertEquals(true, thing.isEmpty());
    }
    //test to see if current is going to the correct node after a many deletes
    @Test
    public void deleteManyTest(){
        dllist thing = new dllist();
        thing.insert("The", dllist.position.FIRST);
        thing.insert("Big", dllist.position.FOLLOWING);
        thing.insert("Angry", dllist.position.FOLLOWING);
        thing.insert("Man", dllist.position.FOLLOWING);
        thing.insert("Ate", dllist.position.FOLLOWING);
        thing.insert("My", dllist.position.FOLLOWING);
        thing.insert("Marshmallows", dllist.position.LAST);
        thing.setPosition(dllist.position.FIRST);
        thing.setPosition(dllist.position.FOLLOWING);
        thing.delete();
        assertEquals("Angry", thing.getItem());
        assertEquals(1, thing.getPosition());
        thing.setPosition(dllist.position.FIRST);
        thing.delete();
        assertEquals("Angry", thing.getItem());
        assertEquals(0, thing.getPosition());
        thing.setPosition(dllist.position.LAST);
        thing.delete();
        assertEquals("My", thing.getItem());
        assertEquals(3, thing.getPosition());
    }
    //More tests for setPositon
    @Test
    public void morePositionTests(){
        dllist thing = new dllist();
        thing.insert("My", dllist.position.FIRST);
        thing.insert("leg", dllist.position.FIRST);
        thing.setPosition(dllist.position.LAST);
        assertEquals("My", thing.getItem());
    }
    //Tests insert indexes
    @Test
    public void complex(){
        dllist thing = new dllist();
        thing.insert("A", dllist.position.LAST);
        thing.insert("B", dllist.position.LAST);
        thing.insert("C", dllist.position.LAST);
        thing.insert("D", dllist.position.PREVIOUS);
        thing.setPosition(dllist.position.LAST);
        assertEquals("C", thing.getItem());
    }
    //opposite of complex()
    @Test
    public void reverseComplex(){
        dllist thing = new dllist();
        thing.insert("A", dllist.position.FIRST);
        thing.insert("B", dllist.position.FIRST);
        thing.insert("C", dllist.position.FIRST);
        thing.insert("D", dllist.position.FOLLOWING);
        thing.setPosition(dllist.position.FIRST);
        assertEquals("C", thing.getItem());
    }

    //Test to see if all functions work well with eachother
    @Test
    public void all(){
        dllist thing = new dllist();
        thing.insert("THREE", dllist.position.FIRST);
        assertEquals(0, thing.getPosition());
        thing.insert("ONE", dllist.position.FIRST);
        assertEquals(0, thing.getPosition());
        thing.insert("TWO", dllist.position.FOLLOWING);
        assertEquals(1, thing.getPosition());
        thing.insert("FIVE", dllist.position.LAST);
        assertEquals(3, thing.getPosition());
        thing.insert("FOUR", dllist.position.PREVIOUS);
        assertEquals(3, thing.getPosition());
        thing.setPosition(dllist.position.FIRST);
        assertEquals(0, thing.getPosition());
        assertEquals("ONE", thing.getItem());
        thing.delete();
        assertEquals("TWO", thing.getItem());
        thing.delete();
        thing.delete();
        thing.delete();
        thing.delete();
        assertEquals(true, thing.isEmpty());
    }}
