package seedu.bookmark.model.wordstore;

import java.util.ArrayList;
import java.util.List;

public abstract class WordStore {

    /**
     * Adds words from a given list into the Store
     * @param words
     */
    public abstract void addWords(List<String> words);

    /**
     * Deletes words from a given list out of the Store
     * @param words
     */
    public abstract void deleteWords(List<String> words);

    /**
     * Returns the wordStore
     * @return wordStore
     */
    public abstract ArrayList<Word> getWordStore();

    /**
     * Returns true if the list contains an String which matches a word in the wordStore
     */
    public abstract boolean contains(String toCheck);

    /**
     * Processes the provided word into wordStore by either creating a new Word or incrementing the count based on
     * whether the word already exists
     * @param targetWord
     */
    public abstract void wordAdder(String targetWord);

    /**
     * Processes the provided word out of wordStore by either removing the word or minusing the count based on
     * whether the word count > 1
     * If there are no words found, the deletion is ignored
     * @param targetWord
     */
    public abstract void wordDeleter(String targetWord);

}
