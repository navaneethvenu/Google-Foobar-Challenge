public class Solution {

    public static void main(String[] args) {

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

        int count = 0;
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                for (int k = j + 1; k < l.length; k++) {
                    if (l[k] % l[j] == 0 && l[j] % l[i] == 0) {
                        // System.out
                        // .println("(" + l[i] + "(" + i + "), " + l[j] + "(" + j + "), " + l[k] + "(" +
                        // k + "))");
                        count++;
                    }
                }
            }
        }
        return count;
    }

}