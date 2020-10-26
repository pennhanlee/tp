package seedu.bookmark.ui.help;

public class HelpAdd implements HelpAction {

    private static final String ADD_INTRO = "ADD A BOOK!";
    private static final String ADD_EXPLANATION = "Add books to the storage to keep track of your "
                                                + "reading progress using our Add Command! "
                                                + "Tags and Bookmarked Page are optional but do add them if you fancy~";
    private static final String ADD_COMMAND = "COMMAND: add n/BOOK_TITLE g/GENRE t/TAG t/TAG tp/TOTAL_PAGES "
                                                + "b/PAGE_NUMBER";
    private static final String ADD_EXAMPLE = "EXAMPLE: add n/Harry Potter g/Fiction t/Magic /t/Spells"
                                                + " t/Wizard tp/550 b/20";
    private static final String ADD_RESPONSE = "RESPONSE: New book added: Harry Potter "
                                                + "Genre: Fiction Total Pages: 550 "
                                                + "Bookmarked at: 20 Tags: [Magic, Spells]";
    private static final String LB = " \n";

    public HelpAdd() {}

    @Override
    public String helpIntro() {
        return ADD_INTRO;
    }

    @Override
    public String helpMessage() {
        return ADD_EXPLANATION + LB + ADD_COMMAND + LB + ADD_EXAMPLE + LB + ADD_RESPONSE;
    }
}
