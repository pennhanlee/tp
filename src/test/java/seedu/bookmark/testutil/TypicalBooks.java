package seedu.bookmark.testutil;

import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_BAD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {

    public static final Book HARRY_POTTER = new BookBuilder().withName("Harry Potter")
            .withGenre("Fiction")
            .withTags("Good").build();
    public static final Book TO_KILL_A_MOCKINGBIRD = new BookBuilder().withName("To Kill a Mockingbird")
            .withGenre("Fiction")
            .withTags("Good", "Lengthy").build();
    public static final Book THE_HUNGER_GAMES = new BookBuilder().withName("The Hunger Games")
            .withGenre("Fiction").build();
    public static final Book CRIME_AND_PUNISHMENT = new BookBuilder().withName("Crime and Punishment")
            .withGenre("Crime").withTags("Bad").build();
    public static final Book LORD_OF_THE_FLIES = new BookBuilder().withName("Lord of the Flies")
            .withGenre("Mystery").build();
    public static final Book ENDERS_GAME = new BookBuilder().withName("Enders Game")
            .withGenre("Thriller").build();
    public static final Book ON_THE_ROAD = new BookBuilder().withName("On the Road")
            .withGenre("Travel").build();

    // Manually added
    public static final Book HAMLET = new BookBuilder().withName("Hamlet")
            .withGenre("Classic").build();
    public static final Book THE_FAULT_IN_OUR_STARS = new BookBuilder().withName("The Fault in Our Stars")
            .withGenre("Tragic").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Book NINETEEN_EIGHTY_FOUR = new BookBuilder().withName(VALID_NAME_1984)
            .withGenre(VALID_GENRE_1984).withTags(VALID_TAG_BAD).build();
    public static final Book JANE_EYRE = new BookBuilder().withName(VALID_NAME_JANE_EYRE)
            .withGenre(VALID_GENRE_JANE_EYRE).withTags(VALID_TAG_GOOD, VALID_TAG_BAD)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code Library} with all the typical books.
     */
    public static Library getTypicalLibrary() {
        Library ab = new Library();
        for (Book book : getTypicalBooks()) {
            ab.addBook(book);
        }
        return ab;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(HARRY_POTTER, TO_KILL_A_MOCKINGBIRD, THE_HUNGER_GAMES,
                CRIME_AND_PUNISHMENT, LORD_OF_THE_FLIES, ENDERS_GAME, ON_THE_ROAD));
    }
}
