package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;
import seedu.bookmark.testutil.TypicalBooks;

public class HistoryManagerTest {

    public static final List<Book> BOOKS = TypicalBooks.getTypicalBooks();
    public static final Book TO_ADD = TypicalBooks.HAMLET;

    @Test
    public void equalsTest() {
        List<Book> editedBooks = new ArrayList<>(BOOKS);
        editedBooks.add(TO_ADD);

        HistoryManager historyA = new HistoryManager(new LibraryStub(BOOKS));
        HistoryManager historyB = new HistoryManager(new LibraryStub(BOOKS));
        HistoryManager historyC = new HistoryManager(new LibraryStub(editedBooks));

        // same object
        assertEquals(historyA, historyA);

        // same current state, same undo and redo deque
        assertEquals(historyA, historyB);

        // different current state
        assertNotEquals(historyA, historyC);

        // same current state, different undo deque
        historyA = historyA.addNewState(new LibraryStub(editedBooks));
        assertNotEquals(historyA, historyC);

        // same current state, different redo deque
        historyA = historyA.undo();
        assertNotEquals(historyA, historyB);
    }

    @Test
    public void addThenUndo_validActions_success() {
        List<Book> editedBooks = new ArrayList<>(BOOKS);
        editedBooks.add(TO_ADD);

        HistoryManager historyA = new HistoryManager(new LibraryStub(BOOKS));
        historyA = historyA.addNewState(new LibraryStub(editedBooks));
        historyA = historyA.undo();

        assertEquals(historyA.getCurrentState(), new LibraryStub(BOOKS));
    }

    @Test
    public void addThenUndoThenRedo_validActions_success() {
        List<Book> editedBooks = new ArrayList<>(BOOKS);
        editedBooks.add(TO_ADD);

        HistoryManager historyA = new HistoryManager(new LibraryStub(BOOKS));
        historyA = historyA.addNewState(new LibraryStub(editedBooks));
        historyA = historyA.undo();
        historyA = historyA.redo();

        assertEquals(historyA.getCurrentState(), new LibraryStub(editedBooks));
    }

    @Test
    public void undo_noUndoAvailable_throwsException() {
        HistoryManager historyA = new HistoryManager(new LibraryStub(BOOKS));
        assertThrows(UndoException.class, historyA::undo);
    }

    @Test
    public void redo_noRedoAvailable_throwsException() {
        HistoryManager historyA = new HistoryManager(new LibraryStub(BOOKS));
        assertThrows(RedoException.class, historyA::redo);
    }

}




class LibraryStub implements ReadOnlyLibrary {

    private final List<Book> books;

    public LibraryStub(List<Book> books) {
        this.books = books;
    }

    @Override
    public ObservableList<Book> getBookList() {
        return FXCollections.observableArrayList(books);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        else if (other instanceof LibraryStub) {
            return getBookList().equals(((LibraryStub) other).getBookList());
        }
        return false;
    }
}
