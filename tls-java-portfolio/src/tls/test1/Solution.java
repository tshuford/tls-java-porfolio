package tls.test1;
import java.util.*;
public class Solution
{
    public String solution(int T) {
        
        int t = T;
        int secsInDay = 86400;
        int secsInHr = 3600;
        int secsInMin = 60;
                
        int hrs = 0;
        int remainderMinInSecs = 0;
        int mins = 0;
        int secs = 0;
        
        String rtnTime = "";
        
        System.out.println("\nInput number of seconds = " + T);
        if (t < 0 ) 
        {
            rtnTime = "0h0m0s";
            System.out.println("Remainder day in hours, minutes, and seconds = " + rtnTime);
            return rtnTime;
        }
        t = t % secsInDay;
        
        try 
        {
            hrs = t / secsInHr;
            remainderMinInSecs = t % secsInHr;
            mins = remainderMinInSecs / secsInMin;
            secs = remainderMinInSecs % secsInMin;
            
            rtnTime = Integer.toString(hrs) + "h" + Integer.toString(mins) + "m" + Integer.toString(secs) + "s";
            
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        System.out.println("Remainder day in hours, minutes, and seconds = " + rtnTime);
        return rtnTime;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Solution soltn = new Solution();
        String tim1 = "";
        String tim2 = "";
        String tim3 = "";
        String tim4 = "";
        String tim5 = "";
        String tim6 = "";
        String tim7 = "";
        String tim8 = "";
        String tim9 = "";
        
        System.out.println("Returns the Hours, Minutes, and Seconds for any partial remainder day for the input number of seconds.\n");
        tim1 = soltn.solution(0);
        tim2 = soltn.solution(7500);
        tim3 = soltn.solution(83643);
        tim4 = soltn.solution(3599);
        tim5 = soltn.solution(59);
        tim6 = soltn.solution(18000);
        tim7 = soltn.solution(300);
        tim8 = soltn.solution(86700);
        tim9 = soltn.solution(-1);
        
        
        return;
    }

}
