package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.IsSpecifiedBookPredicate;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        ViewCommand command = new ViewCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, INDEX_FIRST_BOOK.getOneBased());

        Book bookToView = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        IsSpecifiedBookPredicate predicate = new IsSpecifiedBookPredicate(bookToView);
        expectedModel.updateFilteredBookList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand deleteFirstCommand = new ViewCommand(INDEX_FIRST_BOOK);
        ViewCommand deleteSecondCommand = new ViewCommand(INDEX_SECOND_BOOK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ViewCommand deleteFirstCommandCopy = new ViewCommand(INDEX_FIRST_BOOK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
