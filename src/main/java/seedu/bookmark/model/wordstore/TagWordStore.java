package seedu.bookmark.model.wordstore;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TagWordStore extends WordStore {

    ArrayList<Word> tagWordStore;

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
            wordAdder(this, word);
        }
    }

    @Override
    public void deleteWords(List<String> words) {
        for (String word : words) {
            wordDeleter( this, word);
        }
    }

    @Override
    public void addWord(Word word) {
        this.tagWordStore.add(word);
    }

    @Override
    public void deleteWord(Word word) {
        this.tagWordStore.remove(word);
    }

    public ArrayList<Word> getWordStore() {
        return this.tagWordStore;
    }

    @Override
    public void wordAdder(WordStore wordStore, String targetWord) {
        boolean added = contains(targetWord);
        if (added) {
            tagWordStore.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get().addCount();
        } else {
            Word newWord = new Word(targetWord);
            wordStore.addWord(newWord);
        }
    }

    @Override
    public void wordDeleter(WordStore wordStore, String targetWord) {
        Word existingWord = tagWordStore.stream().filter(word -> word.getWord()
                .equals(targetWord)).findFirst().get();
        if (existingWord.getCount() == 1) {  //only got 1 instance which is the deleted book
            wordStore.deleteWord(existingWord);
        } else {
            existingWord.minusCount();
        }
    }
}
