package seedu.bookmark.testutil;

import seedu.bookmark.model.BookList;
import seedu.bookmark.model.person.Book;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BookList bookList;

    public AddressBookBuilder() {
        bookList = new BookList();
    }

    public AddressBookBuilder(BookList bookList) {
        this.bookList = bookList;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Book book) {
        bookList.addPerson(book);
        return this;
    }

    public BookList build() {
        return bookList;
    }
}
