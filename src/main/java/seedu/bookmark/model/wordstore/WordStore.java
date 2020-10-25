package seedu.bookmark.model.wordstore;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;

public class WordStore {

    private HashMap<Integer, Word> wordStore;

    /**
     * Creates a WordStore object
     */
    public WordStore() {
        this.wordStore = new HashMap<>();
    }

    /**
     * Returns true if the list contains an String which matches a word in the wordStore
     */
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        Integer hashedWord = toCheck.hashCode();
        return wordStore.containsKey(hashedWord);
    }

    /**
     * Adds words from a given list into the Store
     * @param words
     */
    public void addWords(List<String> words) {
        for (String word : words) {
            wordAdder(word);
        }
    }

    /**
     * Deletes words from a given list out of the Store
     * @param words
     */
    public void deleteWords(List<String> words) {
        for (String word : words) {
            wordDeleter(word);
        }
    }

    /**
     * Processes the provided word into wordStore by either creating a new Word or incrementing the count based on
     * whether the word already exists
     * @param targetWord
     */
    public void wordAdder(String targetWord) {
        requireNonNull(targetWord);
        boolean added = contains(targetWord);
        if (added) {
            Word targetWordObj = wordStore.get(targetWord.hashCode());
            targetWordObj.addCount();
        } else {
            Word newWord = new Word(targetWord);
            this.addWord(newWord);
        }
    }

    /**
     * Processes the provided word out of wordStore by either removing the word or minusing the count based on
     * whether the word count > 1
     * If there are no words found, the deletion is ignored
     * @param targetWord
     */
    public void wordDeleter(String targetWord) {
        requireNonNull(targetWord);
        Word targetWordObj = wordStore.get(targetWord.hashCode());
        if (targetWordObj == null) {
            throw new WordNotFoundException();
        }
        if (targetWordObj.getCount() == 1) { //only got 1 instance which is the deleted book
            this.deleteWord(targetWordObj);
        } else {
            targetWordObj.minusCount();
        }
    }

    /**
     * Returns the wordStore
     * @return wordStore
     */
    public HashMap<Integer, Word> getWordStore() {
        return this.wordStore;
    }

    /**
     * Returns the Word object
     * @return Word
     */
    public Word getWord(String word) {
        Integer wordHash = word.hashCode();
        return this.wordStore.get(wordHash);
    }


    private void addWord(Word word) {
        this.wordStore.put(word.hashCode(), word);

    }

    private void deleteWord(Word word) {
        this.wordStore.remove(word.hashCode(), word);
    }

}