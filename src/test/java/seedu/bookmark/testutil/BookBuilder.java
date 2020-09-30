package seedu.bookmark.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.model.util.SampleDataUtil;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_GENRE = "Fiction";

    private Name name;
    private Genre genre;
    private Set<Tag> tags;

    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        name = new Name(DEFAULT_NAME);
        genre = new Genre(DEFAULT_GENRE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the BookBuilder with the data of {@code bookToCopy}.
     */
    public BookBuilder(Book bookToCopy) {
        name = bookToCopy.getName();
        genre = bookToCopy.getGenre();
        tags = new HashSet<>(bookToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Book} that we are building.
     */
    public BookBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Book} that we are building.
     */
    public BookBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Book} that we are building.
     */
    public BookBuilder withGenre(String genre) {
        this.genre = new Genre(genre);
        return this;
    }

    public Book build() {
        return new Book(name, genre, tags);
    }

}
