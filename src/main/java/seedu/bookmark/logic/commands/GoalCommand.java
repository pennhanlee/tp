package seedu.bookmark.logic.commands;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.model.book.Goal;
import seedu.bookmark.model.Model;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_PAGE;

public class GoalCommand extends Command {
    public static final String COMMAND_WORD = "goal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a goal for a specified book. \n"
            + "Parameters: INDEX (positive integer) "
            + PREFIX_PAGE + "PAGE NUMBER "
            + PREFIX_DEADLINE + "DEADLINE (DD-MM-YYYY) \n"
            + "Example: "
            + COMMAND_WORD + " 2 "
            + PREFIX_PAGE + "102 "
            + PREFIX_DEADLINE + "21-12-2024 \n";

    public static final String MESSAGE_ADD_GOAL_SUCCESS = "Added Goal: %1$s";

    private final Index targetIndex;
    private final Goal goal;

    public GoalCommand(Index targetIndex, Goal goal) {
        this.targetIndex = targetIndex;
        this.goal = goal;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from Goal command! Goal is now not operating yet, " +
                "please try again later!");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GoalCommand // instanceof handles nulls
                && targetIndex.equals(((GoalCommand) other).targetIndex) // same index
                && goal.equals(((GoalCommand) other).goal)); // same goal
    }
}
