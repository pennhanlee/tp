package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;

/**
 * Adds a Book into a Library.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to the Library. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_GENRE + "GENRE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Harry Potter "
            + PREFIX_GENRE + "Fiction "
            + PREFIX_TAG + "Magic "
            + PREFIX_TAG + "Witches";

    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the library";

    private final Book toAdd;

    /**
     * Creates an AddCommand to add the specified {@code book}
     */
    public AddCommand(Book book) {
        requireNonNull(book);
        toAdd = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBook(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        model.addBook(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
