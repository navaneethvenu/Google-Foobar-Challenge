import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static int limit = 1;
    static int[][] timeMatrix;
    static boolean[] visited;
    static List<Integer> sortedList = new ArrayList<Integer>();

    public static void main(String[] args) {

        // get the start time
        long start = System.nanoTime();

        // call the method

        // Solution.solution(new int[][] { { 0, 2, 2, 2, -1 }, { 9, 0, 2, 2, -1 }, { 9,
        // 3, 0, 2, -1 },
        // { 9, 3, 2, 0, -1 }, { 9, 3, 2, 2, 0 } }, 1); // [1,2]

        Solution.solution(new int[][] { { 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1,
                0, 1, 1 }, { 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 0 } }, 3); // [0, 1]

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int[] solution(int[][] times, int times_limit) {

        int[] bunnyList;

        limit = times_limit;
        timeMatrix = times;
        visited = new boolean[timeMatrix.length];

        // System.out.println("Times: " + Arrays.deepToString(timeMatrix));
        // System.out.println("Bool: " + Arrays.deepToString(visited));
        // System.out.println("Time limit: " + limit);
        // Your code here

        pickUpBunnies(0, -1);

        bunnyList = sortedList.stream().mapToInt(Integer::intValue).toArray();

        // System.out.println("\nBunny List: " + Arrays.toString(bunnyList));

        return bunnyList;

    }

    public static void pickUpBunnies(int current, int previous) {

        int delta = -1;
        if (previous != -1) {

            delta = timeMatrix[previous][current];
            limit -= delta;
        }
        if (current != 0 && current != timeMatrix.length - 1)
            visited[current] = true;
        // System.out.println("\n" + (previous == -1 ? "-" : previous) + " " + current +
        // " "
        // + (previous == -1 ? "-" : delta) + " "
        // + limit);

        if (current != 0 && current != timeMatrix.length - 1) {
            // System.out.print("Bunny " + (current - 1) + " picked up. ");
            sortedList.add(current - 1);
        }

        if (previous == -1) {
            // System.out.print("Bulkhead initially open.");
        }

        if (limit + delta >= 0 && limit < 0) {
            // System.out.print("Bulkhead closes.");
        }

        if (limit + delta < 0 && limit >= 0) {
            // System.out.print("Bulkhead Reopens.");
        }

        if (current == timeMatrix.length - 1 && limit == 0) {
            // System.out.print("You and the Bunnies Exit.");
            return;
        }

        int next = -1;

        for (int i = 1; i < timeMatrix.length; ++i) {
            if (i != current && !visited[i]) {
                // System.out.println("i: " + i + " current: " + current + " next: " + next);
                if (next == -1)
                    next = i;
                else if (timeMatrix[current][i] < timeMatrix[current][next])
                    next = i;
            }
        }

        if (next == -1) {
            // System.out.println("No more bunnies to pick up.");
            return;
            // pickUpBunnies(timeMatrix.length - 1, current);
        } else if (timeMatrix[current][next] >= limit) {
            if (current == timeMatrix.length - 1) {
                // System.out.print("You and the bunnies exit (no time)");
                return;
            } else {
                // System.out.print("Other case");
                return;
            }
        }

        pickUpBunnies(next, current);
    }
}