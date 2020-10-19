package seedu.bookmark.logic.commands;

import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.exceptions.RedoException;

public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the most recent undone command. \n"
            + "Usage: redo";

    public static final String MESSAGE_SUCCESS = "Previous undone command successfully redone";
    public static final String MESSAGE_NO_UNDONE_CHANGES = "No commands available to redo";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.redo();
        } catch (RedoException e) {
            throw new CommandException(MESSAGE_NO_UNDONE_CHANGES);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
