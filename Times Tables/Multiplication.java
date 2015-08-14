
/**
 * This class displays a multiplication table by multiplying the row and col of a matrix
 * 
 * @author Ramzey Ghanaim
 * @version 2/14/2014
 */
public class Multiplication
{
    public static void main()
    {
        int[][] math = new int[10][10];
        for(int row = 1; row < math.length; row++)
        {
            for(int col = 1; col < math[row].length; col++)
            {
                math[row][col] = row*col;
                System.out.print(math[row][col] + " ");
            }
            System.out.println();
        }
    }
}
