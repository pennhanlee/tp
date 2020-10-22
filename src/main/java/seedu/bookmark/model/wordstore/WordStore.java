package seedu.bookmark.model.wordstore;

import java.util.ArrayList;
import java.util.List;

public abstract class WordStore {

    /**
     *
     * @param words
     */
    public abstract void addWords(List<String> words);

    /**
     *
     * @param words
     */
    public abstract void deleteWords(List<String> words);

    /**
     *
     */
    public abstract void addWord(Word word);

    /**
     *
     */
    public abstract void deleteWord(Word word);

    /**
     *
     * @return
     */
    public abstract ArrayList<Word> getWordStore();

    /**
     * Returns true if the list contains an equivalent word as the given argument.
     */
    public abstract boolean contains(String toCheck);

    /**
     *
     * @param wordStore
     * @param targetWord
     */
    public abstract void wordAdder(WordStore wordStore, String targetWord);

    /**
     *
     * @param wordStore
     * @param targetWord
     */
    public abstract void wordDeleter(WordStore wordStore, String targetWord);

}
