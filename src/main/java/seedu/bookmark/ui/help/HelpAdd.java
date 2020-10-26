package seedu.bookmark.ui.help;

public class HelpAdd implements HelpAction {

    private static final String ADD_INTRO = "ADD A BOOK!";
    private static final String ADD_COMMAND = "COMMAND: add n/{Book Name} g/{Genre} tp/{Total Pages} b/{Bookmarked Page}";
    private static final String ADD_EXAMPLE = "EXAMPLE: add n/Harry Potter g/Fiction t/Wizard tp/550 b/20";
    private static final String ADD_RESPONSE = "RESPONSE: New book added: Harry Potter "
            + "Genre: Fiction Total Pages: 550 Bookmarked at: 20";
    private static final String ADD_REMARK = "Note: Adding Tags (t/) and Bookmarks (b/) are optional!";
    private static final String LB = " \n";

    public HelpAdd() {}

    @Override
    public String helpIntro() {
        return ADD_INTRO;
    }

    @Override
    public String helpMessage() {
        String message = ADD_COMMAND + LB + ADD_EXAMPLE + LB + ADD_RESPONSE + LB + ADD_REMARK;
        return message;
    }
}
