package seedu.bookmark.ui.help;

public class HelpNoteDelete implements HelpAction {

    private static final String NOTE_DELETE_INTRO = "DELETE A NOTE FROM YOUR BOOK!";
    private static final String NOTE_DELETE_MESSAGE = "Having Second thoughts? Delete past Notes "
                                                    + "by using our Note Command!";
    private static final String NOTE_DELETE_COMMAND = "COMMAND: note INDEX remove [n/NOTE_TITLE]";
    private static final String NOTE_DELETE_EXAMPLE = "EXAMPLE: note 1 remove n/Chapter 1";
    private static final String NOTE_DELETE_RESPONSE = "Note: Chapter 1 removed for Harry Potter";
    private static final String LB = " \n";

    public HelpNoteDelete() {}

    @Override
    public String helpIntro() {
        return NOTE_DELETE_INTRO;
    }

    @Override
    public String helpMessage() {
        return NOTE_DELETE_MESSAGE + LB + NOTE_DELETE_COMMAND + LB + NOTE_DELETE_EXAMPLE + LB + NOTE_DELETE_RESPONSE;
    }
}
