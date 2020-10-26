package seedu.bookmark.ui.help;

public class HelpGoalDelete implements HelpAction {

    private static final String GOAL_DELETE_INTRO = "DELETE A GOAL FROM YOUR BOOK!";
    private static final String GOAL_DELETE_MESSAGE = "Feeling overwhelmed? It's okay! Delete your goal by "
                                                + "using our Goal Command!";
    private static final String GOAL_DELETE_COMMAND = "COMMAND: goal INDEX remove";
    private static final String GOAL_DELETE_EXAMPLE = "EXAMPLE: goal 1 remove";
    private static final String GOAL_DELETE_RESPONSE = "Goal removed for Harry Potter";
    private static final String LB = " \n";

    public HelpGoalDelete() {}

    @Override
    public String helpIntro() {
        return GOAL_DELETE_INTRO;
    }

    @Override
    public String helpMessage() {
        return GOAL_DELETE_MESSAGE;
    }

    @Override
    public String helpExample() {
        return GOAL_DELETE_COMMAND + GOAL_DELETE_EXAMPLE + GOAL_DELETE_RESPONSE;
    }
}
