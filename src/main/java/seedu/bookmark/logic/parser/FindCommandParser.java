package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.BookCompletedPredicate;
import seedu.bookmark.model.book.BookNotCompletedPredicate;

import java.util.Arrays;

import seedu.bookmark.logic.commands.FindCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    public int prefixCount = 0;
    public Prefix inputPrefix;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_GENRE, PREFIX_COMPLETED, PREFIX_NOT_COMPLETED);

        //checking if user had input more than one prefix. put the input prefix in inputPrefix.
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_NAME;
        }
        if (argMultimap.getValue(PREFIX_GENRE).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_GENRE;
        }
        if (argMultimap.getValue(PREFIX_COMPLETED).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_COMPLETED;
        }
        if (argMultimap.getValue(PREFIX_NOT_COMPLETED).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_NOT_COMPLETED;
        }
        if (prefixCount != 1) { //if more than 1 input prefix, we throw an error.
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        prefixCount = 0; //set count back to 0

        String trimmedArgs = argMultimap.getValue(inputPrefix).get().trim();

        String[] keywords = trimmedArgs.split("\\s+");

        Predicate<Book> predicate = null;

        if (inputPrefix == PREFIX_NAME) {
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (inputPrefix == PREFIX_GENRE) {
            predicate = new GenreContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (inputPrefix == PREFIX_COMPLETED) {
            predicate = new BookCompletedPredicate();
        } else if (inputPrefix == PREFIX_NOT_COMPLETED) {
            predicate = new BookNotCompletedPredicate();
        }

        return new FindCommand(predicate);
    }
}
