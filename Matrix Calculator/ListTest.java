/**
 *		ListTest.java
 *	This program is a Tester file for List.java
 *
 *
 *	@author: Ramzey Ghanaim
 *
 *
 * */

public class ListTest{
	public static void main(String [] args){
		List L = new List(); //create a new list
		int x = 10; //make a couple of random ints
		int k = 20;
		L.append(x); //insert
		List Ltwo = new  List(); //make new list
		Ltwo.prepend(x); //insert same value k as list L
	//	System.out.println("List one : "+ L);
		L.moveFront(); Ltwo.moveFront(); //move both to front
		while( k> 0){ //insert multiples of five
			L.insertBefore(k);
			Ltwo.insertBefore(k);
			k -=5;
			L.moveFront();
			Ltwo.moveFront();

		}
		System.out.println("List one: " + L+" \nList two : "+Ltwo); //print both lists (Should be same)
		System.out.println("List one equals List two: "+ L.equals(Ltwo)); //test equals method (should be true)

		k = 20; //put k back at 20
		while( k >0){  //insert multiples of two in Ltwo working backward
			Ltwo.append(k);
			k -=2;
			Ltwo.moveBack();
		}
		System.out.println("List two : "+ Ltwo); //now print this modefied list
		System.out.println("List one equals List two: "+ L.equals(Ltwo));//test equals method (should be false)

		Ltwo.moveBack(); //move back
		System.out.print("Print List two in reverse: "); //used to check movePrev()
		for(int i = 0;i <Ltwo.length(); i++){
			System.out.print(Ltwo.get()+" ");
			Ltwo.movePrev();
		}
		L.moveFront(); //move front for first list
		
		System.out.println("\nBack element of List one is: "+ L.back());//check back() works
		L.deleteBack(); 
		System.out.println("List 1 with back element deleted: " + L);//check deleteBack() works (data of value 10 should be deleted)
		k = 10;
		L.prepend(k);
		System.out.println("Putting the element back in the front: " + L); //put the value of 10 in front. Check prepend()
		
		L.moveBack(); L.movePrev(); //move to second to last element
		L.moveNext(); //check move next works and move to second to last element
		int t = 42;
		L.insertAfter(t);
		L.insertBefore(t);
		System.out.println("inserting 42 before and after the second to last element: "+ L); //check insertBefore and insertAfter
		L.deleteFront();
		System.out.println("Deleting front element  from List one: "+ L); //check deleteFront()
		
		int index = 2;
		Ltwo.moveBack(); Ltwo.movePrev();
		while(index >0){ //delete back 2 elements of Ltwo
			Ltwo.delete();
			Ltwo.moveBack();
			index --;
		}
		System.out.println("Deleting second to last 3 elements of List two: "+ Ltwo); //check delete() works
		System.out.println("List one equals list two: " +L.equals(Ltwo)); //check if equals works
		Ltwo.clear(); //clear Ltwo

		//If i print Ltwo, i will get Runtime exception error (which is good because now Ltwo is empty)
	}
}
