package company.coursera;

public class ZeroOrManyWildcardMatcherWithSubstring {

    /**
     * Matches the text against the pattern where '*' represents zero or many occurrences
     * of the preceding character. This implementation checks if the pattern matches
     * any substring of the text.
     *
     * @param text    The input string to match.
     * @param pattern The pattern string containing '*' as the wildcard.
     * @return True if the pattern matches any substring of the text; otherwise, false.
     */
    public boolean isMatch(String text, String pattern) {
        // Iterate over all possible starting positions in the text
        for (int i = 0; i <= text.length(); i++) {
            if (matchHelper(text, pattern, i, 0)) {
                return true;  // If any substring matches, return true
            }
        }
        return false;  // No match found for any substring
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
            return true;  // End of pattern, match found
        }

        // Check if current characters match
        boolean firstMatch = (i < n) && (text.charAt(i) == pattern.charAt(j));

        // Handle '*' wildcard (zero or many occurrences)
        if (j + 1 < m && pattern.charAt(j + 1) == '*') {
            // Zero occurrence: Skip current character and '*'
            if (matchHelper(text, pattern, i, j + 2)) {
                return true;
            }

            // Many occurrences: If first characters match, advance the text index
            while (firstMatch) {
                if (matchHelper(text, pattern, i + 1, j + 2)) {
                    return true;
                }
                i++;
                firstMatch = (i < n) && (text.charAt(i) == pattern.charAt(j));
            }

            // No match found in zero or many occurrences cases
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
        ZeroOrManyWildcardMatcherWithSubstring matcher = new ZeroOrManyWildcardMatcherWithSubstring();

        // Test cases
        System.out.println(matcher.isMatch("hello world", "he*llo"));  // Expected: true
        System.out.println(matcher.isMatch("hello world", "h*llo"));   // Expected: true
        System.out.println(matcher.isMatch("hello world", "he*o"));    // Expected: false
        System.out.println(matcher.isMatch("hello world", "he*l*o"));  // Expected: true
        System.out.println(matcher.isMatch("abc", "a*b*c*"));          // Expected: true
        System.out.println(matcher.isMatch("hello", "he*o"));          // Expected: true
        System.out.println(matcher.isMatch("aaaab", "a*b"));           // Expected: true
        System.out.println(matcher.isMatch("abcd", "a*d"));            // Expected: true
    }
}
