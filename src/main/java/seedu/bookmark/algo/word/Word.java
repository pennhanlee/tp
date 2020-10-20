package seedu.bookmark.algo.word;

public class Word {
    private final int STARTING_COUNT = 1;
    private String word;
    private int count;
    private int distance;

    public Word(String word) {
        this.word = word;
        this.count = STARTING_COUNT;
        this.distance = 0;
    };

    public Word(String word, int distance) {
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
     */
    public void addCount() {
        count++;
    }

    /**
     * Decrease the count by 1
     */
    public void minusCount() {
        count--;
    }

    public int getDistance() {
        return this.distance;
    }
}
