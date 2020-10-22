package seedu.bookmark.model.book;

import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    private final List<Note> notes = new ArrayList<>();

    /**
     * Fields must be present, valid and not null.
     */
    public Book(Name name, Genre genre, Set<Tag> tags, TotalPages totalPages, Bookmark bookmark) {
        requireAllNonNull(name, genre, tags, totalPages, bookmark);
        this.name = name;
        this.genre = genre;
        this.tags.addAll(tags);
        this.totalPages = totalPages;
        this.bookmark = bookmark;
    }

    /**
     * Overloaded constructor, to accommodate notes without having to refactor every command.
     * Will update to this constructor once notes is up and fully functional.
     */
    public Book(Name name, Genre genre, Set<Tag> tags,
                TotalPages totalPages, Bookmark bookmark, List<Note> notes) {
        requireAllNonNull(name, genre, tags, totalPages, bookmark, notes);
        this.name = name;
        this.genre = genre;
        this.tags.addAll(tags);
        this.totalPages = totalPages;
        this.bookmark = bookmark;
        this.notes.addAll(notes);
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

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
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
     * Checks if this {@code Book} has started being read based on its {@code Bookmark}'s value.
     */
    public boolean hasStartedReading() {
        return getPagesRead() > 0;
    }

    /**
     * Returns the number of pages of this book read, according to its {@code Bookmark}'s value.
     */
    public int getPagesRead() {
        return Integer.parseInt(bookmark.value);
    }

    /**
     * Returns the total number of pages in this book, according to its {@code TotalPages}'s value.
     */
    public int getTotalPagesNumber() {
        return Integer.parseInt(totalPages.value);
    }

    /**
     * Returns true if book is completed, i.e. if bookmarked page is equal to total pages.
     */
    public boolean isCompleted() {
        return bookmark.value.equals(totalPages.value);
    }

    /**
     * Returns true if the exact same note is present in the note list of the book.
     */
    public boolean containsNote(Note note) {
        for (Note n : this.notes) {
            if (n.equals(note)) {
                return true;
            }
        }
        return false;
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
                && otherBook.getBookmark().equals(getBookmark())
                && otherBook.getNotes().equals(getNotes());
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
        builder.append("Notes: ");
        getNotes().forEach(note -> builder.append(note.title + ","));
        return builder.toString();
    }
}
