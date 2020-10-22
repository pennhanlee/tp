package seedu.bookmark.logic.algorithm;

import seedu.bookmark.model.WordBank;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.logic.parser.Prefix;

import java.util.ArrayList;
import java.util.List;

/**
 * The EditDistance class manages the finding of suggestions for
 * misspelled words
 */
public class SuggestionAlgorithm {

    private final int SUGGESTION_LIMIT = 4;
    private final int DISTANCE_TOLERANCE = 3;
    private final WordBank wordBank;

    public SuggestionAlgorithm(WordBank wordBank) {
        this.wordBank = wordBank;
    }

    /**
     * Finds 3 words that are close to the misspelled word.
     * @param sourceWord mispelled word to find suggestion for
     */
    public ArrayList<Word> findSuggestion(String sourceWord, Prefix prefix) {
        String prefixName = prefix.getPrefix();
        List<Word> wordStore = wordBank.getWordStore(prefixName).getWordStore();
        int wordCount = 0;
        int suggestionCount = 0;
        ArrayList<Word> suggestions = new ArrayList<>();
        while (suggestionCount < SUGGESTION_LIMIT && wordCount < wordStore.size()) {
            Word targetWord = wordStore.get(wordCount);
            int wordDistance = calculateDistance(sourceWord, targetWord.getWord());
            if (wordDistance <= DISTANCE_TOLERANCE && wordDistance > 0) {
                System.out.println("Word: " + targetWord.getWord() + " Distance: " + wordDistance);
                Word wordCopy = new Word(targetWord.getWord(), wordDistance);
                suggestions.add(wordCopy);
                suggestionCount++;
            }
            wordCount++;
        }
        return suggestions;
    }

    /**
     * Calculates the string Distance between source and target
     * strings using the Damerau-Levenshtein algorithm. Distance is case sensitive.
     * @param source String object
     * @param target String object
     * @return The edit distance between source and target strings
     * @throws IllegalArgumentException if either source or target is null
     */
    public int calculateDistance(String source, String target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }

        int sourceLength = source.length();
        int targetLength = target.length();

        if (sourceLength == 0) {
            return targetLength;
        } else if (targetLength == 0) {
            return sourceLength;
        }

        int[][] distanceArray = new int[sourceLength+1][targetLength+1];
        for (int i = 0; i < sourceLength + 1; i++) {
            distanceArray[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            distanceArray[0][j] = j;
        }

        //setting up 2D array for cost calculation --> Deliberate double forloop.
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;  //testing if char are same
                distanceArray[i][j] = Math.min(Math.min(distanceArray[i - 1][j] + 1, distanceArray[i][j - 1] + 1),
                                                distanceArray[i - 1][j - 1] + cost); //setting up the cost using 2d arr
                if (i > 1 &&
                        j > 1 &&
                        source.charAt(i - 1) == target.charAt(j - 2) &&
                        source.charAt(i - 2) == target.charAt(j - 1)) { //for transpositional swapped chars
                    distanceArray[i][j] = Math.min(distanceArray[i][j], distanceArray[i - 2][j - 2] + cost);
                }
            }
        }
        return distanceArray[sourceLength][targetLength];
    }
}
