package seedu.bookmark.algo.wordbank;

public class WordBank {
    private final int STARTING_COUNT = 1;
    private String word;
    private int count;
    private int distance;

    public WordBank(String word) {
        this.word = word;
        this.count = STARTING_COUNT;
    };

    public WordBank(String word, int distance) {
        this.word = word;
        this.count = STARTING_COUNT;
        this.distance = distance;
    }

    /**
     * Returns the word
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the count of the word
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Increases the count by 1
     * @return count
     */
    public int addCount() {
        count++;
        return count;
    }

    /**
     * Decrease the count by 1
     * @return count
     */
    public int minusCount() {
        count--;
        return count;
    }

    public int getDistance() {
        return this.distance;
    }
}
