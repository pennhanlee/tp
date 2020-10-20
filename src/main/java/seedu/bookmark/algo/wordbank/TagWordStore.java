package seedu.bookmark.algo.wordbank;

import seedu.bookmark.algo.word.Word;

import java.util.ArrayList;
import java.util.List;

public class TagWordStore extends WordStore {

    ArrayList<Word> tagWordStore;

    public TagWordStore() {
        this.tagWordStore = new ArrayList<>();
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

    }

    @Override
    public void deleteWord(Word word) {

    }

    public ArrayList<Word> getWordStore() {
        return this.tagWordStore;
    }
}
