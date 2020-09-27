package seedu.bookmark.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalPersons.ALICE;
import static seedu.bookmark.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.person.exceptions.DuplicatePersonException;
import seedu.bookmark.model.person.exceptions.PersonNotFoundException;
import seedu.bookmark.testutil.PersonBuilder;

public class UniqueLibraryTest {

    private final UniqueBookList uniqueBookList = new UniqueBookList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueBookList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueBookList.add(ALICE);
        assertTrue(uniqueBookList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookList.add(ALICE);
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueBookList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueBookList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBookList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBookList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueBookList.add(ALICE);
        uniqueBookList.setPerson(ALICE, ALICE);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(ALICE);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueBookList.add(ALICE);
        Book editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueBookList.setPerson(ALICE, editedAlice);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(editedAlice);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueBookList.add(ALICE);
        uniqueBookList.setPerson(ALICE, BOB);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOB);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBookList.add(ALICE);
        uniqueBookList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueBookList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBookList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueBookList.add(ALICE);
        uniqueBookList.remove(ALICE);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setPersons((UniqueBookList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueBookList.add(ALICE);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOB);
        uniqueBookList.setPersons(expectedUniqueBookList);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookList.setPersons((List<Book>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueBookList.add(ALICE);
        List<Book> bookList = Collections.singletonList(BOB);
        uniqueBookList.setPersons(bookList);
        UniqueBookList expectedUniqueBookList = new UniqueBookList();
        expectedUniqueBookList.add(BOB);
        assertEquals(expectedUniqueBookList, uniqueBookList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Book> listWithDuplicateBooks = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueBookList.setPersons(listWithDuplicateBooks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookList.asUnmodifiableObservableList().remove(0));
    }
}
