package seedu.bookmark.logic.algo;

import javafx.collections.ObservableList;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;

import java.util.ArrayList;
import java.util.Set;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;


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
    private final ArrayList<WordStore> nameWordBank;
    private final ArrayList<WordStore> genreWordBank;
    private final ArrayList<WordStore> tagWordBank;

    public EditDistance(ReadOnlyLibrary library) {
        this.library = library;
        this.nameWordBank = new ArrayList<>();
        this.genreWordBank = new ArrayList<>();
        this.tagWordBank = new ArrayList<>();
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
            handleBook(book, ADD);
        }
    }

    /**
     * Deletes old words and adds new words into the
     * list
     * @param oldBook Book Object that is deleted.
     * @param newBook Book Object that is added.
     */
    public void updateWordBank(Book oldBook, Book newBook) {
        handleBook(oldBook, DELETE);
        handleBook(newBook, ADD);
    }

    /**
     * Adds unique words from a newly added book into
     * the list.
     * @param book book to be added
     */
    public void addToWordBank(Book book) {
        handleBook(book, ADD);
    }

    /**
     * Deletes old words from the list
     * @param book book to be deleted
     */
    public void deleteFromWordBank(Book book) {
        handleBook(book, DELETE);
    }

    /**
     * Finds 3 words that are close to the misspelled word.
     * @param sourceWord mispelled word to find suggestion for
     */
    public ArrayList<WordStore> findSuggestion(String sourceWord, Prefix prefix) {
        ArrayList<WordStore> wordBank = nameWordBank; //by default to prevent unknown prefix
        if (prefix.equals(PREFIX_NAME)) {
            wordBank = nameWordBank;
        } else if (prefix.equals(PREFIX_GENRE)) {
            wordBank = genreWordBank;
        } else if (prefix.equals(PREFIX_TAG)) {
            wordBank = tagWordBank;
        }
        assert wordBank != null;
        int wordCount = 0;
        int suggestionCount = 0;
        ArrayList<WordStore> suggestions = new ArrayList<>();
        while (suggestionCount < SUGGESTION_LIMIT && wordCount < wordBank.size()) {
            WordStore targetWord = wordBank.get(wordCount);
            int wordDistance = calculateDistance(sourceWord, targetWord.getWord());
            if (wordDistance <= DISTANCE_TOLERANCE && wordDistance > 0) {
                System.out.println("Word: " + targetWord.getWord() + " Distance: " + wordDistance);
                WordStore wordCopy = new WordStore(targetWord.getWord(), wordDistance);
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
     * Adds words into the list
     * @param book book to be processed
     */
    private void handleBook(Book book, String action) {
        String bookName = book.getName().fullName;
        String genre = book.getGenre().value;
        Set<Tag> tags = book.getTags();
        if (action.equals(ADD)) {
            addWords(bookName, genre, tags);
        } else if (action.equals(DELETE)) {
            deleteWords(bookName, genre, tags);
        }
    }

    private void addWords(String name, String genre, Set<Tag> tags) {
        String[] nameWords = name.split("\\s+"); //split into individual words
        for (String word : nameWords) {
            wordAdder(nameWordBank, word);
        }
        wordAdder(genreWordBank, genre);
        for (Tag tag : tags) {
            wordAdder(tagWordBank, tag.getTagName());
        }
    }

    private void deleteWords(String name, String genre, Set<Tag> tags) {
        String[] nameWords = name.split("\\s+"); //split into individual words
        for (String word : nameWords) {
            wordDeleter(nameWordBank, word);
        }
        wordDeleter(genreWordBank, genre);
        for (Tag tag : tags) {
            wordDeleter(tagWordBank, tag.getTagName());
        }
    }

    private void wordAdder(ArrayList<WordStore> wordBank, String word) {
        boolean added = false;
        for (WordStore wordstore : wordBank) {
            if (wordstore.getWord().equals(word)) {
                wordstore.addCount();
                added = true;
                break;
            }
        }
        if (!added) {
            WordStore newWord = new WordStore(word);
            wordBank.add(newWord);
        }
    }

    private void wordDeleter(ArrayList<WordStore> wordBank, String word) {
        WordStore targetWord = null;
        for (WordStore wordStore : wordBank) {
            if (wordStore.getWord().equals(word)) {
                targetWord = wordStore;
                break;
            }
        }
        assert targetWord != null;
        if (targetWord.getCount() == 1) {  //only got 1 instance which is the deleted book
            wordBank.remove(targetWord);
        } else {
            targetWord.minusCount();
        }
    }
}
