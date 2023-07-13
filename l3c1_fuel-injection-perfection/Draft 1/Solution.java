
public class Solution {
    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        // System.out.println(obj.solution("1045"));
        // System.out.println(obj.solution("15"));
        // System.out.println(obj.solution("3"));
        // System.out.println(obj.solution("54"));

        System.out.println(Solution.solution("1")); // Expected output: 0
        System.out.println(Solution.solution("10")); // Expected output: 5
        System.out.println(Solution.solution("23")); // Expected output: 8
        System.out.println(Solution.solution("9876543210")); // Expected output: 33
        System.out.println(Solution.solution("512")); // Expected output: 9
        System.out.println(Solution.solution("64")); // Expected output: 6
        System.out.println(Solution.solution("63")); // Expected output: 17
        System.out.println(Solution.solution("1000000")); // Expected output: 15
        System.out.println(Solution.solution("999999")); // Expected output: 20
        System.out.println(Solution.solution("12345")); // Expected output: 20

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int solution(String x) {
        System.out.print("\n\nx: " + x + "\n\n");
        int steps = 0;
        long value = Long.parseLong(x);

        steps += divideByTwo(value);
        // System.out.println(steps);

        return steps;

        // Your code here
    }

    public static int divideByTwo(long value) {
        System.out.println(value);
        if (value <= 1)
            return 0;
        if (value % 2 == 0) {
            return 1 + divideByTwo(value / 2);
        } else {
            return 1 + divideByTwo(findGreaterDivisibleValue(value - 1, value + 1));
        }

    }

    public static long findGreaterDivisibleValue(long lower, long higher) {
        System.out.println("lower: " + lower + " higher: " + higher);
        long result = 0;
        if (lower % 2 == 0 && higher % 2 == 0) {
            result = lower / 2 == findGreaterDivisibleValue(lower / 2, higher / 2) ? lower : higher;

        } else {
            if (lower % 2 == 0) {
                result = lower;
            } else if (higher % 2 == 0) {
                if (lower == 1)
                    result = lower;
                else
                    result = higher;
            } else {

                long newLower = findGreaterDivisibleValue(lower - 1, lower + 1);
                long newHigher = findGreaterDivisibleValue(higher - 1, higher + 1);
                result = findGreaterDivisibleValue(newLower, newHigher);
            }

        }
        System.out.println("result: " + result);
        return result;
    
    }

}
