package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.BookBuilder;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
//@Disabled("EditCommand is not refactored to work with TotalPages and Bookmark yet")
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Book editedBook = new BookBuilder().build();
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(editedBook).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredBookList().size());
        Book lastBook = model.getFilteredBookList().get(indexLastPerson.getZeroBased());

        BookBuilder personInList = new BookBuilder(lastBook);
        Book editedBook = personInList.withName(VALID_NAME_JANE_EYRE)
                .withTags(VALID_TAG_GOOD).build();

        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE)
                .withTags(VALID_TAG_GOOD).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(lastBook, editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditBookDescriptor());
        Book editedBook = model.getFilteredBookList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_PERSON);

        Book bookInFilteredList = model.getFilteredBookList().get(INDEX_FIRST_PERSON.getZeroBased());
        Book editedBook = new BookBuilder(bookInFilteredList).withName(VALID_NAME_JANE_EYRE).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_BOOK_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(model.getFilteredBookList().get(0), editedBook);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @org.junit.jupiter.api.Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        seedu.bookmark.model.book.Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_PERSON.getZeroBased());
        seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor descriptor =
                new seedu.bookmark.testutil.EditBookDescriptorBuilder(firstBook).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, seedu.bookmark.logic.commands.EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Book bookInList = model.getLibrary().getBookList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new EditBookDescriptorBuilder(bookInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_BOOK);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        EditBookDescriptor descriptor = new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_1984);

        // same values -> returns true
        EditBookDescriptor copyDescriptor = new EditBookDescriptor(DESC_1984);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_1984)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_JANE_EYRE)));
    }

}
