package seedu.bookmark.model.wordstore;

import seedu.bookmark.model.book.Book;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Word {
    private int STARTING_COUNT = 1;
    private String word;
    private int count;
    private int distance;

    public Word(String word) {
        requireNonNull(word);
        this.word = word;
        this.count = STARTING_COUNT;
        this.distance = 0;
    };

    public Word(String word, int distance) {
        requireNonNull(word);
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

    /**
     * Returns true if both words have the same {@code String word}
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameWord(Word otherWord) {
        if (otherWord == this) {
            return true;
        }

        return otherWord != null
                && otherWord.getWord().equals(this.getWord());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Word)) {
            return false;
        }

        Word otherWord = (Word) other;
        return otherWord.getWord().equals(this.getWord())
                && otherWord.getCount() == this.getCount()
                && otherWord.getDistance() == this.getDistance();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(word, count, distance);
    }

    @Override
    public String toString() {
        return this.word;
    }
}
