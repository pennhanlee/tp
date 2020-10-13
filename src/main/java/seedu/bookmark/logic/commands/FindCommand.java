package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import seedu.bookmark.commons.core.Messages;

import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;

/**
 * Finds and lists all books in bookmark whose specified property contains any of the argument keywords.
 * Currently, supports finding in name, genre properties, as well as finding completed and non completed books.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate<Book> predicate;

    public FindCommand(Predicate<Book> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
