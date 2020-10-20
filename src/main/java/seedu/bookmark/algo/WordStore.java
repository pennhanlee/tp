package seedu.bookmark.algo;

public class WordStore {
    private final int STARTING_COUNT = 1;
    private String word;
    private int count;
    private int distance;

    public WordStore(String word) {
        this.word = word;
        this.count = STARTING_COUNT;
    };

    public WordStore(String word, int distance) {
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
