import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static int[] list;
    static int[][] unique;
    static Map<String, Boolean> completed = new HashMap<String, Boolean>();

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
        System.out.println(Solution.solution(new int[] { 2, 4, 4, 8, 8, 8, 16 })); //
        // Expected output: 5
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

        // System.out.println("\n\n" + Arrays.toString(l) + ":\n");
        list = l;
        Arrays.sort(list);
        unique = removeDuplicates(list);
        // for (int i = 0; i < unique.length; i++) {
        // System.out.println(Arrays.toString(unique[i]));
        // }
        int noOfTuples = 0;
        noOfTuples = iteration(0, -1);

        // System.out.println(iteration(noOfTuples, noOfTuples));
        return noOfTuples;
    }

    public static int iteration(int currentIndex, int previousIndex) {
        boolean notFirstIteration = true;
        int result = 0;
        if (previousIndex == -1)
            notFirstIteration = false;

        // visited[currentIndex] = !addTuple;

        for (int i = currentIndex + 1; unique[i][0] != -1 && i < unique.length; i++) {
            System.out.println(
                    " ".repeat(currentIndex) + "Checking " + unique[i][0] + " with " +
                            unique[currentIndex][0]);

            if (unique[i][0] % unique[currentIndex][0] == 0
                    && (!notFirstIteration || !completed.containsKey(previousIndex + "-" + currentIndex + "-" + i))) {
                // System.out.println(" ".repeat(currentIndex)
                // + "Found Matching " + list[i] + " with " +
                // list[currentIndex]);

                if (notFirstIteration) {
                    System.out.println(" ".repeat(currentIndex)
                            + "(" + unique[previousIndex][0] + "," + unique[currentIndex][0] + ","
                            + unique[i][0] + ")\n");
                    completed.put(previousIndex + "-" + currentIndex + "-" + i, true);
                    result += unique[previousIndex][1] * unique[currentIndex][1] * unique[i][1];
                }

                // completed[i] = true;

                result += iteration(i, currentIndex);
            }

        }

        if (!notFirstIteration)

            for (int i = currentIndex + 1; unique[i][0] != -1 && i < unique.length; i++) {
                boolean complete = anyKeyStartsWith(String.valueOf(i));
                System.out.println(" ".repeat(currentIndex) + "Completed " + unique[i][0] + "(" + i + "): " +
                        complete);
                if (!complete) {
                    result += iteration(i, -1);
                }
            }

        return result;

    }

    static boolean anyKeyStartsWith(String key) {
        for (String k : completed.keySet()) {
            if (k.startsWith(key + "-") || k.endsWith("-" + key) || k.contains("-" + key + "-"))
                return true;
        }
        return false;
    }

    static int[][] removeDuplicates(int[] arr) {
        int[][] result = new int[arr.length][2];
        // Arrays.fill(result, new int[] { -1, 0 });
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            // System.out.println("\n\nChecking " + arr[i] + " with " + arr[i + 1]);
            if (arr[i] != arr[i + 1]) {
                if (i - 1 < 0 || arr[i - 1] != arr[i]) {
                    // System.out.println("Adding " + arr[i]);

                    result[j][0] = arr[i];
                    result[j][1] = 1;
                    j++;

                } else {
                    // System.out.println("Incrementing 2" + arr[i + 1]);
                    result[j - 1][1] += 1;
                }
                result[j][0] = arr[i + 1];
                result[j][1] = 0;
                j++;

            } else {
                // System.out.println("Incrementing " + arr[i]);
                // System.out.println("Before: " + Arrays.toString(result[j - 1]));
                result[j - 1][1] += 1;
                // System.out.println("Incremented " + arr[i] + " to " + result[j - 1][1]);
            }

        }

        result[j - 1][1] += 1;

        if (j != arr.length) {
            result[j][0] = -1;
            result[j][1] = -1;
        }

        return result;
    }
}