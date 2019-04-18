package tls.test2;

// For this Test, was allowed to change 4 lines of code to fix one or more defects, but not add new lines,
// move existing lines, or delete existing line.
// Original lines have been commented out.

// The method solution is suppose to return the value with the maximum number of occurrences.
// If there was a tie for maximum number of occurrences, then the first value in the array that
// was tied for maximum number of occurrences is returned.
// For example: input array {1,2,2,4,4} would return 2 as the value with the maximum number of occurrences
// since 2 if before 4 in the array.

//
public class Solution
{
    int solution(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 1;
//        int maxOccurence = 0;
//        int index = -1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
//                int tmp = count[A[i]];
                int tmp = count[A[i]] + 1;
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
//                count[A[i]] = tmp + 1;
                count[A[i]] = tmp;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
        Solution soltn = new Solution();
        
        int[] arry1 = {1,2,3,3,1,3,1};
        String str1 = "{1,2,3,3,1,3,1}";
        int m1 = 3;
        int rtn1 = 0;

        int[] arry2 = {1,0,2,0,3,0,3,1,0,3,1};
        String str2 = "{1,0,2,0,3,0,3,1,0,3,1}";
        int m2 = 3;
        int rtn2 = 0;

        int[] arry3 = {1};
        String str3 = "{1}";
        int m3 = 1;
        int rtn3 = 0;

        int[] arry4 = {1,2,3,4,5};
        String str4 = "{1,2,3,4,5}";
        int m4 = 5;
        int rtn4 = 0;

        int[] arry5 = {1,2,2,4,4};
        String str5 = "{1,2,2,4,4}";
        int m5 = 5;
        int rtn5 = 0;

        int[] arry6 = {2,3,4,5};
        String str6 = "{2,3,4,5}";
        int m6 = 5;
        int rtn6 = 0;

        System.out.println("After defects were fixed, returns the first value in the array with the maximum number of occurrences/n");
        
        rtn1 = soltn.solution(m1, arry1);
        System.out.println("\nMaximum value in input array = " + m1 + " Input Array = " + str1);
        System.out.println("First value in array with maximum occurences = " + rtn1);
        
        rtn2 = soltn.solution(m2, arry2);
        System.out.println("\nMaximum value in input array = " + m2 + " Input Array = " + str2);
        System.out.println("First value in array with maximum occurences = " + rtn2);
        
        rtn3 = soltn.solution(m3, arry3);
        System.out.println("\nMaximum value in input array = " + m3 + " Input Array = " + str3);
        System.out.println("First value in array with maximum occurences = " + rtn3);

        rtn4 = soltn.solution(m4, arry4);
        System.out.println("\nMaximum value in input array = " + m4 + " Input Array = " + str4);
        System.out.println("First value in array with maximum occurences = " + rtn4);
        
        rtn5 = soltn.solution(m5, arry5);
        System.out.println("\nMaximum value in input array = " + m5 + " Input Array = " + str5);
        System.out.println("First value in array with maximum occurences = " + rtn5);
        
        rtn6 = soltn.solution(m6, arry6);
        System.out.println("\nMaximum value in input array = " + m6 + " Input Array = " + str6);
        System.out.println("First value in array with maximum occurences = " + rtn6);

        return;
        
    }

}
