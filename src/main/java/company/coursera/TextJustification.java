package company.coursera;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextJustification {

    public static void main(String[] args) {

        String[] words = {"This", "is", "an", "example", "of", "text", "justification"};
        int maxWidth = 16;

        List<String> result = fullJustify(words, maxWidth);

    }

    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList();
        int sumOfWordsLength = 0;
        List<StringBuilder> currentLineWords = new ArrayList();

        for(String word : words){

            int currentLineLength = word.length()+ sumOfWordsLength+currentLineWords.size();

            if(currentLineLength>maxWidth){
                int extraPadding = maxWidth- (sumOfWordsLength+currentLineWords.size()-1);
                int roundRobinSpaces = Math.max(currentLineWords.size()-1,1);
                for(int i=0; i<extraPadding;i++){

                    int index = i % roundRobinSpaces;
                    currentLineWords.get(index).append(" ");
                }
                result.add(currentLineWords.stream().map(StringBuilder::toString).collect(Collectors.joining(" ")));
                sumOfWordsLength = 0;
                currentLineWords = new ArrayList();
            }
            currentLineWords.add(new StringBuilder(word));
            sumOfWordsLength +=  word.length();
        }

        int extraSpaces= maxWidth- (sumOfWordsLength+currentLineWords.size()-1);
        String spaces = Stream.generate(()-> " ").limit(extraSpaces).collect(Collectors.joining(""));
        result.add(currentLineWords.stream().map(StringBuilder::toString).collect(Collectors.joining(" "))+spaces);

        return result;
    }
}
