package seedu.bookmark.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.bookmark.logic.commands.GoalDelCommand;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

public class GoalDelCommandParserTest {
    private GoalDelCommandParser parser = new GoalDelCommandParser();

    @Test
    public void parse_validArgs_returnsGoalDelCommand() {
        assertParseSuccess(parser, "1", new GoalDelCommand(INDEX_FIRST_BOOK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalDelCommand.MESSAGE_USAGE));
    }

}
