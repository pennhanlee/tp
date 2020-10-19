package seedu.bookmark.logic.algo;

import java.util.Comparator;

public class WordStoreComparator implements Comparator<WordStore> {

    @Override
    public int compare(WordStore firstWord, WordStore secondWord) {
        int firstWordDistance = firstWord.getDistance();
        int secondWordDistance = secondWord.getDistance();
        if (firstWordDistance < secondWordDistance) {
            return -1;
        } else if (firstWordDistance > secondWordDistance) {
            return 1;
        } else {
            return  0;
        }
    }
}
