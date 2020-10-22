package seedu.bookmark.model.wordstore;

import org.junit.jupiter.api.Test;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.model.wordstore.WordComparator;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordComparatorTest {
    public static final PriorityQueue<Word> differentDistancePQ = new PriorityQueue<>(new WordComparator());
    public static final PriorityQueue<Word> differentWordsPQ = new PriorityQueue<>(new WordComparator());
    public static final Word APPLE = new Word("Apple");
    public static final Word BANANA = new Word("Banana");
    public static final Word CHERRY = new Word("Cherry");
    public static final Word DURIAN = new Word("Durian");
    public static final Word ELDERBERRY = new Word("Elderberry");
    public static final Word FOO = new Word("Foo", 1);
    public static final Word BOO = new Word("Boo", 2);
    public static final Word POO = new Word("Poo", 3);

    @Test
    public void CompareWord_ByDistance() {
        differentDistancePQ.add(FOO);
        differentDistancePQ.add(BOO);
        differentDistancePQ.add(POO);

        Word word = differentDistancePQ.poll();
        assertEquals(word, FOO);
        word = differentDistancePQ.poll();
        assertEquals(word, BOO);
        word = differentDistancePQ.poll();
        assertEquals(word, POO);
    }

    @Test
    public void CompareWord_ByWord() {
        differentWordsPQ.add(CHERRY);
        differentWordsPQ.add(DURIAN);
        differentWordsPQ.add(APPLE);
        differentWordsPQ.add(BANANA);
        differentWordsPQ.add(ELDERBERRY);

        Word word = differentWordsPQ.poll();
        assertEquals(word, APPLE);
        word = differentWordsPQ.poll();
        assertEquals(word, BANANA);
        word = differentWordsPQ.poll();
        assertEquals(word, CHERRY);
        word = differentWordsPQ.poll();
        assertEquals(word, DURIAN);
        word = differentWordsPQ.poll();
        assertEquals(word, ELDERBERRY);
    }

}
