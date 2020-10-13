package seedu.bookmark.model.book;

import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.bookmark.model.tag.Tag;

/**
 * Represents a Book in the book list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {

    // Identity fields
    private final Name name;
    private final Genre genre;
    private final TotalPages totalPages;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Bookmark bookmark;

    /**
     * Every field must be present and not null.
     */
    public Book(Name name, Genre genre, Set<Tag> tags) {
        requireAllNonNull(name, genre, tags);
        this.name = name;
        this.genre = genre;
        this.tags.addAll(tags);
        this.totalPages = new TotalPages("500");
        this.bookmark = new Bookmark();
    }

    /**
     * Overloaded constructor, to accommodate totalPages and bookmark, remove the other constructor when integration
     * is completed.
     */
    public Book(Name name, Genre genre, Set<Tag> tags, TotalPages totalPages, Bookmark bookmark) {
        requireAllNonNull(name, genre, tags, totalPages, bookmark);
        this.name = name;
        this.genre = genre;
        this.tags.addAll(tags);
        this.totalPages = totalPages;
        this.bookmark = bookmark;
    }

    public Name getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public TotalPages getTotalPages() {
        return totalPages;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    /**
     * Returns true if both books of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getName().equals(getName())
                && otherBook.getGenre().equals(getGenre())
                && otherBook.getTotalPages().equals(getTotalPages());
    }

    /**
     * Returns true if book is completed, i.e. if bookmarked page is equal to total pages.
     */
    public boolean isCompleted() {
        return bookmark.value.equals(totalPages.value);
    }

    /**
     * Returns true if both books have the same identity and data fields.
     * This defines a stronger notion of equality between two books.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getName().equals(getName())
                && otherBook.getGenre().equals(getGenre())
                && otherBook.getTags().equals(getTags())
                && otherBook.getTotalPages().equals(getTotalPages())
                && otherBook.getBookmark().equals(getBookmark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, genre, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        String bookmarkPage = Optional.ofNullable(bookmark)
                .map(Bookmark::toString)
                .orElse("No bookmark for this book");

        builder.append(getName())
                .append(" Genre: ")
                .append(getGenre())
                .append(" Total Pages: ")
                .append(getTotalPages())
                .append(" Bookmarked at: ")
                .append(bookmarkPage)
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
