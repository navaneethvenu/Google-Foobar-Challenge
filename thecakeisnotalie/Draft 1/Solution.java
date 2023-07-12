public class Solution {

	public static int main(String[] args) {
		if (args.length > 0)
			return solution(args[0]);
        return 0;

	}

	public static int solution(String str) {
		if (str.length() == 0) {
			return 0;
		}

		for (var i = 1; i < str.length(); i++) {
			var pattern = str.substring(0, i);
			var patternLength = pattern.length();
			var strLength = str.length();

			if (strLength % patternLength == 0) {
				var repetitions = strLength / patternLength;

				if (pattern.repeat(repetitions) == str) {
					return repetitions;
				}
			}
		}
		return 1;
	}

}
