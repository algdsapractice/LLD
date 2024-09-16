package company.coursera;

// These has 3 questions 2 are here and rest 1 different type of it is part of zerooronewildcardmatch class

public class StringMatcher {

    /**
     * Simple substring search.
     *
     * @param text The main string to search within.
     * @param query The substring to search for.
     * @return True if the query is found as a substring of the text.
     */
    public static boolean simpleSubstringSearch(String text, String query) {
        if (query.length() > text.length()) {
            return false;
        }

        for (int i = 0; i <= text.length() - query.length(); i++) {
            int j;
            for (j = 0; j < query.length(); j++) {
                if (text.charAt(i + j) != query.charAt(j)) {
                    break;
                }
            }
            if (j == query.length()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Wildcard match with '.' to match exactly one character.
     *
     * @param text The main string to search within.
     * @param query The query string containing '.' as a wildcard.
     * @return True if the query matches the text considering '.' as a wildcard.
     */
    public static boolean wildcardMatch(String text, String query) {
        if (query.length() > text.length()) {
            return false;
        }

        for (int i = 0; i <= text.length() - query.length(); i++) {
            int j;
            for (j = 0; j < query.length(); j++) {
                if (query.charAt(j) != '.' && query.charAt(j) != text.charAt(i + j)) {
                    break;
                }
            }
            if (j == query.length()) {
                return true;
            }
        }

        return false;
    }

    private static boolean match(String text, int ti, String query, int pi, Boolean[][] memo) {
        int tn = text.length();
        int pn = query.length();

        if (memo[ti][pi] != null) {
            return memo[ti][pi];
        }

        if (pi == pn) {
            memo[ti][pi] = true;
            return true;
        }

        if (ti == tn) {
            if (pi + 1 < pn && query.charAt(pi + 1) == '*') {
                boolean result = match(text, ti, query, pi + 2, memo);
                memo[ti][pi] = result;
                return result;
            } else {
                memo[ti][pi] = false;
                return false;
            }
        }

        boolean result;

        if (pi + 1 < pn && query.charAt(pi + 1) == '*') {
            // Zero occurrence
            boolean zeroOccurrence = match(text, ti, query, pi + 2, memo);

            // One occurrence
            boolean oneOccurrence = false;
            if (query.charAt(pi) == '.' || query.charAt(pi) == text.charAt(ti)) {
                oneOccurrence = match(text, ti + 1, query, pi + 2, memo);
            }

            result = zeroOccurrence || oneOccurrence;
        } else {
            if (query.charAt(pi) == '.' || query.charAt(pi) == text.charAt(ti)) {
                result = match(text, ti + 1, query, pi + 1, memo);
            } else {
                result = false;
            }
        }

        memo[ti][pi] = result;
        return result;
    }

    public static void main(String[] args) {
        // Test cases for simple substring search
        System.out.println(simpleSubstringSearch("hello world", "hello")); // true
        System.out.println(simpleSubstringSearch("hello world", "world")); // true
        System.out.println(simpleSubstringSearch("hello world", "bye"));   // false
        System.out.println(simpleSubstringSearch("hello world", "wo"));    // true

        // Test cases for wildcard match (with '.')
        System.out.println(wildcardMatch("hello world", ".ello"));    // true
        System.out.println(wildcardMatch("hello world", "worl."));    // true
        System.out.println(wildcardMatch("hello world", "....o"));    // true
        System.out.println(wildcardMatch("hello world", "h....d"));   // true
        System.out.println(wildcardMatch("hello world", "he....z"));  // false


    }
}
