import java.math.BigInteger;

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
        // System.out.print("\n\nx: " + x + "\n\n");
        int steps = 0;
        BigInteger value = new BigInteger(x);

        steps += divideByTwo(value);
        // System.out.println(steps);

        return steps;

        // Your code here
    }

    public static int divideByTwo(BigInteger value) {
        // System.out.println(value);
        if (value.compareTo(BigInteger.ONE) < 1)
            return 0;
        if (value.remainder(BigInteger.TWO) == BigInteger.ZERO) {
            return 1 + divideByTwo(value.divide(BigInteger.TWO));
        } else {
            return 1 + divideByTwo(
                    findGreaterDivisibleValue(value.subtract(BigInteger.ONE), value.add(BigInteger.ONE)));
        }

    }

    public static BigInteger findGreaterDivisibleValue(BigInteger lower, BigInteger higher) {
        // System.out.println("lower: " + lower + " higher: " + higher);
        BigInteger result = BigInteger.ZERO;
        if ((lower.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0)
                && (higher.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0)) {
            // System.out.println("PRINT" + lower.divide(BigInteger.TWO) + " " +
            // higher.divide(BigInteger.TWO));
            result = lower.divide(BigInteger.TWO).compareTo(findGreaterDivisibleValue(lower.divide(BigInteger.TWO),
                    higher.divide(BigInteger.TWO))) == 0 ? lower : higher;

        } else {
            if (lower.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
                result = lower;
            } else if (higher.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
                if (lower.compareTo(BigInteger.ONE) == 0)
                    result = lower;
                else
                    result = higher;
            } else {

                BigInteger newLower = findGreaterDivisibleValue(lower.subtract(BigInteger.ONE),
                        lower.add(BigInteger.ONE));
                BigInteger newHigher = findGreaterDivisibleValue(higher.subtract(BigInteger.ONE),
                        higher.add(BigInteger.ONE));
                result = findGreaterDivisibleValue(newLower, newHigher);
            }

        }
        // System.out.println("result: " + result);
        return result;
    
    }

}
