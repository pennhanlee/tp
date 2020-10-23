package seedu.bookmark.model.wordstore;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;

public class WordStore {

    private ArrayList<Word> wordStoreList;
    private HashMap<Integer, String> wordHashMap;

    /**
     * Creates a WordStore object
     */
    public WordStore() {
        this.wordStoreList = new ArrayList<>();
        this.wordHashMap = new HashMap<>();
    }

    /**
     * Returns true if the list contains an String which matches a word in the wordStore
     */
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        Integer hashedWord = toCheck.hashCode();
        return wordHashMap.containsKey(hashedWord);
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
            wordStoreList.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get().addCount();
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
        Optional<Word> storedWord = wordStoreList.stream().filter(word -> word.getWord()
                .equals(targetWord)).findFirst();
        if (storedWord.isEmpty()) {
            throw new WordNotFoundException();
        }
        Word existingWord = storedWord.get();
        if (existingWord.getCount() == 1) { //only got 1 instance which is the deleted book
            this.deleteWord(existingWord);
        } else {
            existingWord.minusCount();
        }
    }

    /**
     * Returns the wordStore
     * @return wordStore
     */
    public ArrayList<Word> getWordStore() {
        return this.wordStoreList;
    }


    private void addWord(Word word) {
        String wordValue = word.getWord();
        this.wordHashMap.put(wordValue.hashCode(), wordValue);
        this.wordStoreList.add(word);

    }

    private void deleteWord(Word word) {
        String wordValue = word.getWord();
        this.wordHashMap.remove(wordValue.hashCode(), wordValue);
        this.wordStoreList.remove(word);
    }

}
