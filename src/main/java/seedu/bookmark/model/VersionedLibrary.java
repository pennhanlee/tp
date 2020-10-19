package seedu.bookmark.model;

import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;

public class VersionedLibrary extends Library {

    private HistoryManager historyManager;

    public VersionedLibrary(ReadOnlyLibrary initialState) {
        super(initialState);
        this.historyManager = new HistoryManager(initialState);
    }

    private void save() {
        historyManager = historyManager.addNewState(copy());
    }

    /**
     * Copy the underlying {@code UniqueBookList} to generate a {@code ReadOnlyLibrary} to be saved as state.
     */
    private ReadOnlyLibrary copy() {
        Library copied = new Library();
        copied.setBooks(super.getBookList());
        return copied;
    }

    public void undo() throws UndoException {
        this.historyManager = historyManager.undo();
    }

    public void redo() throws RedoException {
        this.historyManager = historyManager.redo();
    }

    // ============================= Versioned Library Modifiers ============================================= //
    // These methods modify the state of the library and hence need to initiate saving of state.

    @Override
    public void addBook(Book book) {
        super.addBook(book);
        save();
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        super.setBook(target, editedBook);
        save();
    }

    @Override
    public void removeBook(Book key) {
        super.removeBook(key);
        save();
    }

    // ===================================== Util Methods ======================================================//

    @Override
    public ObservableList<Book> getBookList() {
        ReadOnlyLibrary currentState = historyManager.getCurrentState();
        return currentState.getBookList();
    }
}
