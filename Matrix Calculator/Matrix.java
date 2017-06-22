
/**
 * 			Matrix.java
 * This file is in charge of making and minipulating matricies
 * Each entry in a matrix consists of a column and a value.
 * Each entry is placed in a List (from List.java). 
 * And each Matrix is an array of lists where the array is the
 * rows for the matrix. Each row has their own list and only
 * non zero entries are stored so large calculations can be done
 * easily.
 * 
 *
 *
 * @author Ramzey Ghanaim 
 */
public class Matrix
{
    //create Entry class
    private class Entry{
        int col;
        double data;
        //constructor
        Entry(int col, double data){
            this.col = col;
            this.data = data;
        }
		//check if entries are equal
        public boolean equals(Object x){
		if( x instanceof Entry && x !=null){
            if(this.col == ((Entry)x).col && this.data == ((Entry)x).data){
				return true;
            }
			}
			//System.out.println("\t\tcol " + col + "\n\t\t is not the same as" + ((Entry) x).col);
			//System.out.println("\t\tdata " + data + "\n\t\t is not the same as" + ((Entry) x).data);
			return  false;
        }

        public String toString(){
            return "("+ Integer.toString(col)+", "+Double.toString(data)+") ";
        }
    }
    //useful variables
    private int size;
    private List[] array;

   //Matrix constructor
   Matrix(int n){
        if(n>=1){
            array = new List[n+1];
            for(int i = 1; i<=n;i++){
                array[i] = new List();
            }
            size = n;
        }
        else{
            throw new RuntimeException("can not make Matrix less than size 1");
        }
    }  

    //access functions
	
	//getSize() returns the number of elements in one row
    int getSize(){ 
        return array.length-1;
    }

    //returns the Number of Non Zero entries (NNZ)
    int getNNZ(){
        int sum = 0;
        for(int i = 1; i<=size;i++){
            sum += array[i].length();
        }
        return sum;
    }

    //overrides Object's equals() method
	//
	//Checks to see if Matricies are equal
    public boolean equals(Object x){
        if(x instanceof Matrix){
            Matrix temp = (Matrix) x;
            if(this.size == temp.size){
                for(int i = 1; i<=this.size;i++){
					if(this.array[i].length() != temp.array[i].length()) return false;
					if(this.array[i].length() ==0 && temp.array[i].length() ==0) continue;
                    if(!(this.array[i].equals(temp.array[i]))) {
                        System.out.println("List " + this.array[i] + "\ndoesn't equal " + temp.array[i]);
						return false;
					}
                }
                return true;
            }
        }
        return false;
    }
    //manipulation procedures

    //ses matrix to zero state
    public void makeZero(){
        for(int i=1;i<=size;i++){
            this.array[i] = new List();
        }
    }

    //copy() returns a copy of a matrix
    public Matrix copy(){
        Matrix m = new Matrix(this.size);
        for(int i=1;i<=this.size;i++){
            this.array[i].moveFront();
            while(this.array[i].index() >= 0){
                Entry temp = ((Entry)this.array[i].get());
                m.changeEntry(i, temp.col, temp.data);
                this.array[i].moveNext();
            }
        }
        return m;
    }
    
    //changes ith row, jth column of this matrix to x
    //if 1<=i<=getSize(), 1,=j<=getSize()
	//
	//This method closely follows insertionSort from PA1
	
    public void changeEntry(int i, int j, double x){
        if((i< 1 || i> this.getSize()) || j<1 || j> this.getSize()){
            throw new RuntimeException("error on changeEntry: invalid index");
        }
            if(this.array[i].length() == 0) {
                if(x != 0.0){
                    Entry t = new Entry(j,x);
                    this.array[i].append(t);
                    return;
                }
				return;
            }
            //int len = this.array[i].length();
            this.array[i].moveBack(); //start in the back
            //Entry temp = new Entry(j,x);
			while(((Entry) array[i].get()).col >j){
				
				if(array[i].index == 0){ //inserting at the front of the list
				//	System.out.println("Inserting first "+ j +" val "+ x); 
					array[i].prepend(new Entry(j, x));
					return;
				}
				else{
					array[i].movePrev(); //continue moving back
				}
			}

			//if we got out of the while loop, we found the location
			//of where we want to place the new element
			Entry temp = (Entry) array[i].get();
			if(temp.col< j && x !=0){ //if you passed the desiered location and its NZ...
				array[i].insertAfter(new Entry(j, x));//...insert after current element
			}
			else if(temp.col == j){ //if you found the exact location...
				if(x == 0.0){
					array[i].delete(); //...and you want to insert 0, delete node
					return;
				}
				((Entry) array[i].get()).data = x;//otherwise update existing data value
			}
            
	}


    //returns anew Matrix that is the scalar product of this 
    //matrix with x
    public Matrix scalarMult(double x){
		//create a new matrix the same size as  this
        Matrix multiply = new Matrix(this.getSize());
        for(int i=1;i<=size;i++){
            this.array[i].moveFront();//always start at front of list
            while(this.array[i].index() >=0){ //increment thorugh list
                multiply.changeEntry(i, ((Entry) this.array[i].get()).col, ((Entry)this.array[i].get()).data * x); //multiply by scalar and insert into new matrix 
                this.array[i].moveNext();     //incrment column
            }      
        }
        return multiply; //return final matrix

    }

