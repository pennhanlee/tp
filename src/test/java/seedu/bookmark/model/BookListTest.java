package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalPersons.ALICE;
import static seedu.bookmark.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.bookmark.model.person.Book;
import seedu.bookmark.model.person.exceptions.DuplicatePersonException;
import seedu.bookmark.testutil.PersonBuilder;

public class BookListTest {

    private final BookList bookList = new BookList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bookList.getBookList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        BookList newData = getTypicalAddressBook();
        bookList.resetData(newData);
        assertEquals(newData, bookList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Book> newBooks = Arrays.asList(ALICE, editedAlice);
        BookListStub newData = new BookListStub(newBooks);

        assertThrows(DuplicatePersonException.class, () -> bookList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(bookList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        bookList.addPerson(ALICE);
        assertTrue(bookList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        bookList.addPerson(ALICE);
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(bookList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bookList.getBookList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class BookListStub implements ReadOnlyBookList {
        private final ObservableList<Book> books = FXCollections.observableArrayList();

        BookListStub(Collection<Book> books) {
            this.books.setAll(books);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }
    }

}
