package seedu.bookmark.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSamplePersons() {
        return new Book[] {
            new Book(new Name("Alex Yeoh"), new Genre("alexyeoh@example.com"),
                getTagSet("friends")),
            new Book(new Name("Bernice Yu"), new Genre("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Book(new Name("Charlotte Oliveiro"), new Genre("charlotte@example.com"),
                getTagSet("neighbours")),
            new Book(new Name("David Li"), new Genre("lidavid@example.com"),
                getTagSet("family")),
            new Book(new Name("Irfan Ibrahim"), new Genre("irfan@example.com"),
                getTagSet("classmates")),
            new Book(new Name("Roy Balakrishnan"), new Genre("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyLibrary getSampleAddressBook() {
        Library sampleAb = new Library();
        for (Book sampleBook : getSamplePersons()) {
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

}
