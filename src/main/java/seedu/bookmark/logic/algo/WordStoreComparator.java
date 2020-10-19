package seedu.bookmark.logic.algo;

import java.util.Comparator;

public class WordStoreComparator implements Comparator<WordStore> {

    @Override
    public int compare(WordStore firstWord, WordStore secondWord) {
        int firstWordDistance = firstWord.getCount();
        int secondWordDistance = secondWord.getCount();
        return Integer.compare(firstWordDistance, secondWordDistance);
    }
}
