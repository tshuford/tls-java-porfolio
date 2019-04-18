package tls.test;

import java.util.Arrays;

//import java.util.Arrays;
public class Test
{
 // you can also use imports, for example:
 // import java.util.*;

 // you can write to stdout for debugging purposes, e.g.
 // System.out.println("this is a debug message");



 // you can also use imports, for example:
 // import java.util.*;

 // you can write to stdout for debugging purposes, e.g.
 // System.out.println("this is a debug message");

    // returns smallest positive integer value which does not exist in array A
    
     public int solution(int[] A) {
         int rtn = 0;
         int len = A.length;
         boolean found = false;
         // write your code in Java SE 8
         Arrays.sort(A);
         if (len == 0 || A[0] > 1 || A[len-1] <= 0 )
         {
             rtn = 1;
             found = true;
         }
         else
         {
          for (int i = 0; i < A.length; i++)
          {
             if (A[i] < 0) A[i] = 0;
             if ( ((i+1) < A.length) && A[i] >= 0 && ((A[i]+1 < A[i+1])))
             {
                 rtn = A[i] +1;
                 found = true;
                 break;
             }
          }
          if (!found )
           {
              rtn = A[A.length-1] +1;
           }
          }
          return rtn;
     }

    public static void main(String[] args)
    {
        int result1=0;
        int result2=0;
        int result3=0;
        int result4=0;
        int result5=0;
        String str1 = "{1,2,3}";
        String str2 = "{-1,-3}";
        String str3 = "{1,3,6,4,1,2}";
        String str4 = "{-1,3,6,4,1,2}";
        String str5 = "{-1,3,6,4,2}";
        int [] intArr1 = {1,2,3};
        int [] intArr2 = {-1,-3};
        int [] intArr3 = {1,3,6,4,1,2};
        int [] intArr4 = {-1,3,6,4,1,2};
        int [] intArr5 = {-1,3,6,4,2};
        Test test = new Test();
        
        System.out.println("Result is the smallest positive integer not in input array\n");
        result1 = test.solution(intArr1);
        System.out.println("Input Array = " + str1);
        System.out.println("Result = " + result1);
        result2 = test.solution(intArr2);
        System.out.println("Input Array = " + str2);
        System.out.println("Result = " + result2);
        result3 = test.solution(intArr3);
        System.out.println("Input Array = " + str3);
        System.out.println("Result = " + result3);
        result4 = test.solution(intArr4);
        System.out.println("Input Array = " + str4);
        System.out.println("Result = " + result4);
        result5 = test.solution(intArr5);
        System.out.println("Input Array = " + str5);
        System.out.println("Result = " + result5);
        
        return;
        
        // TODO Auto-generated method stub

    }

}
