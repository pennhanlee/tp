package seedu.bookmark.testutil;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.book.Book;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Library library;

    public AddressBookBuilder() {
        library = new Library();
    }

    public AddressBookBuilder(Library library) {
        this.library = library;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Book book) {
        library.addBook(book);
        return this;
    }

    public Library build() {
        return library;
    }
}
