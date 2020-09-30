package seedu.bookmark.model.book;

import static seedu.bookmark.commons.util.AppUtil.checkArgument;
import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Book's bookmark.
 * Guarantees: immutable; is valid as declared in {@link #isValidBookmark(String, TotalPages)}
 */
public class Bookmark {

    public static final String MESSAGE_CONSTRAINTS =
            "bookmark should only contain numeric characters, it should not be blank, and its numerical value should"
                    + "be less than or equals to the total number of pages in the book it is in";

    /*
     * Only a 1 or more of the digits 0-9 allowed
     */
    public static final String VALIDATION_REGEX = "^\\d+";

    public final String value;

    /**
     * Constructs an {@code Bookmark}.
     *
     * @param bookmarkedPage A valid page string.
     * @param totalPages total pages in the book this bookmark is in.
     */
    public Bookmark(String bookmarkedPage, TotalPages totalPages) {
        requireAllNonNull(bookmarkedPage, totalPages);
        checkArgument(isValidBookmark(bookmarkedPage, totalPages), MESSAGE_CONSTRAINTS);
        this.value = bookmarkedPage;
    }

    /**
     * Returns if a given string and {@code TotalPages} forms a valid bookmark.
     *
     * @param bookmarkedPage String representing the bookmark page
     * @param totalPages the total number of pages in the Book which the bookmark will be placed in
     * @return true iff bookmarkedPage String is valid and bookmark page is less than equals to total pages
     */
    public static boolean isValidBookmark(String bookmarkedPage, TotalPages totalPages) {
        if (!bookmarkedPage.matches(VALIDATION_REGEX)) {
            return false;
        }

        int bookmarkInt = Integer.parseInt(bookmarkedPage);
        int totalPagesInt = Integer.parseInt(totalPages.value);
        return bookmarkInt <= totalPagesInt;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bookmark // instanceof handles nulls
                && value.equals(((Bookmark) other).value)); // state check
    }
}