    // returns a new Matrix that is the sum of this Matrix with M
    // pre: getSize()==M.getSize()
    public Matrix add(Matrix M){
        if(!(M.getSize()==this.getSize())){//check if sizes are the same
            throw new RuntimeException("error: add(). size doesn't match");
        }

		//make a new matrix to put end  result in
        Matrix end = new Matrix(M.size);
        Matrix cp = M.copy(); //make a cpy of M so you don't change M's values
        for(int i=1;i<=size;i++){ //increment through rows
            if(this.array[i].length() == 0){ //if first matrix's list is empty....(no adding needed. Just insert)
                cp.array[i].moveFront();
                while(cp.array[i].index() >= 0){ //itterate through lsit/row
                    Entry temp = ((Entry) cp.array[i].get());// extract entry
                    end.array[i].append(new Entry(temp.col, temp.data));//insert data into proper spot
					cp.array[i].moveNext(); //move matrix along
                }
                continue; //move along
            }
            if(cp.array[i].length() == 0){ //if the second matrix's list is empty...(no adding needed. just insert)
                this.array[i].moveFront();
                while(this.array[i].index() >= 0){ //itterate through list/row
                    Entry temp = ((Entry)this.array[i].get()); //extract entry.
                    end.array[i].append(new Entry(temp.col, temp.data));//insert data into proper spot
					this.array[i].moveNext(); //move matrix along
                }
                continue; //move along
            }
            
            this.array[i].moveFront();
            cp.array[i].moveFront();

			//if the lists are the same, just double one matrix
            if(this.array[i].equals(cp.array[i])){
                while(this.array[i].index() >= 0){ //itterate thorugh list/row
                    Entry temp= (Entry)this.array[i].get();//extract entry
                    end.array[i].append(new Entry(temp.col, temp.data*2)); //double one matrix
					this.array[i].moveNext();//move matrix along
                }
                continue; //move along
            }
            
            boolean done = false;
            while(!done){
                if(this.array[i].index <0 && cp.array[i].index <0) done = true;//List is empty

				//if first matrix (this) is done, but more entries in second matrix(cp)
				//just insert entries in cp to final matrix
                else if(this.array[i].index < 0 && cp.array[i].index >= 0){
                    while(cp.array[i].index >= 0){
                        Entry temp = (Entry) cp.array[i].get();//extract entry
                        end.array[i].append(new Entry(temp.col, temp.data));//insert into final matrix
                        cp.array[i].moveNext(); //move matrix along
                    }
                    done = true;//done with the row!
                } 

				//if second matrix (cp) is done, but more entries in first matrix (this)
				//just insert entries in this to final matrix
                else if(this.array[i].index() >= 0 && cp.array[i].index() < 0){
                    while(this.array[i].index() >= 0){
                        Entry temp = (Entry) this.array[i].get();
                        end.array[i].append(new Entry(temp.col, temp.data));
                        this.array[i].moveNext();
                    }
                    done = true; //done with the row!
                }
                else{
				//otherwise extract enetries in each matrix
                    Entry temp = (Entry) this.array[i].get();
                    Entry tempn = (Entry) cp.array[i].get();
					
					//check the columns
                    if(temp.col == tempn.col){ //add data and insert if cols are same
                        if(temp.data + tempn.data != 0.0)
                        end.array[i].append(new Entry(temp.col, temp.data + tempn.data));
                        cp.array[i].moveNext();//move along
                        this.array[i].moveNext(); //move along
                    }
                    else if(temp.col< tempn.col){ //if this matrix's col is behind of cp's col... put this's entry in (aka add 0)
                        end.array[i].append(temp);
                        this.array[i].moveNext();
                    }
                    else { //tempn.col<temp.col 
						//same thing as previous if statment except for cp insead of this
                        end.array[i].append(tempn);
                        cp.array[i].moveNext();
                    } 
                }
            }
        }
        return end; //return final matrix
    }

    // returns a new Matrix that is the difference of this Matrix with M
    // pre: getSize()==M.getSize()
	//
	// sub is the same code as add, with - instead of + for combining entries
	// and if first matrix is empty entry but not second matrix, insert 
	// negative entry from the second list

