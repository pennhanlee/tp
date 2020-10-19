package seedu.bookmark.logic.algo;

import javafx.collections.ObservableList;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * The EditDistance class manages the finding of suggestions for
 * misspelled words
 */
public class EditDistance {

    private final int SUGGESTION_LIMIT = 4;
    private final int DISTANCE_TOLERANCE = 3;
    private final String DELETE = "delete";
    private final String ADD = "add";
    private final ReadOnlyLibrary library;
    private final ArrayList<WordStore> wordList;

    public EditDistance(ReadOnlyLibrary library) {
        this.library = library;
        this.wordList = new ArrayList<>();
        initWordList();
    }

    /**
     * Initialises a List that stores unique words
     * in preparation for Usage by the EditDistance algorithm.
     */
    public void initWordList() {
        ArrayList<WordStore> wordStore = new ArrayList<>();
        ObservableList<Book> library = this.library.getBookList();
        for (Book book : library) {
            String bookName = book.getName().fullName;
            String[] words = bookName.split("\\s+"); //split into individual words
            handleBookName(words, ADD);
        }
    }

    /**
     * Deletes old words and adds new words into the
     * list
     * @param oldBook Book Object that is deleted.
     * @param newBook Book Object that is added.
     */
    public void updateWordList(Book oldBook, Book newBook) {
        String oldBookName = oldBook.getName().fullName;
        String[] oldWords = oldBookName.split("\\s+");
        handleBookName(oldWords, DELETE);
        String newBookName = newBook.getName().fullName;
        String[] newWords = newBookName.split("\\s+");
        handleBookName(newWords, ADD);
    }

    /**
     * Adds unique words from a newly added book into
     * the list.
     * @param book
     */
    public void addToWordList(Book book) {
        String bookName = book.getName().fullName;
        String[] words = bookName.split("\\s+");
        handleBookName(words, ADD);
    }

    /**
     * Deletes old words from the list
     * @param book
     */
    public void deleteFromWordList(Book book) {
        String bookName = book.getName().fullName;
        String[] words = bookName.split("\\s+");
        handleBookName(words, DELETE);
    }

    /**
     * Finds 3 words that are close to the misspelled word.
     * @param sourceWord mispelled word to find suggestion for
     */
    public PriorityQueue<WordStore> findSuggestion(String sourceWord) {
        int wordCount = 0;
        int suggestionCount = 0;
        PriorityQueue<WordStore> suggestions = new PriorityQueue<>(new WordComparator());
        while (suggestionCount < SUGGESTION_LIMIT && wordCount < wordList.size()) {
            WordStore targetWord = wordList.get(wordCount);
            int wordDistance = calculateDistance(sourceWord, targetWord.getWord());
            if (wordDistance <= DISTANCE_TOLERANCE) {
                suggestions.add(targetWord);
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
     * Check if a word exists already and AddCount if yes
     * @param word String object to be added
     */
    private void addWord(String word) {
        boolean added = false;
        for (WordStore wordstore : wordList) {
            if (wordstore.getWord().equals(word)) {
                wordstore.addCount();
                added = true;
                break;
            }
        }
        if (!added) {
            WordStore newWord = new WordStore(word);
            wordList.add(newWord);
        }
    }

    /**
     * Minuses the count of the word in the
     * word list and removes the word if count is 1
     * @param word
     */
    private void deleteWord(String word) {
        WordStore targetWord = null;
        for (WordStore wordStore : wordList) {
            if (wordStore.getWord().equals(word)) {
                targetWord = wordStore;
                break;
            }
        }
        assert targetWord != null;
        if (targetWord.getCount() == 1) {  //only got 1 instance which is the deleted book
            wordList.remove(targetWord);
        } else {
            targetWord.minusCount();
        }
    }

    /**
     * Adds words into the list
     * @param bookWords String Array of words
     */
    private void handleBookName(String[] bookWords, String action) {
        if (action.equals(ADD)) {
            for (String word : bookWords) {
                addWord(word);
            }
        } else if (action.equals(DELETE)) {
            for (String word : bookWords) {
                deleteWord(word);
            }
        }
    }

    /**
     * Used in ordering suggestions in findSuggestion method
     */
    private static class WordComparator implements Comparator<WordStore> {
        @Override
        public int compare(WordStore firstWord, WordStore secondWord) {
            int firstWordDistance = firstWord.getCount();
            int secondWordDistance = secondWord.getCount();
            return Integer.compare(firstWordDistance, secondWordDistance);
        }
    }
}
