public class Solution {

    static int[][] moves = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 },
            { -1, 2 }, { -1, -2 } };

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method

        System.out.println(obj.solution(0, 36));
        System.out.println(obj.solution(0, 1));
        System.out.println(obj.solution(0, 2));
        System.out.println(obj.solution(19, 36));

        // get the end time
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("\nExecution time: " + execution + " nanoseconds");

    }

    // public static int main(String[] args) {
    // if (args.length > 0)
    // return solution(args[0]);
    // return 0;

    // }

    public static int solution(int start, int end) {

        System.out.println("\n\n\nsolution( " + start + " , " + end + " )");

        int startRow = start / 8;
        int startCol = start % 8;

        int endRow = end / 8;
        int endCol = end % 8;

        System.out.println("startRow: " + startRow + " startCol: " + startCol);
        System.out.println("endRow: " + endRow + " endCol: " + endCol);

        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);

        System.out.println("rowDiff: " + rowDiff + " colDiff: " + colDiff);

        int count = 0;
        int limit = 0;

        while (count == 0) {
            limit++;
            count = calculateNextIndex(startRow, startCol, endRow, endCol, 0, limit);
        }
        return count;
    }

    static int calculateNextIndex(int row, int col, int destRow, int destCol, int current, int limit) {

        System.out.println("calculateNextIndex( " + row + " , " + col + " , " + destRow + " , " + destCol + " , "
                + current + " , " + limit + " )");

        int rowDiff = Math.abs(row - destRow);
        int colDiff = Math.abs(col - destCol);

        if (rowDiff == 0 && colDiff == 0) {
            System.out.println("Reached destination in " + current + " moves.");
            return limit;
        }

        if (current == limit) {
            System.out.println("Reached limit. No possible moves.");
            return 0;
        }

        for (int i = 0; i < moves.length; i++) {
            if (row + moves[i][0] >= 0 && row + moves[i][0] < 8 && col + moves[i][1] >= 0 && col + moves[i][1] < 8) {
                System.out
                        .println("Move" + i + "\nrow: " + (row + moves[i][0]) + " col: " + (col + moves[i][1]) + "\n");
                int result = calculateNextIndex(row + moves[i][0], col + moves[i][1], destRow, destCol, current + 1,
                        limit);
                if (result != 0)
                    return result;
            }

        }

        System.out.println("No 8possible moves");

        return 0;

    }

    static public int[] possibleMoves(int row, int col) {

        int possible[] = new int[8];
        int index = 0;
        for (int i = 0; i < moves.length; i++) {
            if (row + moves[i][0] >= 0 && row + moves[i][0] < 8 && col + moves[i][1] >= 0 && col + moves[i][1] < 8) {
                possible[index++] = i;
                System.out
                        .println("Move" + i + "\nrow: " + (row + moves[i][0]) + " col: " + (col + moves[i][1]) + "\n");
            }
        }
        return possible;
    }

}
