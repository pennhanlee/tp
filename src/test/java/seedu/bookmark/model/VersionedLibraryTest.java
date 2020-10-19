package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.model.HistoryManagerTest.LibraryStub;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalBooks.HAMLET;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalBooks;


import org.junit.jupiter.api.Test;

import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests of VersionedLibrary with {@code HistoryManager}
 */
public class VersionedLibraryTest {

    private static final List<Book> INITIAL_BOOKS = getTypicalBooks();
    private static final LibraryStub INITIAL_STATE = new LibraryStub(INITIAL_BOOKS);
    private static final Book TO_ADD = HAMLET;
    private static final Book TO_REMOVE = HARRY_POTTER;

    @Test
    public void addBook_validBook_stateChanged() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        library.addBook(TO_ADD);

        List<Book> newBooks = getTypicalBooks();
        newBooks.add(TO_ADD);
        LibraryStub newState = new LibraryStub(newBooks);

        assertEquals(newState.getBookList(), library.getBookList());
    }

    @Test
    public void removeBook_validBook_stateChanged() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        library.removeBook(TO_REMOVE);

        List<Book> newBooks = getTypicalBooks();
        newBooks.remove(TO_REMOVE);
        LibraryStub newState = new LibraryStub(newBooks);

        assertEquals(newState.getBookList(), library.getBookList());
    }

    @Test
    public void setBook_validBook_stateChanged() {
        Book lastBook = INITIAL_BOOKS.get(INITIAL_BOOKS.size() - 1);
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        library.setBook(lastBook, HAMLET);

        List<Book> newBooks = new ArrayList<>(INITIAL_BOOKS);
        newBooks.set(newBooks.size() - 1, HAMLET);
        LibraryStub newState = new LibraryStub(newBooks);

        assertEquals(newState.getBookList(), library.getBookList());
    }

    @Test
    public void undo_nothingToUndo_throwsUndoException() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        assertThrows(UndoException.class, library::undo);
    }

    @Test
    public void redo_nothingToRedo_throwsRedoException() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        assertThrows(RedoException.class, library::redo);
    }

    @Test
    public void undo_validUndo_success() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        library.addBook(TO_ADD);
        library.undo();

        assertEquals(INITIAL_STATE.getBookList(), library.getBookList());
    }

    @Test
    public void undoThenRedo_validUndoRedo_success() {
        VersionedLibrary library = new VersionedLibrary(INITIAL_STATE);
        library.addBook(TO_ADD);
        library.undo();
        library.redo();

        List<Book> newBooks = new ArrayList<>(INITIAL_BOOKS);
        newBooks.add(TO_ADD);
        LibraryStub newState = new LibraryStub(newBooks);

        assertEquals(newState.getBookList(), library.getBookList());
    }
}
