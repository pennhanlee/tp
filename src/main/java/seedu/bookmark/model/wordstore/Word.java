package seedu.bookmark.model.wordstore;

public class Word {
    private int STARTING_COUNT = 1;
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
        return this.word;
    }

    /**
     * Returns the count of the word
     * @return count
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Increases the count by 1
     */
    public void addCount() {
        this.count++;
    }

    /**
     * Decrease the count by 1
     */
    public void minusCount() { this.count--; }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) { this.distance = distance; }

    @Override
    public String toString() {
        return this.word;
    }
}
