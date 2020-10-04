package seedu.bookmark.testutil;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.book.Book;

/**
 * A utility class to help with building Library objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class LibraryBuilder {

    private Library library;

    public LibraryBuilder() {
        library = new Library();
    }

    public LibraryBuilder(Library library) {
        this.library = library;
    }

    /**
     * Adds a new {@code Book} to the {@code Library} that we are building.
     */
    public LibraryBuilder withBook(Book book) {
        library.addBook(book);
        return this;
    }

    public Library build() {
        return library;
    }
}
