package seedu.bookmark.model.wordstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class WordStore {

    private ArrayList<Word> wordStoreList;
    private HashMap<Integer, String> wordHashMap;

    public WordStore() {
        this.wordStoreList = new ArrayList<>();
        this.wordHashMap = new HashMap<>();
    }


    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        Integer hashedWord = toCheck.hashCode();
        return wordHashMap.containsKey(hashedWord);
    }

    public void addWords(List<String> words) {
        for (String word : words) {
            wordAdder(word);
        }
    }

    public void deleteWords(List<String> words) {
        for (String word : words) {
            wordDeleter(word);
        }
    }

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

    public void wordDeleter(String targetWord) {
        requireNonNull(targetWord);
        Optional<Word> storedWord = wordStoreList.stream().filter(word -> word.getWord()
                .equals(targetWord)).findFirst();
        if (!storedWord.isEmpty()) {
            Word existingWord = storedWord.get();
            if (existingWord.getCount() == 1) { //only got 1 instance which is the deleted book
                this.deleteWord(existingWord);
            } else {
                existingWord.minusCount();
            }
        }
    }

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
