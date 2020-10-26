package seedu.bookmark.ui.help;

public class HelpList implements HelpAction {

    public static final String LIST_INTRO = "You can now LIST all recorded books! \n";
    public static final String LIST_COMMAND = "list \n";
    public static final String LIST_EXAMPLE = "eg. list \n";
    public static final String LIST_RESPONSE = "Response: \nListing all books";

    public HelpList() {}

    @Override
    public String helpIntro() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }
}
