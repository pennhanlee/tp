package seedu.bookmark.algo.wordbank;

import javafx.collections.ObservableList;
import seedu.bookmark.algo.word.Word;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * @param wordStore
     * @param targetWord
     */
    protected void wordAdder(WordStore wordStore, String targetWord) {
        boolean added = false;
        for (Word existingWord : wordStore.getWordStore()) {
            if (existingWord.getWord().equals(targetWord)) {
                existingWord.addCount();
                added = true;
                break;
            }
        }
        if (!added) {
            Word newWord = new Word(targetWord);
            wordStore.addWord(newWord);
        }
    }

    /**
     *
     * @param wordStore
     * @param targetWord
     */
    protected void wordDeleter(WordStore wordStore, String targetWord) {
        Word word = null;
        for (Word existingWord : wordStore.getWordStore()) {
            if (existingWord.getWord().equals(targetWord)) {
                word = existingWord;
                break;
            }
        }
        assert word != null;
        if (word.getCount() == 1) {  //only got 1 instance which is the deleted book
            wordStore.deleteWord(word);
        } else {
            word.minusCount();
        }
    }

    /**
     *
     * @return
     */
    public abstract ArrayList<Word> getWordStore();

}
