package seedu.bookmark.model.wordstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.HARRY;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;
import seedu.bookmark.testutil.TypicalWords;

public class GenreWordStoreTest {

    private GenreWordStore genreWordStore = new GenreWordStore();

    //------------------- contains() ------------------------------//

    @Test
    public void contains_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> genreWordStore.contains(null));
    }

    @Test
    public void contains_wordNotInList_returnsFalse() {
        assertFalse(genreWordStore.contains(HARRY));
    }

    @Test
    public void contains_wordInList_returnsTrue() {
        genreWordStore.wordAdder(HARRY);
        String harry = CORRECT_HARRY.getWord();
        assertTrue(genreWordStore.contains(harry));
    }

    //--------------------- wordAdder() ---------------------------//

    @Test
    public void add_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> genreWordStore.wordAdder(null));
    }

    @Test
    public void add_newWord_countOne() {
        genreWordStore.wordAdder(HARRY);
        Word harry = genreWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    @Test
    public void add_existingWord_increaseCount() {
        genreWordStore.wordAdder(HARRY);
        genreWordStore.wordAdder(HARRY);
        Word harry = genreWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(2, harry.getCount());

    }

    //-------------------- wordDeleter() -------------------------//

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> genreWordStore.wordDeleter(null));
    }

    @Disabled
    @Test
    public void delete_bookDoesNotExist_throwsWordNotFoundException() {
        assertThrows(WordNotFoundException.class, () -> genreWordStore.wordDeleter(HARRY));
    }

    @Test
    public void delete_existingWord_countZero() {
        genreWordStore.wordAdder(HARRY);
        genreWordStore.wordDeleter(HARRY);
        assertFalse(genreWordStore.contains(HARRY));
    }
    @Test
    public void delete_existingBook_countNonZero() {
        genreWordStore.wordAdder(HARRY);
        genreWordStore.wordAdder(HARRY); //increase count to 2
        genreWordStore.wordDeleter(HARRY);
        Word harry = genreWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    //--------------------- addWords() ---------------------------//

    @Test
    public void addWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        genreWordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(genreWordStore.contains(word));
        }
    }

    @Test
    public void add_existingWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        genreWordStore.addWords(listOfWords);
        genreWordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(genreWordStore.contains(word));
            Word targetWord = genreWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(2, targetWord.getCount());
        }
    }

    //---------------------- deleteWords() -----------------------//

    @Test
    public void deleteWords_countOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        genreWordStore.addWords(listOfWords);
        genreWordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertFalse(genreWordStore.contains(word));
        }
    }

    @Test
    public void deleteWords_countBiggerThanOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        genreWordStore.addWords(listOfWords);
        genreWordStore.addWords(listOfWords);
        genreWordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(genreWordStore.contains(word));
            Word targetWord = genreWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
    }

    //---------------------- getWordStore() ------------------------//

    @Test
    public void getWordStore() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        List<Word> listOfWordObjs = TypicalWords.getTypicalWords();
        genreWordStore.addWords(listOfWords);
        List<Word> genreWordStoreList = genreWordStore.getWordStore();
        assertEquals(listOfWordObjs, genreWordStoreList);
    }
}

