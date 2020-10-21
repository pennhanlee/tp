package seedu.bookmark.algo.word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
