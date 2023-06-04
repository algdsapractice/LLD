package company.coursera;
///wildcard_match("hello world", ".ello") : True
//wildcard_match("hello world", "..llo wor") : True
//wildcard_match("hello world", "..l.o") : True
//wildcard_match("hello world", "...llo") : False
public class WildCharcter {

    public static boolean wildcardMatch(String text, String query){
        char[] textCharArr = text.toCharArray();
        char[] qCharArr = query.toCharArray();
        boolean result = false;

        for (int i = 0; i < textCharArr.length; i++) {
            int j;
            for (j = 0; j < qCharArr.length && i + j < textCharArr.length; j++) {
                if (qCharArr[j] != '.' && qCharArr[j] != textCharArr[i + j]) {
                    break;
                }
            }
            if (j == qCharArr.length) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(wildcardMatch("hello world", ".ello")); // true
        System.out.println(wildcardMatch("hello world", "..llo wor")); // true
        System.out.println(wildcardMatch("hello world", "..l.o")); // true
        System.out.println(wildcardMatch("hello world", "...llo")); // false
    }
}
