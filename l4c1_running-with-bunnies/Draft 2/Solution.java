import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Solution {
    static List<Integer> sortedList = new ArrayList<Integer>();

    public static void main(String[] args) {

        // get the start time
        long start = System.nanoTime();

        // call the method

        Solution.solution(new int[][] { { 0, 2, 2, 2, -1 }, { 9, 0, 2, 2, -1 }, { 9,
                3, 0, 2, -1 },
                { 9, 3, 2, 0, -1 }, { 9, 3, 2, 2, 0 } }, 3); // [1,2]

        // Solution.solution(new int[][] { { 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1,
        // 0, 1, 1 }, { 1, 1, 1, 0, 1 },
        // { 1, 1, 1, 1, 0 } }, 3); // [0, 1]

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int[] solution(int[][] times, int times_limit) {

        int[] bunnyList;

        int limit = times_limit;
        int[][] timeMatrix = times;
        boolean[] visited = new boolean[timeMatrix.length];

        // System.out.println("Times: " + Arrays.deepToString(timeMatrix));
        // System.out.println("Bool: " + Arrays.deepToString(visited));
        // System.out.println("Time limit: " + limit);
        // Your code here

        ReturnValue returnValue = pickUpBunnies(0, -1, limit, timeMatrix, visited, 0);

        Collections.sort(returnValue.bunnies);

        bunnyList = returnValue.bunnies.stream().mapToInt(Integer::intValue).toArray();

        // System.out.println("\nBunny List: " + Arrays.toString(bunnyList));

        return bunnyList;

    }

    public static ReturnValue pickUpBunnies(int current, int previous, int limit, int[][] timeMatrix, boolean[] visited,
            int indent) {

        List<Integer> bunnies = new ArrayList<Integer>();
        ReturnValue result = new ReturnValue(false, bunnies);
        int delta = -1;
        if (previous != -1) {

            delta = timeMatrix[previous][current];
            limit -= delta;
        }
        if (current != 0 && current != timeMatrix.length - 1)
            visited[current] = true;
        // System.out.println("\n" + " ".repeat(indent) + (previous == -1 ? "-" :
        // previous) + " " + current +
        // " "
        // + (previous == -1 ? "-" : delta) + " "
        // + limit);

        if (current != 0 && current != timeMatrix.length - 1) {
            // System.out.println(" ".repeat(indent) + "Bunny " + (current - 1) + " picked
            // up. ");
            result.bunnies.add(current - 1);
        }

        if (previous == -1) {
            // System.out.println(" ".repeat(indent) + "Bulkhead initially open.");
        }

        if (limit + delta >= 0 && limit < 0) {
            // System.out.println(" ".repeat(indent) + "Bulkhead closes.");
        }

        if (limit + delta < 0 && limit >= 0) {
            // System.out.println(" ".repeat(indent) + "Bulkhead Reopens.");
        }

        if (current == timeMatrix.length - 1 && limit == 0) {
            // System.out.println(" ".repeat(indent) + "You and the Bunnies Exit.");
            result.success = true;
            return result;
        }

        boolean tryAgain = false;
        boolean[] tried = visited.clone();
        tried[0] = true;
        do {
            int next = -1;
            // System.out.println(" ".repeat(indent) + "Try again: " + tryAgain);

            for (int i = 1; i < timeMatrix.length; ++i) {
                if (i != current && !tried[i]) {
                    // System.out.println("i: " + i + " current: " + current + " next: " + next);
                    if (next == -1)
                        next = i;
                    else if (timeMatrix[current][i] < timeMatrix[current][next])
                        next = i;
                }
            }

            if (next == -1) {
                // System.out.println(" ".repeat(indent) + "No more bunnies to pick up.
                // Revert");
                return result;
                // pickUpBunnies(timeMatrix.length - 1, current);
            } else if (timeMatrix[current][next] >= limit) {
                if (current == timeMatrix.length - 1) {
                    // System.out.println(" ".repeat(indent) + "Should You and the bunnies exit (no
                    // time)?");
                    ReturnValue returnValue = pickUpBunnies(next, current, limit, timeMatrix, visited, indent + 1);
                    boolean continuePicking = returnValue.success;
                    if (continuePicking) {
                        // System.out.println("\n" + " ".repeat(indent) + "You and the bunnies
                        // continued");
                        result.success = true;
                        result.bunnies.addAll(returnValue.bunnies);
                    } else {
                        // System.out.println("\n" + " ".repeat(indent) + "You and the bunnies exit (no
                        // time)");
                    }
                    return result;
                } else {
                    // System.out.print(" ".repeat(indent) + "Other case but continue");
                    // System.out.print("Tried: " + Arrays.toString(tried));
                    // return false;
                }
            }

            ReturnValue returnValue = pickUpBunnies(next, current, limit, timeMatrix, visited, indent + 1);
            tryAgain = !returnValue.success;
            tried[next] = true;

            boolean trialsOver = true;

            // System.out.println(" ".repeat(indent) + "Tried: " + Arrays.toString(tried));

            for (boolean complete : tried) {
                if (!complete) {
                    trialsOver = false;
                    break;
                }
            }

            if (returnValue.success) {
                result.success = true;
                result.bunnies.addAll(returnValue.bunnies);
                break;
            } else if (trialsOver) {
                result.success = false;
                break;
            }

        } while (tryAgain);

        return result;
    }
}

class ReturnValue {
    boolean success;
    List<Integer> bunnies;

    public ReturnValue(boolean success, List<Integer> bunnies) {
        this.success = success;
        this.bunnies = bunnies;
    }
}