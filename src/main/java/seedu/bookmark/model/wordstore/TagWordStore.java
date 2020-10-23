package seedu.bookmark.model.wordstore;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public class TagWordStore extends WordStore {

    private ArrayList<Word> tagWordStore;

    public TagWordStore() {
        this.tagWordStore = new ArrayList<>();
    }

    @Override
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        return tagWordStore.stream().anyMatch(word -> word.getWord().equals(toCheck));
    }

    @Override
    public void addWords(List<String> words) {
        for (String word : words) {
            wordAdder(word);
        }
    }

    @Override
    public void deleteWords(List<String> words) {
        for (String word : words) {
            wordDeleter(word);
        }
    }



    @Override
    public void wordAdder(String targetWord) {
        requireNonNull(targetWord);
        boolean added = contains(targetWord);
        if (added) {
            tagWordStore.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get().addCount();
        } else {
            Word newWord = new Word(targetWord);
            this.addWord(newWord);
        }
    }

    @Override
    public void wordDeleter(String targetWord) {
        requireNonNull(targetWord);
        if (!tagWordStore.isEmpty()) {
            Word existingWord = tagWordStore.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get();
            if (existingWord.getCount() == 1) { //only got 1 instance which is the deleted book
                this.deleteWord(existingWord);
            } else {
                existingWord.minusCount();
            }
        }
    }

    @Override
    public ArrayList<Word> getWordStore() {
        return this.tagWordStore;
    }

    private void addWord(Word word) {
        this.tagWordStore.add(word);
    }

    private void deleteWord(Word word) {
        this.tagWordStore.remove(word);
    }
}
