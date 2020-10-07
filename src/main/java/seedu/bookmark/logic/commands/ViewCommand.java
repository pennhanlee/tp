package seedu.bookmark.logic.commands;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.model.Model.PREDICATE_SHOW_ALL_BOOKS;

/**
 * Allows user to view a all details of a specific book
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View all details of the book identified by index number in book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_NOT_IMPLEMENTED = "Hello from View Command! I'm still under development, please wait :)";

    public final Index index;

    public ViewCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(MESSAGE_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ViewCommand) {
            return index.equals(((ViewCommand) other).index);
        } else {
            return false;
        }
    }
}
