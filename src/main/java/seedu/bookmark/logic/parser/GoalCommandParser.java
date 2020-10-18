package seedu.bookmark.logic.parser;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.GoalCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Goal;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_PAGE;

public class GoalCommandParser implements Parser<GoalCommand> {

    @Override
    public GoalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PAGE, PREFIX_DEADLINE);

        Index index;

        // Check index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE), pe);
        }

        // Check page and date
        if (!(argMultimap.getValue(PREFIX_PAGE).isPresent() && argMultimap.getValue(PREFIX_DEADLINE).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE));
        // Check if page and deadline provided matches page and deadline regex.
        } else if (!argMultimap.getValue(PREFIX_DEADLINE).get().matches(Goal.DEADLINE_REGEX) ||
                    !argMultimap.getValue(PREFIX_PAGE).get().matches(Goal.PAGE_REGEX)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE));
        }

        // Both Page and Date should be present
        String page = argMultimap.getValue(PREFIX_PAGE).get();
        String deadline = argMultimap.getValue(PREFIX_DEADLINE).get();

        return new GoalCommand(index, new Goal(page, deadline));
    }
}
