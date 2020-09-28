package seedu.bookmark.logic.commands;

import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Book validBook = new PersonBuilder().withName("TESTER BOOK").build();

        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.addBook(validBook);

        assertCommandSuccess(new AddCommand(validBook), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validBook), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Book bookInList = model.getLibrary().getBookList().get(0);
        assertCommandFailure(new AddCommand(bookInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
