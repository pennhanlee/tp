package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TEXT;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.AddNoteCommand;
import seedu.bookmark.logic.commands.EditCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Note;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_NOTE_TEXT);

        Index index;
        Note note = null;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()
                    && argMultimap.getValue(PREFIX_NOTE_TEXT).isPresent()) {
            note = ParserUtil.parseNote(
                    argMultimap.getValue(PREFIX_NAME).get(), argMultimap.getValue(PREFIX_NOTE_TEXT).get());
        }

        return new AddNoteCommand(index, note);
    }

}
