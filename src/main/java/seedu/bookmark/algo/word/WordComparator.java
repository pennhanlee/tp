package seedu.bookmark.algo.word;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word firstWord, Word secondWord) {
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
