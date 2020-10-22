package seedu.bookmark.model.wordstore;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;
import seedu.bookmark.testutil.TypicalWords;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.HARRY;

public class TagWordStoreTest {

    TagWordStore tagWordStore = new TagWordStore();

    //------------------- contains() ------------------------------//

    @Test
    public void contains_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagWordStore.contains(null));
    }

    @Test
    public void contains_wordNotInList_returnsFalse() {
        assertFalse(tagWordStore.contains(HARRY));
    }

    @Test
    public void contains_wordInList_returnsTrue() {
        tagWordStore.wordAdder(HARRY);
        String harry = CORRECT_HARRY.getWord();
        assertTrue(tagWordStore.contains(harry));
    }

    //--------------------- wordAdder() ---------------------------//

    @Test
    public void add_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagWordStore.wordAdder(null));
    }

    @Test
    public void add_newWord_countOne() {
        tagWordStore.wordAdder(HARRY);
        Word harry = tagWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    @Test
    public void add_existingWord_increaseCount() {
        tagWordStore.wordAdder(HARRY);
        tagWordStore.wordAdder(HARRY);
        Word harry = tagWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(2, harry.getCount());

    }

    //-------------------- wordDeleter() -------------------------//

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagWordStore.wordDeleter(null));
    }

    @Disabled
    @Test
    public void delete_bookDoesNotExist_throwsWordNotFoundException() {
        assertThrows(WordNotFoundException.class, () -> tagWordStore.wordDeleter(HARRY));
    }

    @Test
    public void delete_existingWord_countZero() {
        tagWordStore.wordAdder(HARRY);
        tagWordStore.wordDeleter(HARRY);
        assertFalse(tagWordStore.contains(HARRY));
    }
    @Test
    public void delete_existingBook_countNonZero() {
        tagWordStore.wordAdder(HARRY);
        tagWordStore.wordAdder(HARRY);  //increase count to 2
        tagWordStore.wordDeleter(HARRY);
        Word harry = tagWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals(HARRY)).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    //--------------------- addWords() ---------------------------//

    @Test
    public void addWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        tagWordStore.addWords(listOfWords);
        for(String word : listOfWords) {
            assertTrue(tagWordStore.contains(word));
        }
    }

    @Test
    public void add_existingWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        tagWordStore.addWords(listOfWords);
        tagWordStore.addWords(listOfWords);
        for(String word : listOfWords) {
            assertTrue(tagWordStore.contains(word));
            Word targetWord = tagWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(2, targetWord.getCount());
        }
    }

    //---------------------- deleteWords() -----------------------//

    @Test
    public void deleteWords_countOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        tagWordStore.addWords(listOfWords);
        tagWordStore.deleteWords(listOfWords);
        for(String word : listOfWords) {
            assertFalse(tagWordStore.contains(word));
        }
    }

    @Test
    public void deleteWords_countBiggerThanOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        tagWordStore.addWords(listOfWords);
        tagWordStore.addWords(listOfWords);
        tagWordStore.deleteWords(listOfWords);
        for(String word : listOfWords) {
            assertTrue(tagWordStore.contains(word));
            Word targetWord = tagWordStore.getWordStore().stream()
                    .filter(n -> n.getWord().equals(word)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
    }

    //---------------------- getWordStore() ------------------------//

    @Test
    public void getWordStore() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        List<Word> listOfWordObjs = TypicalWords.getTypicalWords();
        tagWordStore.addWords(listOfWords);
        List<Word> tagWordStoreList = tagWordStore.getWordStore();
        assertEquals(listOfWordObjs, tagWordStoreList);
    }
    
}

