package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's genre.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenre(String)}
 */
public class Genre {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the genre must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    public final String value;

    /**
     * Constructs an {@code Genre}.
     *
     * @param genre A valid genre string.
     */
    public Genre(String genre) {
        requireNonNull(genre);
        checkArgument(isValidGenre(genre), MESSAGE_CONSTRAINTS);
        value = genre;
    }

    /**
     * Returns if a given string is a valid genre.
     */
    public static boolean isValidGenre(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && value.equals(((Genre) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
