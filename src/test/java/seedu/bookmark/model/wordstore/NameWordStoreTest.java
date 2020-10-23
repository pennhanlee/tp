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

public class NameWordStoreTest {

    private NameWordStore nameWordStore = new NameWordStore();

    //------------------- contains() ------------------------------//

    @Test
    public void contains_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nameWordStore.contains(null));
    }

    @Test
    public void contains_wordNotInList_returnsFalse() {
        assertFalse(nameWordStore.contains(HARRY));
    }

    @Test
    public void contains_wordInList_returnsTrue() {
        nameWordStore.wordAdder(HARRY);
        String harry = CORRECT_HARRY.getWord();
        assertTrue(nameWordStore.contains(harry));
    }

    //--------------------- wordAdder() ---------------------------//

    @Test
    public void add_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nameWordStore.wordAdder(null));
    }

    @Test
    public void add_newWord_countOne() {
        nameWordStore.wordAdder(HARRY);
        Word harry = nameWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    @Test
    public void add_existingWord_increaseCount() {
        nameWordStore.wordAdder(HARRY);
        nameWordStore.wordAdder(HARRY);
        Word harry = nameWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(2, harry.getCount());

    }

    //-------------------- wordDeleter() -------------------------//

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nameWordStore.wordDeleter(null));
    }

    @Disabled
    @Test
    public void delete_bookDoesNotExist_throwsWordNotFoundException() {
        assertThrows(WordNotFoundException.class, () -> nameWordStore.wordDeleter(HARRY));
    }

    @Test
    public void delete_existingWord_countZero() {
        nameWordStore.wordAdder(HARRY);
        nameWordStore.wordDeleter(HARRY);
        assertFalse(nameWordStore.contains(HARRY));
    }
    @Test
    public void delete_existingBook_countNonZero() {
        nameWordStore.wordAdder(HARRY);
        nameWordStore.wordAdder(HARRY); //increase count to 2
        nameWordStore.wordDeleter(HARRY);
        Word harry = nameWordStore.getWordStore().stream()
                            .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    //--------------------- addWords() ---------------------------//

    @Test
    public void addWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(nameWordStore.contains(word));
        }
    }

    @Test
    public void add_existingWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        nameWordStore.addWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(nameWordStore.contains(word));
            Word targetWord = nameWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(2, targetWord.getCount());
        }
    }

    //---------------------- deleteWords() -----------------------//

    @Test
    public void deleteWords_countOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        nameWordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertFalse(nameWordStore.contains(word));
        }
    }

    @Test
    public void deleteWords_countBiggerThanOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        nameWordStore.addWords(listOfWords);
        nameWordStore.deleteWords(listOfWords);
        for (String word : listOfWords) {
            assertTrue(nameWordStore.contains(word));
            Word targetWord = nameWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
    }

    //---------------------- getWordStore() ------------------------//

    @Test
    public void getWordStore() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        List<Word> listOfWordObjs = TypicalWords.getTypicalWords();
        nameWordStore.addWords(listOfWords);
        List<Word> nameWordStoreList = nameWordStore.getWordStore();
        assertEquals(listOfWordObjs, nameWordStoreList);
    }
}

