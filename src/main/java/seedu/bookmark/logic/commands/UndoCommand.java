package seedu.bookmark.logic.commands;

import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.exceptions.UndoException;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the most recent command. \n"
            + "Only commands which modified, added or deleted the Books stored in the Library are considered \n"
            + "Usage: undo";

    public static final String MESSAGE_SUCCESS = "Previous command successfully undone";
    public static final String MESSAGE_MOST_RECENT_CHANGE = "No commands available to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.undo();
        } catch (UndoException e) {
            throw new CommandException(MESSAGE_MOST_RECENT_CHANGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
