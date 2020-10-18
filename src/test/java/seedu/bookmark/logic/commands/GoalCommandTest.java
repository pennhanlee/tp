package seedu.bookmark.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;
import seedu.bookmark.testutil.BookBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

public class GoalCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Goal validGoal = new Goal("10", "15-10-2024");
    private Goal overdueGoal = new Goal("10 15-10-1999");

    @Test
    public void execute_validIndex_success() {
        GoalCommand command = new GoalCommand(INDEX_FIRST_BOOK, validGoal);
        Book bookToSetGoal = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book bookWithGoal = Book.setGoal(bookToSetGoal, validGoal);

        String expectedMessage = String.format(GoalCommand.MESSAGE_ADD_GOAL_SUCCESS, bookToSetGoal.getName(), validGoal.toString());
        expectedModel.setBook(bookToSetGoal, bookWithGoal);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void constructBook_defaultGoal_success() {
        Goal defGoal = Goal.defaultGoal();
        Book newBook = new BookBuilder().build();

        assertTrue(defGoal.equals(newBook.getGoal()));
    }

    @Test
    public void execute_overdueDeadline_throwsCommandException() {
        GoalCommand goalCommand = new GoalCommand(INDEX_FIRST_BOOK, overdueGoal);

        assertCommandFailure(goalCommand, model, String.format(GoalCommand.MESSAGE_DEADLINE_OVERDUE, overdueGoal.deadline));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        GoalCommand goalCommand = new GoalCommand(outOfBoundIndex, validGoal);

        assertCommandFailure(goalCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GoalCommand goalFirstCommand = new GoalCommand(INDEX_FIRST_BOOK, validGoal);
        GoalCommand goalSecondCommand = new GoalCommand(INDEX_SECOND_BOOK, validGoal);

        // same object -> returns true
        assertTrue(goalFirstCommand.equals(goalFirstCommand));

        // same values -> returns true
        GoalCommand goalFirstCommandCopy = new GoalCommand(INDEX_FIRST_BOOK, validGoal);
        assertTrue(goalFirstCommand.equals(goalFirstCommandCopy));

        // different types -> returns false
        assertFalse(goalFirstCommand.equals(1));

        // null -> returns false
        assertFalse(goalFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(goalFirstCommand.equals(goalSecondCommand));
    }

}
