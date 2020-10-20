package seedu.bookmark.algo.word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {

    public static final Word VALID_WORD_DISTANCESET = new Word("Word", 3);
    public static final Word VALID_WORD_NODISTANCESET = new Word("Word");

    @Test
    public void getFieldValues_validFields_correctValues_DistanceSet() {
        final String word = "Word";
        final int count = 1;
        final int distance = 3;

        assertEquals(VALID_WORD_DISTANCESET.getWord(), word);
        assertEquals(VALID_WORD_DISTANCESET.getCount(), count);
        assertEquals(VALID_WORD_DISTANCESET.getDistance(), distance);
    }

    @Test
    public void getFieldValues_validFields_correctValues_NoDistanceSet() {
        final String word = "Word";
        final int count = 1;
        final int distance = 0;

        assertEquals(VALID_WORD_NODISTANCESET.getWord(), word);
        assertEquals(VALID_WORD_NODISTANCESET.getCount(), count);
        assertEquals(VALID_WORD_NODISTANCESET.getDistance(), distance);
    }

    @Test
    public void addCount() {
        Word word = VALID_WORD_DISTANCESET;
        int counter = 1;
        word.addCount();
        assertEquals(word.getCount(), counter++);
        assertEquals(word.getCount(), counter++);
        assertEquals(word.getCount(), counter++);
        assertEquals(word.getCount(), counter);
    }

    @Test
    public void minusCount() {
        Word word = VALID_WORD_DISTANCESET;
        int counter = 1;
        word.minusCount();
        assertEquals(word.getCount(), counter--);
        assertEquals(word.getCount(), counter--);
        assertEquals(word.getCount(), counter--);
        assertEquals(word.getCount(), counter);
    }

    @Test
    public void setDistance() {
        Word word = VALID_WORD_NODISTANCESET;
        int distance = 3;
        word.setDistance(3);
        assertEquals(word.getDistance(), distance);
    }
}
