package seedu.bookmark.model.wordstore;

import seedu.bookmark.model.book.Book;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class NameWordStore extends WordStore {

    ArrayList<Word> nameWordStore;

    public NameWordStore() {
        this.nameWordStore = new ArrayList<>();
    }

    @Override
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        return nameWordStore.stream().anyMatch(word -> word.getWord().equals(toCheck));
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
            nameWordStore.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get().addCount();
        } else {
            Word newWord = new Word(targetWord);
            this.addWord(newWord);
        }
    }

    @Override
    public void wordDeleter(String targetWord) {
        requireNonNull(targetWord);
        Word existingWord = nameWordStore.stream().filter(word -> word.getWord()
                .equals(targetWord)).findFirst().get();
        if (existingWord.getCount() == 1) {  //only got 1 instance which is the deleted book
            this.deleteWord(existingWord);
        } else {
            existingWord.minusCount();
        }
    }

    @Override
    public ArrayList<Word> getWordStore() {
        return this.nameWordStore;
    }

    private void addWord(Word word) {
        this.nameWordStore.add(word);
    }

    private void deleteWord(Word word) {
        this.nameWordStore.remove(word);
    }
}
