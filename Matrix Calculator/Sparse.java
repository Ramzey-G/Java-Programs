import java.io.*;
import java.util.Scanner;

/*
 *           Sparse.java
 * Sparse.java takes in a file and output file. the input file
 * is read and placed into two matricies. then matrix operations
 * are done using the Matrix class. the results of these operations 
 * are saved to the output file
 *
 * usage: java Sparse <inputfile> <outputfile>
 *
 *
 * @author: Ramzey Ghanaim
 *
 *
 *
 *
 *
 **/
 public class Sparse{
 public static void main(String [] args) throws IOException{
	if(args.length !=2){ //make sure we have correct arguements
		System.out.println("stderr");
		System.exit(1);
		}
	try{
	String file = args[0]; //assign input file
	String fileOut=args[1];//assign output file

	Scanner in = new Scanner(new File(file));//read file
	String line = in.nextLine(); //read the line
	String[] seperate = line.split(" "); // split data into multiple strings
	
	Matrix A; //crate matricies
	Matrix B;
	 A = new Matrix(Integer.parseInt(seperate[0])); //get correct sizes
	 B = new Matrix(Integer.parseInt(seperate[0]));

	int a = Integer.parseInt(seperate[1]); //get correct NNZ
	int b = Integer.parseInt(seperate[2]);
	
	in.nextLine(); //skip to next line
	for(int i = 1; i<=a; i++){ //the next a lines contain data for first matrix
		int row = in.nextInt();
		int col = in.nextInt();
		double data = in.nextDouble();
		A.changeEntry(row, col, data); //asisgn values from file to matrix
	}
	in.nextLine(); //skip to next line
	for(int i=1;i<=b;i++){ //the next b lines contain data for the second matrix
		int row = in.nextInt();
		int col = in.nextInt();
		double data = in.nextDouble();
		B.changeEntry(row, col, data); //assign values from file to matrix
	}
	
	PrintWriter out = new PrintWriter(args[1]); //assign an output file to a printwriter called out


	out.println("A has " + a+ " Non-Zero entries");
	out.println(A);
	out.println("B has " + b+ " Non-Zero entries");
	out.println(B);

	out.println("(1.5)*A = ");
	out.println(A.scalarMult(1.5));

	out.println("A + B = ");
	out.println(A.add(B));

	out.println("A+A = ");
	out.println(A.add(A));

	out.println("B-A = ");
	out.println(B.sub(A));

	out.println("A-A =");
	out.println(A.sub(A));

	out.println("Transpose(A)= ");
	out.println(A.transpose());

	out.println("A*B = ");
	out.println(A.mult(B));

	out.println("B*B = ");
	out.println(B.mult(B));

	out.close(); in.close();
	}

	//if inputfile could not be found:
	catch(IOException e){
		System.out.println("Can't find input file");
		System.exit(1);
	}

 }

 }
