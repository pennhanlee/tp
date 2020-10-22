package seedu.bookmark.model.wordstore;

import org.junit.jupiter.api.Test;
import seedu.bookmark.model.wordstore.exceptions.WordNotFoundException;
import seedu.bookmark.testutil.TypicalWords;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.getTypicalStrings;
import static seedu.bookmark.testutil.TypicalWords.getTypicalWords;

public class NameWordStoreTest {

    NameWordStore nameWordStore = new NameWordStore();

    //------------------- contains() ------------------------------//

    @Test
    public void contains_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nameWordStore.contains(null));
    }

    @Test
    public void contains_wordNotInList_returnsFalse() {
        assertFalse(nameWordStore.contains("Harry"));
    }

    @Test
    public void contains_wordInList_returnsTrue() {
        nameWordStore.wordAdder("Harry");
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
        nameWordStore.wordAdder("Harry");
        Word harry = nameWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals("Harry")).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    @Test
    public void add_existingWord_increaseCount() {
        nameWordStore.wordAdder("Harry");
        nameWordStore.wordAdder("Harry");
        Word harry = nameWordStore.getWordStore().stream()
                .filter(n -> n.getWord().equals("Harry")).findFirst().get();
        assertEquals(2, harry.getCount());

    }

    //-------------------- wordDeleter() -------------------------//

    @Test
    public void delete_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nameWordStore.wordDeleter(null));
    }

    @Test
    public void delete_bookDoesNotExist_throwsWordNotFoundException() {
        assertThrows(WordNotFoundException.class, () -> nameWordStore.wordDeleter("Harry"));
    }

    @Test
    public void delete_existingWord_countZero() {
        nameWordStore.wordAdder("Harry");
        nameWordStore.wordDeleter("Harry");
        assertFalse(nameWordStore.contains("Harry"));
    }
    @Test
    public void delete_existingBook_countNonZero() {
        nameWordStore.wordAdder("Harry");
        nameWordStore.wordAdder("Harry");  //increase count to 2
        nameWordStore.wordDeleter("Harry");
        Word harry = nameWordStore.getWordStore().stream()
                            .filter(n -> n.getWord().equals("Harry")).findFirst().get();
        assertEquals(1, harry.getCount());
    }

    //--------------------- addWords() ---------------------------//

    @Test
    public void addWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        for(String word : listOfWords) {
            assertTrue(nameWordStore.contains(word));
        }
    }

    @Test
    public void add_existingWords() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        nameWordStore.addWords(listOfWords);
        for(String word : listOfWords) {
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
        for(String word : listOfWords) {
            assertFalse(nameWordStore.contains(word));
        }
    }

    @Test
    public void deleteWords_countBiggerThanOne() {
        List<String> listOfWords = TypicalWords.getTypicalStrings();
        nameWordStore.addWords(listOfWords);
        nameWordStore.addWords(listOfWords);
        nameWordStore.deleteWords(listOfWords);
        for(String word : listOfWords) {
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

