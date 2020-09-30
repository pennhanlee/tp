package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

/**
 * Represents the total number of pages in a Book.
 * Guarantees: immutable;
 */
public class TotalPages {

    public static final String MESSAGE_CONSTRAINTS =
            "Total pages should only contain numeric characters, and it should not be blank";

    /*
     * Only a 1 or more of the digits 0-9 allowed
     */
    public static final String VALIDATION_REGEX = "^\\d+";

    public final String value;

    /**
     * Constructs an {@code TotalPages}.
     *
     * @param totalPages A valid total pages string.
     */
    public TotalPages(String totalPages) {
        requireNonNull(totalPages);
        checkArgument(isValidGenre(totalPages), MESSAGE_CONSTRAINTS);
        value = totalPages;
    }

    /**
     * Returns if a given string is a valid total pages.
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
                || (other instanceof TotalPages // instanceof handles nulls
                && value.equals(((TotalPages) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
