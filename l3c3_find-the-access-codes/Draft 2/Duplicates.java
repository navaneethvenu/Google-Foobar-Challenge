import java.util.Arrays;

public class Duplicates {
    public static void main(String args[]) {

        int[] arr = new int[] { 1, 2, 3, 3, 3, 3, 4, 5, 5 };
        int[][] duplicates = removeDuplicates(arr);
        for (int[] element : duplicates) {
            System.out.println(Arrays.toString(element));
        }

    }

    public static int[][] removeDuplicates(int[] arr) {
        System.out.println("Im here");
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
