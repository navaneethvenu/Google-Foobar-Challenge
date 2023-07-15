class RegularSolution {

    public static void main(String args[]) {
        int[] array = { 2, 4, 4, 8, 8, 8, 16 };
        System.out.println(solution(array));
    }

    public static int solution(int[] l) {
        int count = 0;
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                for (int k = j + 1; k < l.length; k++) {
                    if (l[k] % l[j] == 0 && l[j] % l[i] == 0) {
                        System.out
                                .println("(" + l[i] + "(" + i + "), " + l[j] + "(" + j + "), " + l[k] + "(" + k + "))");
                        count++;
                    }
                }
            }
        }
        return count;
    }
}