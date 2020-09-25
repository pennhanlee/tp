package seedu.bookmark.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.bookmark.model.person.Genre;
import seedu.bookmark.model.person.Name;
import seedu.bookmark.model.person.Book;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_GENRE = "Fiction";

    private Name name;
    private Genre genre;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        genre = new Genre(DEFAULT_GENRE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Book bookToCopy) {
        name = bookToCopy.getName();
        genre = bookToCopy.getGenre();
        tags = new HashSet<>(bookToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Genre} of the {@code Person} that we are building.
     */
    public PersonBuilder withGenre(String genre) {
        this.genre = new Genre(genre);
        return this;
    }

    public Book build() {
        return new Book(name, genre, tags);
    }

}
