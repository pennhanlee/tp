package seedu.bookmark.ui.help;

public class HelpAdd implements HelpAction {

    public static final String ADD_INTRO = "You can now ADD a book! \n";
    public static final String ADD_COMMAND = "add n/{Book Name} g/{Genre} tp/{Total Pages} b/{Bookmarked Page} \n";
    public static final String ADD_EXAMPLE = "eg. add n/Harry Potter g/Fiction t/Wizard tp/550 b/20\n";
    public static final String ADD_RESPONSE = "Response: \nNew book added: Harry Potter "
            + "Genre: Fiction Total Pages: 550 Bookmarked at: 20\n";
    public static final String ADD_REMARK = "Note: Adding Tags (t/) and Bookmarks (b/) are optional! \n";

    public HelpAdd() {}

    @Override
    public String helpIntro() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }
}
