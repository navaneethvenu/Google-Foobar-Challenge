public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        System.out.println(obj.solution("2", "4"));

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static String solution(String x, String y) {

        int requiredMach = Integer.parseInt(x);
        int requiredFac = Integer.parseInt(y);
        int cycles = cycle(requiredMach, requiredFac);
        System.out.println("Cycles: " + (cycles - 1));
        // Your code here
        return "";
    }

    public static int cycle(int machCount, int facCount) {

        int result;
        System.out.println("machCount: " + machCount + " facCount: " + facCount);
        if (machCount == 1 && facCount == 1) {
            System.out.println("Ended");
            result = 0;
        } else if (machCount < 1 || facCount < 1) {
            System.out.println("Impossible");
            result = -1;
        } else if (machCount > facCount) {
            result = cycle(machCount - facCount, facCount);
        } else {
            result = cycle(machCount, facCount - machCount);
        }
        if (result == -1) {
            return -1;
        }
        return result + 1;

    }

}
