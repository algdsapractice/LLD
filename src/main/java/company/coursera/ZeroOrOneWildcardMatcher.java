package company.coursera;

import java.util.Arrays;



public class ZeroOrOneWildcardMatcher {

    /**
     * Matches the text against the pattern where '*' represents zero or one occurrence
     * of the preceding character. This implementation uses recursion without memoization.
     *
     * @param text    The input string to match.
     * @param pattern The pattern string containing '*' as the wildcard.
     * @return True if the text matches the pattern; otherwise, false.
     */
    public boolean isMatch(String text, String pattern) {
        return matchHelper(text, pattern, 0, 0);
    }

    /**
     * Helper function to perform the matching using recursion.
     *
     * @param text    The input string.
     * @param pattern The pattern string.
     * @param i       Current index in the text.
     * @param j       Current index in the pattern.
     * @return True if text[i:] matches pattern[j:]; otherwise, false.
     */
    private boolean matchHelper(String text, String pattern, int i, int j) {
        int n = text.length();
        int m = pattern.length();

        // Base case: End of pattern
        if (j == m) {
            return i == n;
        }

        // Check if current characters match
        boolean firstMatch = (i < n) && (text.charAt(i) == pattern.charAt(j));

        // Handle '*' wildcard (zero or one occurrence)
        if (j + 1 < m && pattern.charAt(j + 1) == '*') {
            // Zero occurrence: Skip current character and '*'
            if (matchHelper(text, pattern, i, j + 2)) {
                return true;
            }

            // One occurrence: If first characters match, advance both indices
            if (firstMatch && matchHelper(text, pattern, i + 1, j + 2)) {
                return true;
            }

            // No match found in both zero and one occurrence cases
            return false;
        } else {
            // No wildcard: Proceed if current characters match
            if (firstMatch) {
                return matchHelper(text, pattern, i + 1, j + 1);
            } else {
                return false;
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        ZeroOrOneWildcardMatcher matcher = new ZeroOrOneWildcardMatcher();

        // Test cases
        System.out.println(matcher.isMatch("hello", "he*llo")); // Expected: true
        System.out.println(matcher.isMatch("hello", "h*llo"));  // Expected: false
        System.out.println(matcher.isMatch("hello", "he*lo"));  // Expected: false
        System.out.println(matcher.isMatch("hello", "he*l*o")); // Expected: false
        System.out.println(matcher.isMatch("abc", "a*b*c*"));   // Expected: true
        System.out.println(matcher.isMatch("abc", "a*c"));      // Expected: false
        System.out.println(matcher.isMatch("aaab", "a*b"));     // Expected: false
        System.out.println(matcher.isMatch("ab", "a*b*"));      // Expected: true
        System.out.println(matcher.isMatch("b", "a*b"));        // Expected: true
        System.out.println(matcher.isMatch("aab", "c*a*b"));    // Expected: false
    }
}
