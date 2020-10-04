package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Hybrid tests for Bookmark, depends on {@code TotalPages}.
 */
public class BookmarkTest {

    private static final TotalPages VALID_TOTAL_PAGES = new TotalPages("1234");

    @Test
    public void constructor_default_pageZeroBookmark() {
        Bookmark bookmark = new Bookmark();
        assertEquals(bookmark.value, "0");
    }

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        // both null
        assertThrows(NullPointerException.class, () -> new Bookmark(null, null));
        // bookmarked page null
        assertThrows(NullPointerException.class, () -> new Bookmark(null, new TotalPages("20")));
        // total pages null
        assertThrows(NullPointerException.class, () -> new Bookmark("123", null));
    }

    @Test
    public void constructor_invalidBookmarkPage_throwsIllegalArgumentException() {
        String invalidBookmarkPage = "-1";
        assertThrows(IllegalArgumentException.class, () -> new Bookmark(invalidBookmarkPage, VALID_TOTAL_PAGES));
    }

    @Test
    public void isValidBookmark() {
        // null bookmark page
        assertThrows(NullPointerException.class, () -> Bookmark.isValidBookmark(null, VALID_TOTAL_PAGES));

        // blank bookmark page
        assertFalse(Bookmark.isValidBookmark("", VALID_TOTAL_PAGES)); // empty string
        assertFalse(Bookmark.isValidBookmark(" ", VALID_TOTAL_PAGES)); // spaces only

        // invalid bookmark page
        assertFalse(Bookmark.isValidBookmark("@@@", VALID_TOTAL_PAGES));

        // bookmarked page > total pages
        assertFalse(Bookmark.isValidBookmark("12345", VALID_TOTAL_PAGES));

        // valid bookmarks
        assertTrue(Bookmark.isValidBookmark("123", VALID_TOTAL_PAGES));
        assertTrue(Bookmark.isValidBookmark("0", VALID_TOTAL_PAGES));
        assertTrue(Bookmark.isValidBookmark("000001", VALID_TOTAL_PAGES)); // leading zeroes
    }
}