    public Matrix sub(Matrix M){
        if(!(M.getSize()==this.getSize())){
            throw new RuntimeException("error: add(). size doesn't match");
        }
        Matrix end = new Matrix(M.size);
        Matrix cp = M.copy();
        for(int i=1;i<=size;i++){
            if(this.array[i].length() == 0){ //if first's list is empty...
                cp.array[i].moveFront();
                while(cp.array[i].index() >= 0){
                    Entry temp = ((Entry)cp.array[i].get());
                    end.array[i].append(new Entry(temp.col, -temp.data));//negative data
					cp.array[i].moveNext();
                }
                continue;
            }
            if(cp.array[i].length() == 0){ //if second list is empty
                this.array[i].moveFront();
                while(this.array[i].index() >= 0){
                    Entry temp = ((Entry)this.array[i].get());
                    end.array[i].append(new Entry(temp.col, temp.data));//insert first list
					this.array[i].moveNext();
                }
                continue;
            }
            
            this.array[i].moveFront();
            cp.array[i].moveFront();
            if(this.array[i].equals(cp.array[i])){
                return end;
            }
            
            boolean done = false;
            while(!done){
                if(this.array[i].index <0 && cp.array[i].index <0) done = true;//if list is empty, done with row

				//if first  matrix is done, and second  matrix is not, insert second
				//matrix in 
                else if(this.array[i].index < 0 && cp.array[i].index >= 0){ //if 
                    while(cp.array[i].index >= 0){
                        Entry temp = (Entry) cp.array[i].get();
                        end.array[i].append(new Entry(temp.col, -temp.data));
                        cp.array[i].moveNext();
                    }
                    done = true;
                } 

				//if second matrix is done, and first matrix isnot, insert first matrix
                else if(this.array[i].index() >= 0 && cp.array[i].index() < 0){
                    while(this.array[i].index() >= 0){
                        Entry temp = (Entry) this.array[i].get();
                        end.array[i].append(new Entry(temp.col, temp.data));
                        this.array[i].moveNext();
                    }
                    done = true;
                }
                else{
                    Entry temp = (Entry) this.array[i].get();
                    Entry tempn = (Entry) cp.array[i].get();

					//compare cols
                    if(temp.col == tempn.col){
                        if(temp.data - tempn.data != 0.0)
                           end.array[i].append(new Entry(temp.col, temp.data - tempn.data));
                        cp.array[i].moveNext();
                        this.array[i].moveNext();
                    }
                    else if(temp.col< tempn.col){
                        end.array[i].append(temp);
                        this.array[i].moveNext();
                    }
                    else { //tempn.col<temp.col
                        end.array[i].append(new Entry(tempn.col, - tempn.data));
                        cp.array[i].moveNext();
                    } 
                }
            }
        }
        return end; //retrn end result
    }

    // returns a new Matrix that is the transpose of this Matrix
    public Matrix transpose(){
        Matrix tardis = new Matrix(size);
        for(int i =1;i<= size;i++){ //itterate through rows
            array[i].moveFront(); //move lsit to front
            while(array[i].index()>=0){ //itterate though list...
                Entry temp = (Entry) this.array[i].get();
                tardis.changeEntry(temp.col, i, temp.data );//...swap rows and cols
                this.array[i].moveNext();//itterate
            }
        }
        return tardis; //return the transposed matrix
    }

    // returns a new Matrix that is the product of this Matrix with M
    // pre: getSize()==M.getSize()
    public Matrix mult(Matrix M) {
        if((M.getSize()!=this.getSize())){
            throw new RuntimeException("error: mult(). size doesn't match");
        }
        Matrix mul = new Matrix(this.getSize());
        Matrix arg = M.transpose();
	     int nonz; //keep track of non zero entries
			nonz = arg.getNNZ()+ this.getNNZ();
			//total number of nnz is the max number of entries in the final matrix
			//I use this so mult doesn't have to multiply all the zero rows and cols
        for(int i = 1;i <= this.size;i++){ 
            for(int j = 1; j <= size; j++){
               if(this.array[i]!=null  && arg.array[i] != null){
			   double sum = DotProduct(this.array[i], arg.array[j]);//copute dot product
               if(sum != 0.0 ){ //insert if sum is not zero
                   mul.changeEntry(i, j, DotProduct(this.array[i], arg.array[j]));
				   nonz--; //decrement number on non zero entries
				   if(nonz <0) //check if we completed the max number of multiplication results
				   		return mul; //we are done if we got the product more than nnz times
                }
				}
				else return mul;
               
            }
        }        
        return mul;
    }    

    private double DotProduct(List A, List B){
              if(A.length() == 0 || B.length() == 0) return 0.0;
        // Move both lists for front for looping through & initialize while loop
        // codition & total sum for dot product
        A.moveFront();
        B.moveFront();
        boolean isDone = false;
        double total = 0.0;
        while(!isDone){
            // If we are off bounds of both lists then exit loop
            if(A.index >= 0 && B.index >= 0){
                Entry first = (Entry)A.get(); 
				Entry second = (Entry)B.get();
                if(first.col== second.col){//if cols are same, mult data
                    if((double)(first.data * second.data) != 0.0) //insert data if product is not zero
                        total += (first.data * second.data);
					//	System.out.println("This Entry: "+thisEntry.data + "That Entry: "+ thatEntry.data);
                    A.moveNext(); B.moveNext();
                } else if(first.col < second.col){ //if first's col is behind, increment
                    A.moveNext();
                } else if(first.col > second.col){ //if second's col is behind, increpent
                    B.moveNext();
                }
            } else isDone = true;
        }
        return total;
    }


//returns a string of a matrix
    public String toString(){
        String temp = "";
        for(int i=1;i<=getSize();i++){
            if(array[i].length()>0)
                temp += i+": "+ array[i] + "\n";
        }
        return temp;
    }
}
