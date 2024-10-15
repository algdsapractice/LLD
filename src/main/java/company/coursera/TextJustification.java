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

    private static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> justifiedString = new ArrayList<>();
        List<StringBuilder> currentLineWords = new ArrayList<>();
        int sumOfLengthOfWords =0;

        for(String word : words){
                int newLengthOfLine= sumOfLengthOfWords+word.length()+currentLineWords.size();

                if(newLengthOfLine > maxWidth){

                    int extraPadding = maxWidth - (sumOfLengthOfWords+ currentLineWords.size()-1);
                    int wordsInRoundRobinSize= Math.max(currentLineWords.size()-1,1);

                    for (int i =0; i<extraPadding;i++){
                        int index = i%wordsInRoundRobinSize;
                        currentLineWords.get(index).append(" ");
                    }

                    justifiedString.add(currentLineWords.stream().map(StringBuilder::toString).collect(Collectors.joining(" ")));

                } else{
                    currentLineWords.add( new StringBuilder(word));
                    sumOfLengthOfWords += word.length();
                }

                int extraSpace =  maxWidth - (sumOfLengthOfWords+ currentLineWords.size()-1);
                String spaces= Stream.generate(()->" ").limit(extraSpace).collect(Collectors.joining(""));

                justifiedString.add(currentLineWords.stream().map(StringBuilder::toString).collect(Collectors.joining(" "))+spaces);

        }


        return justifiedString;
    }
}
