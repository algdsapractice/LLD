package company.coursera;

public class RegexParser {

    public static void main(String[] args) {

        // Test cases for simple substring search
        assert substring("hello world", "hello") : "Test case failed: substring(\"hello world\", \"hello\")";
        assert substring("hello world", "world") : "Test case failed: substring(\"hello world\", \"world\")";
        assert !substring("hello world", "bye") : "Test case failed: substring(\"hello world\", \"bye\")";
        assert substring("hello worl", "wo") : "Test case failed: substring(\"hello worl\", \"wo\")";

        // Test cases for wildcard match (with '.')
        assert wildcardMatch("hello world", ".ello") : "Test case failed: wildcardMatch(\"hello world\", \".ello\")";
        assert wildcardMatch("hello world", "worl.") : "Test case failed: wildcardMatch(\"hello world\", \"worl.\")";
        assert wildcardMatch("hello world", "....o") : "Test case failed: wildcardMatch(\"hello world\", \"....o\")";
        assert !wildcardMatch("hello world", "h....d") : "Test case failed: wildcardMatch(\"hello world\", \"h....d\")";
        assert !wildcardMatch("hello world", "he....z") : "Test case failed: wildcardMatch(\"hello world\", \"he....z\")";

        // Test cases using assert statements
        assert wildCardSubstring0or1("hello world", "he*llo") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"he*llo\")";
        assert !wildCardSubstring0or1("hello world", "h*llo") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"h*llo\")";
        assert !wildCardSubstring0or1("hello world", "he*lo") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"he*lo\")";
        assert !wildCardSubstring0or1("hello world", "he*l*o") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"he*l*o\")";
        assert !wildCardSubstring0or1("hello world", "w*d") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"w*d\")";
        assert wildCardSubstring0or1("abc", "a*b*c*") : "Test case failed: wildCardSubstring0or1(\"abc\", \"a*b*c*\")";
        assert wildCardSubstring0or1("hello world", "lo wo") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"lo wo\")";
        assert wildCardSubstring0or1("hello world", "o w") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"o w\")";
        assert !wildCardSubstring0or1("hello", "he*o") : "Test case failed: wildCardSubstring0or1(\"hello\", \"he*o\")";
        assert wildCardSubstring0or1("hello world", "ld") : "Test case failed: wildCardSubstring0or1(\"hello world\", \"ld\")";

        System.out.println("All test cases passed!");
    }

    private static boolean wildCardSubstring0or1(String text, String pattern) {
        int n = text.length();

        // Iterate over all possible starting positions in the text
        for (int i = 0; i < n; i++) {
            if (parse(text, pattern, i, 0)) {
                return true;  // Match found starting at index i
            }
        }
        return false;  // No match found in any substring
    }

    private static boolean parse(String text, String pattern, int i, int j) {
        int m = text.length();
        int n = pattern.length();

        if (j == n) {
            return true;
        }
//        if (i == m) {
//            // If text is exhausted but pattern is not, check if the remaining pattern can match an empty string
//            // In the case of zero occurrences with '*', we can skip those pattern characters
//            if (j + 1 < n && pattern.charAt(j + 1) == '*') {
//                return parse(text, pattern, i, j + 2);
//            }
//            return false;
//        }

        boolean isFirstMatch = i<m && text.charAt(i) == pattern.charAt(j);

        if (j + 1 < n && pattern.charAt(j + 1) == '*') {
            if (isFirstMatch) {
                // Try one occurrence
                if (parse(text, pattern, i + 1, j + 2)) {
                    return true;
                }
                // Try zero occurrence
                if (parse(text, pattern, i, j + 2)) {
                    return true;
                }
            }
            return false;
        } else {
            if (isFirstMatch) {
                return parse(text, pattern, i + 1, j + 1);
            } else {
                return false;
            }
        }
    }

    private static boolean wildcardMatch(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();

        for (int i = 0; i <= m - n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (pattern.charAt(j) != '.' && text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == n) {
                return true;
            }
        }
        return false;
    }

    private static boolean substring(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();
        for (int i = 0; i <= m - n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                char t = text.charAt(i + j);
                char p = pattern.charAt(j);
                if (t != p) {
                    break;
                }
            }
            if (j == n) {
                return true;
            }
        }
        return false;
    }
}
