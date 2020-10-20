package seedu.bookmark.algo.wordbank;

import java.util.Comparator;

public class WordBankComparator implements Comparator<WordBank> {

    @Override
    public int compare(WordBank firstWord, WordBank secondWord) {
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
