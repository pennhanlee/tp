package seedu.bookmark.model;

import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;

/**
 * Represents a {@code Library} that stores different states of itself and can move between these states.
 */
public class VersionedLibrary extends Library {

    private HistoryManager historyManager;

    /**
     * Constructs a {@code VersionedLibrary} with the {@code ReadOnlyLibrary} as the initial state.
     */
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

    /**
     * Undo the most recent change.
     * @throws UndoException if there is no changes to undo
     */
    public void undo() throws UndoException {
        this.historyManager = historyManager.undo();
        resetData(historyManager.getCurrentState());
    }

    /**
     * Redo the most recent undone change.
     * @throws RedoException if there is no undone changes to redo
     */
    public void redo() throws RedoException {
        this.historyManager = historyManager.redo();
        resetData(historyManager.getCurrentState());
    }

    // ================================ Versioned Library Modifiers ============================================= //
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
}
