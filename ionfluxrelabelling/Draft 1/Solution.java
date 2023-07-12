
public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        System.out.print(obj.solution(7, new int[] { 1, 4, 7 }));

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    public static int[] solution(int h, int[] q) {
        // Your code here
        int height = h;
        completeLevel(height, height - 1, 0);
        return new int[0];
    }

    public static void completeLevel(int height, int level, int offset) {
        // System.out.println("Level: " + level + " Start: " + offset);

        if (level > height)
            return;

        System.out.print("(" + level + ", " + offset + ")");
        if (level == 0) {
            System.out.println("Ended");
            return;
        }

        if (offset % 2 == 0) {
            System.out.println("\n");
            if (level + 1 < height) {
                int sum = offset;
                for (int i = 0; i < height - 1 - level; i++) {
                    sum = sum * 2 + 1;
                }

                completeLevel(height, height - 1, sum + 1);
            } else {

                completeLevel(height, level, (offset + 1));

            }
        } else
            completeLevel(height, level - 1, (int) (offset / 2));

    }
}
