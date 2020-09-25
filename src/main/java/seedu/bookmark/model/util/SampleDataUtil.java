package seedu.bookmark.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.bookmark.model.BookList;
import seedu.bookmark.model.ReadOnlyBookList;
import seedu.bookmark.model.person.Book;
import seedu.bookmark.model.person.Email;
import seedu.bookmark.model.person.Name;
import seedu.bookmark.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSamplePersons() {
        return new Book[] {
            new Book(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                getTagSet("friends")),
            new Book(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Book(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                getTagSet("neighbours")),
            new Book(new Name("David Li"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Book(new Name("Irfan Ibrahim"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Book(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyBookList getSampleAddressBook() {
        BookList sampleAb = new BookList();
        for (Book sampleBook : getSamplePersons()) {
            sampleAb.addPerson(sampleBook);
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
