package tls.test3;
import java.lang.Math.*;

public class Solution
{

    public int solution(int A, int B) {
        int rtnCount = 0;
        int sqrtVal = 0;
        int squaredVal = 0;

        System.out.println("\nRange Start = " + A + " Range End = " + B);
        
        try {
            for (int i = A; i <= B; i++)
            {
                
                sqrtVal = (int) Math.sqrt((double) Math.abs(i));
                squaredVal = sqrtVal * sqrtVal;
                if (squaredVal == Math.abs(i))
                {
                    rtnCount++;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        System.out.println("Number of values with square roots in given range = " + rtnCount);
            
        return rtnCount;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        Solution soltn = new Solution ();
        
        int rtn1 = 0;
        int rtn2 = 0;
        int rtn3 = 0;
        
        System.out.println("Returns the count of values with square roots for the given range of numbers\n"
                + "The count includes square roots for negative numbers because Complex Number Theory allows\n"
                + "negative numbers to have square roots.");
        
        rtn1 = soltn.solution(4,17);
        rtn2 = soltn.solution(-17,17);
        
        return;
        
    }

}
