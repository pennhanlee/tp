package seedu.bookmark.ui.help;

public class HelpView implements HelpAction {

    public static final String VIEW_INTRO = "You can now VIEW a book's contents! \n";
    public static final String VIEW_COMMAND = "view {Index} \n";
    public static final String VIEW_EXAMPLE = "eg. view 3 \n";
    public static final String VIEW_RESPONSE = "Response: \nViewing 3";

    @Override
    public String helpIntro() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }
}
