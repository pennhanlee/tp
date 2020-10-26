package seedu.bookmark.ui.help;

public class HelpEdit implements HelpAction {

    public static final String EDIT_INTRO = "You can now EDIT a book's contents! \n";
    public static final String EDIT_COMMAND = "edit {Index} *b/{Bookmarked Page} \n";
    public static final String EDIT_EXAMPLE = "eg. edit 3 b/360 \n";
    public static final String EDIT_RESPONSE = "Response: \nEdited Book: Haikyuu Genre: Manga Total Pages: 500"
            + " Bookmarked at: 360";

    @Override
    public String helpIntro() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }
}
