package seedu.bookmark.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.commands.GoalCommand;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Goal;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.NAME_DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_PAGE;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_SECOND_BOOK;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_THIRD_BOOK;

public class GoalCommandParserTest {
    private static final String PAGE_EMPTY = " " + PREFIX_PAGE;
    private static final String DEADLINE_EMPTY = " " + PREFIX_DEADLINE;

    private static final String VALID_PAGE = " p/10";
    private static final String VALID_DEADLINE = " d/20-12-2040";
    private static final String INVALID_PAGE = " p/10bawj";
    private static final String INVALID_DEADLINE = " d/20/12/20240";
    private static final String VALID_GOAL_COMMAND = " p/10 d/20-12-2040";
    private static final String VALID_GOAL_COMMAND_WITH_INDEX = "2 p/10 d/20-12-2040";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE);

    private GoalCommandParser parser = new GoalCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PAGE + VALID_DEADLINE, MESSAGE_INVALID_FORMAT);

        // no deadline specified
        assertParseFailure(parser, "1" + VALID_PAGE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1" + VALID_DEADLINE, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_GOAL_COMMAND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_GOAL_COMMAND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPage_failure() {
        assertParseFailure(parser, "1" + VALID_PAGE + INVALID_DEADLINE, MESSAGE_INVALID_FORMAT); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_PAGE + VALID_DEADLINE, MESSAGE_INVALID_FORMAT); // invalid page

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PAGE + INVALID_DEADLINE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validGoalCommand_success() {
        Index targetIndex = INDEX_SECOND_BOOK;
        String userInput = targetIndex.getOneBased() + VALID_GOAL_COMMAND;

        GoalCommand expectedCommand = new GoalCommand(targetIndex, new Goal("10 20-12-2040"));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
