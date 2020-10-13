package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.FindCommand;
import seedu.bookmark.model.book.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.BookCompletedPredicate;
import seedu.bookmark.model.book.BookNotCompletedPredicate;
import seedu.bookmark.model.book.TagContainsKeywordsPredicate;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + " " + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "           " + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validGenreArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new GenreContainsKeywordsPredicate(Arrays.asList("Fantasy", "Horror")));
        assertParseSuccess(parser, " " + PREFIX_GENRE + " " + "Fantasy Horror", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_GENRE + "          "+ " \n Fantasy \n \t Horror  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("Happy", "Sad")));
        assertParseSuccess(parser, " " + PREFIX_TAG + " " + "Happy Sad", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + "             " + " \n Happy \n \t Sad  \t", expectedFindCommand);
    }

//    @Test
//    public void parse_validBookCompletedArgs_returnsFindCommand() {
//        // no leading and trailing whitespaces
//        FindCommand expectedFindCommand =
//                new FindCommand(new BookCompletedPredicate());
//        assertParseSuccess(parser, " " + PREFIX_COMPLETED, expectedFindCommand);
//    }
//
//    @Test
//    public void parse_validBookNotCompletedArgs_returnsFindCommand() {
//        // no leading and trailing whitespaces
//        FindCommand expectedFindCommand =
//                new FindCommand(new BookNotCompletedPredicate());
//        assertParseSuccess(parser, " " + PREFIX_NOT_COMPLETED, expectedFindCommand);
//    }
}
