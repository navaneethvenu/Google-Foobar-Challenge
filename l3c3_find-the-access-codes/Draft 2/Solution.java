import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        // System.out.println(Solution.solution(new int[] { 1, 2, 3, 4, 5, 6 })); //
        // Expected output: 9
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

        // for (String key : completed.keySet()) {
        // System.out.println(key + " : " + completed.get(key));
        // }

        Set<String> sortedIndividuals = convertToSet(completed);
        noOfTuples += addPairCombinations(sortedIndividuals);
        noOfTuples += addSingleCombinations();

        // System.out.println(iteration(noOfTuples, noOfTuples));
        return noOfTuples;
    }

    public static Set<String> convertToSet(Map<String, Boolean> map) {
        Set<String> set = new HashSet<String>();
        for (String key : map.keySet()) {
            String[] keys = key.split("-");
            set.add(keys[0] + "-" + keys[1]);
            set.add(keys[1] + "-" + keys[2]);
            set.add(keys[0] + "-" + keys[2]);
        }

        // System.out.println(set);
        return set;
    }

    public static int addPairCombinations(Set<String> set) {
        int result = 0;
        for (String key : set) {
            String[] keys = key.split("-");
            boolean[] hasDuplicates = new boolean[keys.length];
            for (int i = 0; i < keys.length; i++) {
                hasDuplicates[i] = unique[Integer.parseInt(keys[i])][1] > 1;
            }
            if (!hasDuplicates[0] && !hasDuplicates[1])
                continue;

            // System.out.print("Pair Combinations: " + key + "\n");

            int addedCombinations = (hasDuplicates[0] ? (sumOfNNaturalNumbers(unique[Integer.parseInt(keys[0])][1] - 1)
                    * unique[Integer.parseInt(keys[1])][1])
                    : 0)
                    + (hasDuplicates[1] ? (sumOfNNaturalNumbers(unique[Integer.parseInt(keys[1])][1] - 1)
                            * unique[Integer.parseInt(keys[0])][1]) : 0);

            // System.out.println("Added Combinations: " + addedCombinations + "\n");
            result += addedCombinations;

        }
        return result;
    }

    public static int addSingleCombinations() {
        // System.out.println("Single Combinations");
        int result = 0;
        for (int i = 0; i < unique.length && unique[i][0] != -1; i++) {
            // System.out.println(unique[i][0] + " : " + unique[i][1]);
            if (unique[i][1] > 3) {
                for (int j = 1; j < unique[i][1] - 1; j++)
                    result += sumOfNNaturalNumbers(j);
                // System.out.println("(" + unique[i][0] + "," + unique[i][0] + "," +
                // unique[i][0] + ")");

            } else if (unique[i][1] == 3) {
                result += 1;
                // System.out.println("(" + unique[i][0] + "," + unique[i][0] + "," +
                // unique[i][0] + ")");
            }
        }
        return result;
    }

    public static int sumOfNNaturalNumbers(int n) {
        return (n * (n + 1)) / 2;
    }

    public static int iteration(int currentIndex, int previousIndex) {
        boolean notFirstIteration = true;
        int result = 0;
        if (previousIndex == -1)
            notFirstIteration = false;

        // visited[currentIndex] = !addTuple;

        for (int i = currentIndex + 1; i < unique.length && unique[i][0] != -1; i++) {
            // System.out.println(
            // " ".repeat(currentIndex) + "Checking " + unique[i][0] + " with " +
            // unique[currentIndex][0]);

            if (unique[i][0] % unique[currentIndex][0] == 0
                    && (!notFirstIteration || !completed.containsKey(previousIndex + "-" + currentIndex + "-" + i))) {
                // System.out.println(" ".repeat(currentIndex)
                // + "Found Matching " + unique[i][0] + " with " +
                // unique[currentIndex][0]);

                if (notFirstIteration) {
                    // System.out.println(" ".repeat(currentIndex)
                    // + "(" + unique[previousIndex][0] + "(" + previousIndex + ")" + "," +
                    // unique[currentIndex][0] + "("
                    // + currentIndex + ")"
                    // + ","
                    // + unique[i][0] + "(" + i + ")" + ")\n");

                    // System.out.print("Hello?");

                    // System.out.println(" ".repeat(currentIndex) + "Found number of items: " +
                    // unique[previousIndex][1] + " * " +
                    // unique[currentIndex][1] + " * " +
                    // unique[i][1] + " = " +
                    // unique[previousIndex][1] * unique[currentIndex][1] * unique[i][1] + "\n");
                    completed.put(previousIndex + "-" + currentIndex + "-" + i, true);
                    result += unique[previousIndex][1] * unique[currentIndex][1] * unique[i][1];
                }

                // completed[i] = true;

                result += iteration(i, currentIndex);
            }

        }

        if (!notFirstIteration && currentIndex < unique.length - 1 && unique[currentIndex + 1][0] != -1) {
            boolean complete = anyKeyStartsWith(String.valueOf(currentIndex + 1));
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

    static boolean anyKeyStartsWith(String key) {
        for (String k : completed.keySet()) {
            if (k.startsWith(key + "-") || k.endsWith("-" + key) || k.contains("-" + key + "-"))
                return true;
        }
        return false;
    }

    static int[][] removeDuplicates(int[] arr) {
        int[][] result = new int[arr.length][2];

        int j = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (j == 0) {
                result[j][0] = arr[i];
                result[j][1] = 1;
                j++;
            } else {
                if (arr[i] == result[j - 1][0]) {
                    result[j - 1][1]++;
                } else {
                    result[j][0] = arr[i];
                    result[j][1] = 1;
                    j++;
                }
            }
        }

        if (j != arr.length) {
            result[j][0] = -1;
            result[j][1] = -1;
        }

        return result;

    }
}