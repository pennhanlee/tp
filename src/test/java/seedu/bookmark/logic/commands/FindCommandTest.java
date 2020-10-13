package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.commons.core.Messages.MESSAGE_BOOKS_LISTED_OVERVIEW;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.testutil.TypicalBooks.ENDERS_GAME;
import static seedu.bookmark.testutil.TypicalBooks.LORD_OF_THE_FLIES;
import static seedu.bookmark.testutil.TypicalBooks.THE_FAULT_IN_OUR_STARS;
import static seedu.bookmark.testutil.TypicalBooks.THE_HUNGER_GAMES;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.TagContainsKeywordsPredicate;
import seedu.bookmark.model.book.BookCompletedPredicate;
import seedu.bookmark.model.book.BookNotCompletedPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void NamePredicateEquals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void GenrePredicateEquals() {
        GenreContainsKeywordsPredicate firstPredicate =
                new GenreContainsKeywordsPredicate(Collections.singletonList("first"));
        GenreContainsKeywordsPredicate secondPredicate =
                new GenreContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void TagPredicateEquals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nameZeroKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_nameMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("hunger flies enders");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THE_HUNGER_GAMES, LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    @Test
    public void execute_genreZeroKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        GenreContainsKeywordsPredicate predicate = prepareGenrePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_genreMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        GenreContainsKeywordsPredicate predicate = prepareGenrePredicate("thriller mystery");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    @Test
    public void execute_tagZeroKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_tagMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("exciting perfect");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    @Test
    public void execute_findCompletedBooks() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        BookCompletedPredicate predicate = prepareCompletedBooksPredicate();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LORD_OF_THE_FLIES, THE_HUNGER_GAMES), model.getFilteredBookList());
    }

    @Test
    public void execute_findNotCompletedBooks() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        BookNotCompletedPredicate predicate = prepareNotCompletedBooksPredicate();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code GenreContainsKeywordsPredicate}.
     */
    private GenreContainsKeywordsPredicate prepareGenrePredicate(String userInput) {
        return new GenreContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code GenreContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code BookCompletedPredicate}.
     */
    private BookCompletedPredicate prepareCompletedBooksPredicate() {
        return new BookCompletedPredicate();
    }

    /**
     * Parses {@code userInput} into a {@code BookNotCompletedPredicate}.
     */
    private BookNotCompletedPredicate prepareNotCompletedBooksPredicate() {
        return new BookNotCompletedPredicate();
    }
}
