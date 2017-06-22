/**
 *			MatrixTest.java
 * This file tests Matrix to make sure all of its
 * functions work.
 *
 * @author: Ramzey Ghanaim
 *
 * */

public class MatrixTest{
	public static void main(String [] args){
	//create a matrix and enter some values (size 42)
		Matrix a = new Matrix(42);
			a.changeEntry(1,1,5.1);
			a.changeEntry(1,3,6.0);
			a.changeEntry(1,4,9.3949);
			a.changeEntry(2,1,64.93);
			a.changeEntry(2,3,69.99);
			a.changeEntry(2,2,7844.332);
			a.changeEntry(2,5, 6594.04);
			a.changeEntry(7,1,88.23);
			a.changeEntry(7,2,2338.23);
			a.changeEntry(7,9,56.34323);
		//matrix b is a copy of matrix a
		Matrix b = a.copy();	
		//print A and B. They should be the same. Then compare them
		System.out.println("Matrix A: \n"+ a);
		System.out.println("Matrix B: \n"+ b);
		System.out.println("Matrix A equals Matrix B :"+a.equals(b));

		//Now for arithmetic operations:
		System.out.println("\nA*B \n"+ a.mult(b));
		System.out.println("B+A \n"+ b.add(a));
		System.out.println("B-A \n"+b.sub(a));

		//Let's see what happens when we enter 0 into the matrix
		b.changeEntry(7,9,0.0);
		System.out.println("Matrix B Changed 7,9 entry to 0 \n"+b);

	
	
	//Make Matrix B zero
		b.makeZero();
		System.out.println("Matrix B is the Zero Matrix:\n"+b);

		//Scalar multiply
		System.out.println(" 1.5 * A: \n"+a.scalarMult(1.5));

		//check if A = b (hint: it should not!!)
		System.out.println("Matrix A equals Matrix B :"+a.equals(b));

	}

}
