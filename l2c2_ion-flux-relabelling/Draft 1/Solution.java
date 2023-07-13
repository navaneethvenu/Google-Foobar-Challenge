import java.util.Arrays;

public class Solution {

    static int[] initial = {};
    static int[] result;

    static boolean initialMatch[];

    static boolean lookupMatch[];
    static int lookup[][];
    static int lookupPointer = 0;

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        System.out.print(obj.solution(5, new int[] { 19, 14, 28 }));

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int[] solution(int h, int[] q) {
        // Your code here
        int height = h;
        initial = q.clone();
        result = new int[initial.length];

        lookupMatch = new boolean[initial.length];
        Arrays.fill(lookupMatch, false);
        lookup = new int[initial.length][2];

        initialMatch = new boolean[initial.length];
        Arrays.fill(initialMatch, false);

        Arrays.sort(initial);
        completeLevel(height, height - 1, 0, 1);

        result = extendedCompareSort(q);
        // for (int i = 0; i < result.length; i++) {
        // System.out.print(result[i] + " ");
        // }
        return result;
    }

    public static void completeLevel(int height, int level, int offset, int label) {

        // System.out.println("Level: " + level + " Start: " + offset);

        if (level > height)
            return;

        // System.out.print(label + "(" + level + ", " + offset + ")");

        for (int i = 0; i < lookupPointer; i++) {
            if (lookupMatch[i] == false) {
                // System.out.print("\nLooking up -> " + (lookup[i][0]) + ", " +
                // (lookup[i][1]));
                if (level == lookup[i][0] && offset == lookup[i][1]) {
                    // System.out.print("\nFound Match for " + initial[i] + " -> " + (label));
                    result[i] = label;
                    lookupMatch[i] = true;
                }
            }
        }

        for (int i = 0; i < initialMatch.length; i++) {
            if (initialMatch[i] == false) {
                if (initial[i] == label) {
                    // System.out.print("\nMatch -> " + (label));
                    lookup[lookupPointer][0] = level - 1;
                    lookup[lookupPointer][1] = (int) Math.floor(offset / 2);
                    // System.out.print("\nUpdated Lookup -> " + (lookup[lookupPointer][0]) + ", " +
                    // (lookup[lookupPointer][1]));
                    if (lookup[lookupPointer][0] < 0 || lookup[lookupPointer][1] < 0) {
                        // System.out.println("\nNo Found Match -> -1");
                        result[i] = -1;
                        lookupMatch[lookupPointer] = true;
                    }
                    lookupPointer += 1;
                }
            }
        }

        if (level == 0) {
            // System.out.println("Ended");
            return;
        }

        if (offset % 2 == 0) {
            // System.out.println("\n");
            if (level + 1 < height) {
                int sum = offset;
                for (int i = 0; i < height - 1 - level; i++) {
                    sum = sum * 2 + 1;
                }

                completeLevel(height, height - 1, sum + 1, label + 1);
            } else {

                completeLevel(height, level, (offset + 1), label + 1);

            }
        } else
            completeLevel(height, level - 1, (int) (offset / 2), label + 1);

    }

    static int[] extendedCompareSort(int nonSorted[]) {
        int sortedResult[] = new int[nonSorted.length];
        for (int i = 0; i < nonSorted.length; i++) {
            for (int j = 0; j < initial.length; j++) {
                if (nonSorted[i] == initial[j]) {
                    sortedResult[i] = result[j];
                }
            }
        }
        return sortedResult;
    }
}
