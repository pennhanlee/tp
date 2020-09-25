package seedu.bookmark.testutil;

import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_AMY;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bookmark.model.BookList;
import seedu.bookmark.model.person.Book;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Book ALICE = new PersonBuilder().withName("Alice Pauline")
            .withGenre("Fiction")
            .withTags("friends").build();
    public static final Book BENSON = new PersonBuilder().withName("Benson Meier")
            .withGenre("Horror")
            .withTags("owesMoney", "friends").build();
    public static final Book CARL = new PersonBuilder().withName("Carl Kurz")
            .withGenre("Non fiction").build();
    public static final Book DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withGenre("Crime").withTags("friends").build();
    public static final Book ELLE = new PersonBuilder().withName("Elle Meyer")
            .withGenre("Mystery").build();
    public static final Book FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withGenre("Thriller").build();
    public static final Book GEORGE = new PersonBuilder().withName("George Best")
            .withGenre("Travel").build();

    // Manually added
    public static final Book HOON = new PersonBuilder().withName("Hoon Meier")
            .withGenre("Survival").build();
    public static final Book IDA = new PersonBuilder().withName("Ida Mueller")
            .withGenre("Horror").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Book AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withGenre(VALID_GENRE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Book BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withGenre(VALID_GENRE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static BookList getTypicalAddressBook() {
        BookList ab = new BookList();
        for (Book book : getTypicalPersons()) {
            ab.addPerson(book);
        }
        return ab;
    }

    public static List<Book> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
