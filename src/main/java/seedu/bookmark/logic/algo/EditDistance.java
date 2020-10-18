package seedu.bookmark.logic.algo;

import javafx.collections.ObservableList;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.book.Book;

import java.util.ArrayList;


/**
 * The EditDistance class manages the finding of suggestions for
 * misspelled words
 */
public class EditDistance {

    private final Library library;
    private final ArrayList<String> wordList;

    public EditDistance(Library library) {
        this.library = library;
        this.wordList = new ArrayList<>();
    }

    /**
     * Initialises a List that stores unique words
     * in preparation for Usage by the EditDistance algorithm.
     */
    public void initWordList() {
        ObservableList<Book> library = this.library.getBookList();
        for (Book book : library) {
            String bookName = book.getName().fullName;
            String[] words = bookName.split("\\s+"); //split into individual words
            addWords(words, this.wordList);
        }
    }

    /**
     * Adds unique words from a newly added book into
     * the list.
     * @param book Book Object that is added.
     */
    public void updateWordList(Book book) {
        String bookName = book.getName().fullName;
        String[] words = bookName.split("\\s+");
        addWords(words, this.wordList);
    }

    /**
     * Finds 3 words that are close to the misspelled word.
     * @param sourceWord mispelled word to find suggestion for
     */
    public ArrayList<String> findSuggestion(String sourceWord) {
        int wordCount = 0;
        int suggestionCount = 0;
        ArrayList<String> suggestions = new ArrayList<>();
        while (suggestionCount < 4 && wordCount < wordList.size()) {
            wordCount++;
            String targetWord = wordList.get(wordCount);
            int wordDistance = calculateDistance(sourceWord, targetWord);
            if (wordDistance <= 3) {
                suggestions.add(targetWord);
                suggestionCount++;
            }
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

        //setting up 2D array for cost calculation
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

    /**
     * Checks if the word being added already exists in the list
     * @param word String object to be added
     * @return true if word exists, else false
     */
    private boolean alreadyExist(String word) {
        return wordList.contains(word);
    }

    /**
     * Adds words into the list
     * @param bookWords String Array of words
     * @param wordList ArrayList of String
     */
    private void addWords(String[] bookWords, ArrayList<String> wordList) {
        for (String word : bookWords) {
            if (alreadyExist(word)) {
                continue;
            }
            wordList.add(word);
        }
    }

}
