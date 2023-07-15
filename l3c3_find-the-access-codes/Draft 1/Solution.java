import java.util.Arrays;

public class Solution {

    static int[] list;
    static boolean[][][] completed;

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] array = { 537, 943829, 876924, 324937, 123456, 78912, 435678, 987654, 23456, 567890, 876543, 987321,
                654987, 54321, 908765, 654, 4321, 198765, 543789, 120987, 987654, 453678, 876543, 901827, 234567,
                987654, 31234, 654321, 90876, 543210, 987654, 109872, 456789, 876543, 43210, 987654, 23456, 123456,
                789012, 876543, 890765, 987654, 234567 };
        // get the start time
        long start = System.nanoTime();

        // call the method

        // System.out.println(Solution.solution(new int[] { 1, 1 })); // Expected
        // output: 0
        // System.out.println(Solution.solution(new int[] { 3, 4, 6, 8, 5, 16, 18, 54
        // })); // Expected output: 0
        // System.out.println(Solution.solution(new int[] { 1, 2, 4 })); // Expected
        // output: 1
        // System.out.println(Solution.solution(new int[] { 1, 2, 4, 1, 2, 4, 1, 2, 4
        // })); // Expected output: 9
        System.out.println(Solution.solution(new int[] { 2, 4, 4, 8, 8, 8, 16 })); // Expected output: 5
        // System.out.println(Solution.solution(new int[] { 1, 999999, 999999 })); //
        // Expected output: 1
        // System.out.println(Solution.solution(new int[] { 1, 1, 1, 2, 2, 2, 4, 4, 4,
        // 8, 8, 8 })); // Expected output: 12
        // System.out.println(Solution.solution(new int[] { 1, 1, 1, 1, 1 })); //
        // Expected output: 0
        // System.out.println(Solution.solution(new int[] { 1, 2, 4, 1, 2, 4 })); //
        // Expected output: 2
        // System.out.println(Solution.solution(new int[] { 1, 3, 5, 2, 4, 6, 8, 10
        // }));// Expected output: 4

        // System.out.println(Solution.solution(array)); // Expected output: "4"

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int solution(int[] l) {

        System.out.println("\n\n" + Arrays.toString(l) + ":\n");
        list = l;
        Arrays.sort(list);
        completed = new boolean[list.length][list.length][list.length];
        Arrays.stream(completed).forEach(a -> Arrays.stream(a).forEach(b -> Arrays.fill(b, false)));

        int noOfTuples = iteration(0, -1);
        // System.out.println(iteration(noOfTuples, noOfTuples));
        return noOfTuples;
    }

    public static int iteration(int currentIndex, int previousIndex) {
        boolean firstIteration = true;
        int result = 0;
        if (previousIndex == -1)
            firstIteration = false;

        // visited[currentIndex] = !addTuple;

        for (int i = currentIndex + 1; i < list.length; i++) {
            // System.out.println(
            // " ".repeat(currentIndex) + "Checking " + list[i] + " with " +
            // list[currentIndex]);

            if (list[i] % list[currentIndex] == 0 && (!firstIteration || !completed[previousIndex][currentIndex][i])) {
                // System.out.println(" ".repeat(currentIndex)
                // + "Found Matching " + list[i] + " with " +
                // list[currentIndex]);

                if (firstIteration) {
                    // System.out.println(" ".repeat(currentIndex)
                    // + "(" + list[previousIndex] + "(" + previousIndex + ")" + "," +
                    // list[currentIndex] + "("
                    // + currentIndex + ")"
                    // + ","
                    // + list[i] + "(" + i + ")" + ")\n");

                    System.out.println("(" + previousIndex + "," + currentIndex + "," + i + ")\n");
                    completed[previousIndex][currentIndex][i] = true;
                    result++;
                }

                // completed[i] = true;

                result += iteration(i, currentIndex);
            }

        }

        // if (!firstIteration)

        // for (int i = currentIndex + 1; i < list.length; i++) {
        // boolean complete = boolMatrixContains(Solution.completed[i], true);
        // // System.out.println(" ".repeat(currentIndex) + "Completed " + list[i] + ":
        // " +
        // // complete);
        // if (!complete) {
        // result += iteration(i, -1);
        // }
        // }

        if (!firstIteration && currentIndex < list.length - 1) {
            boolean complete = boolMatrixContains(Solution.completed[currentIndex + 1], true);
            // System.out.println(" ".repeat(currentIndex) + "Completed " +
            // unique[currentIndex + 1][0] + "("
            // + (currentIndex + 1) + "): " +
            // complete);
            if (!complete) {
                result += iteration(currentIndex + 1, -1);
            }
        }

        return result;

    }

    static boolean boolMatrixContains(boolean[][] arr, boolean target) {
        for (boolean[] a : arr) {
            for (boolean b : a) {
                if (b == target)
                    return true;
            }
        }
        return false;
    }
}