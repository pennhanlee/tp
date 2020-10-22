package seedu.bookmark.testutil;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.WordBank;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.wordstore.Word;

public class TypicalWords {

    //correctly spelt
    public static final Word CORRECT_HARRY = new Word("harry");
    public static final Word CORRECT_POTTER = new Word("potter");
    public static final Word CORRECT_AND = new Word("and");
    public static final Word CORRECT_CHAMBER = new Word("chamber");
    public static final Word CORRECT_SECRETS = new Word("secrets");

    //incorrectly spelt
    public static final Word INCORRECT_HARRY = new Word("hbrry");
    public static final Word INCORRECT_POTTER = new Word("p0tter");
    public static final Word INCORRECT_AND = new Word("ans");
    public static final Word INCORRECT_CHAMBER = new Word("chnmber");
    public static final Word INCORRECT_SECRETS = new Word("sekrets");

    //distance set
    public static final Word DISTANCE_HARRY = new Word("harry", 1);
    public static final Word DISTANCE_POTTER = new Word("potter", 1);
    public static final Word DISTANCE_AND = new Word("and", 1);
    public static final Word DISTANCE_CHAMBER = new Word("chamber", 1);
    public static final Word DISTANCE_SECRETS = new Word("secrets", 1);

    private TypicalWords() {} //prevents instantiation

    /**
     * Returns an {@code Library} with all the typical books converted into words.
     */
    public static WordBank getTypicalWordBank() {
        Library ab = new Library();
        for (Book book : TypicalBooks.getTypicalBooks()) {
            ab.addBook(book);
        }
        return new WordBank(ab);
    }




}
