package seedu.bookmark.model.wordstore;

import java.util.ArrayList;
import java.util.List;

public class GenreWordStore extends WordStore {

    ArrayList<Word> genreWordStore;

    public GenreWordStore() {
        this.genreWordStore = new ArrayList<>();
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
        this.genreWordStore.add(word);
    }

    @Override
    public void deleteWord(Word word) {
        this.genreWordStore.remove(word);
    }

    @Override
    public ArrayList<Word> getWordStore() {
        return this.genreWordStore;
    }


}
