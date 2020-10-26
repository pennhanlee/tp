package seedu.bookmark.ui.help;

public class HelpDelete implements HelpAction {

    public static final String DELETE_INTRO = "You can now DELETE a book! \n";
    public static final String DELETE_COMMAND = "delete {Index} \n";
    public static final String DELETE_EXAMPLE = "eg. delete 3 \n";
    public static final String DELETE_RESPONSE = "Response: \nDeleted Book: Tokyo Ghoul Genre: Anime Total Pages: 1000 "
            + "Bookmarked at: 3 Tags: [Japanese]";

    @Override
    public String helpIntro() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }
}
