package seedu.bookmark.model.wordstore;

import org.junit.jupiter.api.Test;
import seedu.bookmark.model.wordstore.Word;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_1984;
import static seedu.bookmark.testutil.TypicalBooks.FULL_JANE_EYRE;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_AND;
import static seedu.bookmark.testutil.TypicalWords.CORRECT_HARRY;
import static seedu.bookmark.testutil.TypicalWords.DISTANCE_HARRY;
import static seedu.bookmark.testutil.TypicalWords.INCORRECT_HARRY;

public class WordTest {

    @Test
    public void getFieldValues_validFields_correctValues_DistanceSet() {
        Word validWordWithDistance = new Word("Word", 4);
        final String word = "Word";
        final int count = 1;
        final int distance = 4;

        assertEquals(validWordWithDistance.getWord(), word);
        assertEquals(validWordWithDistance.getCount(), count);
        assertEquals(validWordWithDistance.getDistance(), distance);
    }

    @Test
    public void getFieldValues_validFields_correctValues_NoDistanceSet() {
        Word validWord = new Word("Word");
        final String word = "Word";
        final int count = 1;
        final int distance = 0;
        assertEquals(validWord.getWord(), word);
        assertEquals(validWord.getCount(), count);
        assertEquals(validWord.getDistance(), distance);
    }

    @Test
    public void addCount() {
        Word validWord = new Word("Word");
        int counter = 1;
        counter++;
        validWord.addCount();
        assertEquals(validWord.getCount(), counter);
    }

    @Test
    public void minusCount() {
        Word validWord = new Word("Word");
        int counter = 1;
        counter--;
        validWord.minusCount();
        assertEquals(validWord.getCount(), counter);
    }

    @Test
    public void setDistance() {
        Word validWord = new Word("Word");
        int distance = 3;
        validWord.setDistance(3);
        assertEquals(validWord.getDistance(), distance);
    }

    @Test
    public void isSameWord() {
        // same object -> returns true
        assertTrue(CORRECT_HARRY.isSameWord(CORRECT_HARRY));

        // different word -> returns false
        assertFalse(CORRECT_HARRY.isSameWord(CORRECT_AND));

        // same word with typo -> return false
        assertFalse(CORRECT_HARRY.isSameWord(INCORRECT_HARRY));

        // same word with different distance -> returns true
        assertTrue(CORRECT_HARRY.isSameWord(DISTANCE_HARRY));

        // same word with different count -> returns false
        String harry = CORRECT_HARRY.getWord();
        Word countChangedHarry = new Word(harry);
        countChangedHarry.addCount();
        assertTrue(CORRECT_HARRY.isSameWord(countChangedHarry));

        // null -> returns false
        assertFalse(HARRY_POTTER.isSameBook(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(CORRECT_HARRY.equals(CORRECT_HARRY));

        // different word -> returns false
        assertFalse(CORRECT_HARRY.equals(CORRECT_AND));

        // same word with typo -> return false
        assertFalse(CORRECT_HARRY.equals(INCORRECT_HARRY));

        // different distance -> returns false
        assertFalse(CORRECT_HARRY.equals(DISTANCE_HARRY));

        // different count -> returns false
        String harry = CORRECT_HARRY.getWord();
        Word countChangedHarry = new Word(harry);
        countChangedHarry.addCount();
        assertFalse(CORRECT_HARRY.equals(countChangedHarry));

        // different object -> returns false
        assertFalse(CORRECT_HARRY.equals("harry"));

        // null -> returns false
        assertFalse(HARRY_POTTER.equals(null));
    }
}
