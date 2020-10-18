package seedu.bookmark.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSampleBooks() {
        return new Book[] {
            new Book(new Name("Harry Potter"), new Genre("Fiction"),
                getTagSet("Good")),
            new Book(new Name("To Kill a Mockingbird"), new Genre("Fiction"),
                getTagSet("Good", "Lengthy")),
            new Book(new Name("The Hunger Games"), new Genre("Fiction"),
                getTagSet("Exciting")),
            new Book(new Name("Crime And Punishment"), new Genre("Crime"),
                getTagSet("Action")),
            new Book(new Name("Lord Of The Flies"), new Genre("Mystery"),
                getTagSet("Horror")),
            new Book(new Name("Enders Game"), new Genre("Thriller"),
                getTagSet("Interesting"))
        };
    }

    public static ReadOnlyLibrary getSampleLibrary() {
        Library sampleAb = new Library();
        for (Book sampleBook : getSampleBooks()) {
            sampleAb.addBook(sampleBook);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a list containing notes with the same title and text provided by the list of strings given.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                                     .map(string -> new Note(string, string))
                                     .collect(Collectors.toList());
    }

}
