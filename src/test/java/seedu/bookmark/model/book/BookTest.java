package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.JANE_EYRE;

import org.junit.jupiter.api.Test;

import seedu.bookmark.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getTags().remove(0));
    }

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(HARRY_POTTER.isSameBook(HARRY_POTTER));

        // null -> returns false
        assertFalse(HARRY_POTTER.isSameBook(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book aliceCopy = new BookBuilder(HARRY_POTTER).build();
        assertTrue(HARRY_POTTER.equals(aliceCopy));

        // same object -> returns true
        assertTrue(HARRY_POTTER.equals(HARRY_POTTER));

        // null -> returns false
        assertFalse(HARRY_POTTER.equals(null));

        // different type -> returns false
        assertFalse(HARRY_POTTER.equals(5));

        // different person -> returns false
        assertFalse(HARRY_POTTER.equals(JANE_EYRE));

        // different name -> returns false
        Book editedAlice = new BookBuilder(HARRY_POTTER).withName(VALID_NAME_JANE_EYRE).build();
        assertFalse(HARRY_POTTER.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new BookBuilder(HARRY_POTTER).withTags(VALID_TAG_BAD).build();
        assertFalse(HARRY_POTTER.equals(editedAlice));
    }
}
