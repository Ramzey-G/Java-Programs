
/**
 * This class creates a gid of numbers0-15 and finds the sum of 	the array
 * the sum of each row, and the sum of each column
 * 
 * @author Ramzey Ghanaim
 * @version 2/14/2014
 */
public class Grid
{
    public static void main()
    {
        int[][] array = new int[3][5];
        int [] sumOfRows= new int[array.length];
        int [] sumOfCol=new int[array[0].length];
        int x = 0;
        int sum =0;
        int rsum=0;
        int csum=0;
   //outputs array
        for(int row = 0; row < array.length; row++)
        {
            for(int col = 0; col < array[row].length; col++)
            {
                x++;
                
                if (array[row][col] == 0)
                {
                    array[row][col] = x;
                    sum+=x;
                   

                }
                
                System.out.print (array[row][col] + " ");
                
            }

            System.out.println();
            }
        System.out.println("the sum of the array is " + sum);
        //code below Finds the length of each row:
        for(int row = 0; row < array.length; row++)
        {
            for(int col = 0; col < array[0].length; col++)
            {
                rsum+=array[row][col];
            }
            sumOfRows[row]=rsum;
            System.out.println("sum of row "+(row+1) +" = "+rsum);
            rsum=0;
        }
        //code below finds legth of each column
        for(int col=0; col<array[0].length;col++)
        {
        for(int row=0;row<array.length;row++)
        {
            csum+=array[row][col];
        }
        System.out.println("the sum for column "+(col+1)+" is "+csum);
        csum=0;
        }
        
}
}
