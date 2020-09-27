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

public class LibraryTest {

    private final Library library = new Library();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), library.getBookList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Library newData = getTypicalAddressBook();
        library.resetData(newData);
        assertEquals(newData, library);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Book> newBooks = Arrays.asList(ALICE, editedAlice);
        LibraryStub newData = new LibraryStub(newBooks);

        assertThrows(DuplicatePersonException.class, () -> library.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> library.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(library.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        library.addPerson(ALICE);
        assertTrue(library.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        library.addPerson(ALICE);
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(library.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> library.getBookList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class LibraryStub implements ReadOnlyLibrary {
        private final ObservableList<Book> books = FXCollections.observableArrayList();

        LibraryStub(Collection<Book> books) {
            this.books.setAll(books);
        }

        @Override
        public ObservableList<Book> getBookList() {
            return books;
        }
    }

}
