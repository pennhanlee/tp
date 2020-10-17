package seedu.bookmark.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_GENRE = new Prefix("g/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TOTAL_PAGES = new Prefix("tp/");
    public static final Prefix PREFIX_BOOKMARK = new Prefix("b/");
    public static final Prefix PREFIX_NOTE_TEXT = new Prefix("txt/");
    public static final Prefix PREFIX_COMPLETED = new Prefix("c/");
    public static final Prefix PREFIX_NOT_COMPLETED = new Prefix("nc/");
}
