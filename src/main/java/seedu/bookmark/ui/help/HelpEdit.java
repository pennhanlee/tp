package seedu.bookmark.ui.help;

public class HelpEdit implements HelpAction {

    private static final String EDIT_INTRO = "EDIT A BOOK!";
    private static final String EDIT_MESSAGE = "Oh man! Made a mistake when adding the book? Fret Not! Simply edit your"
                                                + "book with our Edit Command!";
    private static final String EDIT_COMMAND = "COMMAND: edit INDEX [b/BOOKMARKED_PAGE]";
    private static final String EDIT_EXAMPLE = "EXAMPLE: edit 3 b/360";
    private static final String EDIT_RESPONSE = "RESPONSE: Edited Book: Haikyuu Genre: Manga Total Pages: 500"
            + " Bookmarked at: 360";
    private static final String LB = " \n";

    public HelpEdit() {}

    @Override
    public String helpIntro() {
        return EDIT_INTRO;
    }

    @Override
    public String helpMessage() {
        String message = EDIT_COMMAND + LB + EDIT_EXAMPLE + EDIT_RESPONSE;
        return message;
    }
}
