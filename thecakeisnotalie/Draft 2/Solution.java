public class Solution {

    public static void main(String[] args) {
        Solution obj = new Solution();

        // get the start time
        long start = System.nanoTime();

        // call the method
        if (args.length > 0)
            System.out.print(obj.solution(args[0]));
        else
            System.out.print(obj.solution(""));

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

    public static int solution(String str) {

        int strLen = str.length();
        if (strLen == 0 || strLen == 1) {
            return 1;
        }

        int count = checkSimilarity(2, strLen, str);
        System.out.println("count: " + count);
        if (count == 0)
            return 1;
        return count;
    }

    public static int checkSimilarity(int divideBy, int length, String str) {
        System.out.println("checkSimilarity( " + divideBy + " , " + length + " , " +
                str + " )");
        if (divideBy <= length) {
            if (length % divideBy != 0) {
                System.out.println("not divisible by " + divideBy);
                return checkSimilarity(divideBy + 1, length, str);
            }
            int lengthOfPattern = length / divideBy;
            int count = 0;
            for (int i = 0; i < divideBy - 1; i++) {

                if (str.substring(i * lengthOfPattern, (i + 1) * lengthOfPattern)
                        .equals(str.substring((i + 1) * lengthOfPattern, (i + 2) * lengthOfPattern))) {
                    count++;
                    System.out.println("Match found: " + str.substring(i * lengthOfPattern, (i +
                            1) * lengthOfPattern)
                            + " == " + str.substring((i + 1) * lengthOfPattern, (i + 2) *
                                    lengthOfPattern)
                            + "");

                } else
                    break;

            }
            if (count != divideBy - 1) {
                System.out.println("No match found");
                return checkSimilarity(divideBy + 1, length, str);
            } else {
                System.out.println("Multiplying " + divideBy + " with count");
                return divideBy * checkSimilarity(divideBy, lengthOfPattern, str.substring(0, lengthOfPattern));
            }

        }
        return 1;
    }

}
