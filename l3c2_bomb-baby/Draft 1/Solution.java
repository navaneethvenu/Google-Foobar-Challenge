import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        System.out.println(Solution.solution("1", "1")); // Expected output: "0"
        System.out.println(Solution.solution("4", "7")); // Expected output: "0"
        System.out.println(Solution.solution("2", "1")); // Expected output: "0"
        // System.out.println(Solution.solution("5", "5")); // Expected output: "0"
        // System.out.println(Solution.solution("10", "9")); // Expected output: "1"
        // System.out.println(Solution.solution("7", "8")); // Expected output: "1"
        // System.out.println(Solution.solution("1000", "2")); // Expected output: "994"
        // System.out.println(Solution.solution("2", "1000")); // Expected output: "994"
        // System.out.println(Solution.solution("64", "16")); // Expected output: "3"
        // System.out.println(Solution.solution("32", "15")); // Expected output:
        // "impossible"
        System.out.println(Solution.solution("13", "128")); // Expected output: "8"
        System.out.println(Solution.solution("1234567890", "9876543210")); // Expected output: "8"

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static String solution(String x, String y) {

        // System.out.println("\n\nx: " + x + " y: " + y + "\n\n");

        BigInteger requiredMach = x != "" ? new BigInteger(x) : BigInteger.ONE;
        BigInteger requiredFac = y != "" ? new BigInteger(y) : BigInteger.ONE;
        BigInteger cycles = cycle(requiredMach, requiredFac);
        // System.out.println("Cycles: " + (cycles.subtract(BigInteger.ONE)));

        if (cycles.compareTo(BigInteger.valueOf(-1)) == 0) {
            return "impossible";
        }
        // Your code here

        return (cycles.toString());
    }

    public static BigInteger cycle(BigInteger machCount, BigInteger facCount) {

        BigInteger result;
        BigInteger minusOne = BigInteger.valueOf(-1);
        // System.out.println("machCount: " + machCount + " facCount: " + facCount);
        if ((machCount.compareTo(BigInteger.ONE) == 0) && (facCount.compareTo(BigInteger.ONE) == 0)) {
            // System.out.println("Ended");
            result = BigInteger.ZERO;
        } else if ((machCount.compareTo(BigInteger.ONE) == -1) || (facCount.compareTo(BigInteger.ONE) == -1)) {
            // System.out.println("Impossible");
            result = minusOne;
        } else if (machCount.compareTo(facCount) == 1) {
            BigInteger modResult = machCount.mod(facCount);
            boolean divide = modResult.compareTo(BigInteger.ONE) != -1;
            boolean hasRemainder = machCount.divide(facCount).compareTo(BigInteger.ONE) == 1;

            // System.out.println("modResult: " + modResult + " divide: " + divide + "
            // hasRemainder: " + hasRemainder);

            BigInteger newMachCount = divide ? machCount.mod(facCount)
                    : hasRemainder
                            ? (machCount.subtract(
                                    (machCount.divide(facCount).subtract(BigInteger.ONE)).multiply(facCount)))
                            : machCount.subtract(facCount);

            result = cycle(newMachCount, facCount);

            if (result.compareTo(minusOne) == 0) {
                return minusOne;
            }

            result = divide ? result.add(machCount.divide(facCount))
                    : hasRemainder ? machCount.divide(facCount).subtract(BigInteger.ONE) : result.add(BigInteger.ONE);
        } else {
            BigInteger modResult = facCount.mod(machCount);
            boolean divide = modResult.compareTo(BigInteger.ONE) != -1;
            boolean hasRemainder = facCount.divide(machCount).compareTo(BigInteger.ONE) == 1;

            // System.out.println("modResult: " + modResult + " divide: " + divide + "
            // hasRemainder: " + hasRemainder);

            BigInteger newFacCount = divide ? facCount.mod(machCount)
                    : hasRemainder
                            ? (facCount.subtract(
                                    (facCount.divide(machCount).subtract(BigInteger.ONE)).multiply(machCount)))
                            : facCount.subtract(machCount);

            result = cycle(machCount, newFacCount);

            if (result.compareTo(minusOne) == 0) {
                return minusOne;
            }

            result = divide ? result.add(facCount.divide(machCount))
                    : hasRemainder ? facCount.divide(machCount).subtract(BigInteger.ONE) : result.add(BigInteger.ONE);
        }
        if (result.compareTo(minusOne) == 0) {
            return minusOne;
        }
        // System.out.println("Result: " + result);
        return result;

    }

}
