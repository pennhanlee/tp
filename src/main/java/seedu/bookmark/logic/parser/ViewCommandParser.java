package seedu.bookmark.logic.parser;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.DeleteCommand;
import seedu.bookmark.logic.commands.ViewCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;

import javax.swing.text.View;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewCommandParser implements Parser<ViewCommand> {
    public ViewCommand parse(String input) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(input);
            return new ViewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }
    }
}
