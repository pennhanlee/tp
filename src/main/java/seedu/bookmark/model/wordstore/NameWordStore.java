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
        this.nameWordStore.add(word);
    }

    @Override
    public void deleteWord(Word word) {
        this.nameWordStore.remove(word);
    }

    @Override
    public ArrayList<Word> getWordStore() {
        return this.nameWordStore;
    }

    @Override
    public void wordAdder(WordStore wordStore, String targetWord) {
        boolean added = contains(targetWord);
        if (added) {
            nameWordStore.stream().filter(word -> word.getWord()
                    .equals(targetWord)).findFirst().get().addCount();
        } else {
            Word newWord = new Word(targetWord);
            wordStore.addWord(newWord);
        }
    }

    @Override
    public void wordDeleter(WordStore wordStore, String targetWord) {
        Word existingWord = nameWordStore.stream().filter(word -> word.getWord()
                .equals(targetWord)).findFirst().get();
        if (existingWord.getCount() == 1) {  //only got 1 instance which is the deleted book
            wordStore.deleteWord(existingWord);
        } else {
            existingWord.minusCount();
        }
    }
}
