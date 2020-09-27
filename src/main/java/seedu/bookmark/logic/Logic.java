package seedu.bookmark.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.logic.commands.CommandResult;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.bookmark.model.Model#getAddressBook()
     */
    ReadOnlyLibrary getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Book> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
