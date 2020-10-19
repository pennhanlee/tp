package seedu.bookmark.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.testutil.TypicalBooks;

public class HistoryManagerTest {

    public static final Book TO_ADD = TypicalBooks.HAMLET;

    @Test
    public void equalsTest() {
        List<Book> booksA = TypicalBooks.getTypicalBooks();
        List<Book> booksB = TypicalBooks.getTypicalBooks();
        booksB.add(TO_ADD);

        HistoryManager historyA = new HistoryManager(new LibraryStub(booksA));
        HistoryManager historyB = new HistoryManager(new LibraryStub(booksA));
        HistoryManager historyC = new HistoryManager(new LibraryStub(booksB));

        // same object
        assertEquals(historyA, historyA);

        // same current state, same undo and redo deque
        assertEquals(historyA, historyB);

        // different current state
        assertNotEquals(historyA, historyC);

        // same current state, different undo deque
        historyA = historyA.addNewState(new LibraryStub(booksB));
        assertNotEquals(historyA, historyC);

        // same current state, different redo deque
        historyA = historyA.undo();
        assertNotEquals(historyA, historyB);
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
