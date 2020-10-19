package seedu.bookmark.model;

import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;

public class VersionedLibrary extends Library {

    private HistoryManager historyManager;

    public VersionedLibrary(ReadOnlyLibrary initialState) {
        super(initialState);
        this.historyManager = new HistoryManager(initialState);
    }

    private void save() {
        ReadOnlyLibrary copiedLibrary = new Library(this);
        historyManager = historyManager.addNewState(copiedLibrary);
    }

    public void undo() {
        historyManager.undo();
    }

    public void redo() {
        historyManager.redo();
    }

    // ============================= Versioned Library Modifiers ========================================= //
    // These methods modify the state of the library and hence need to save the current state before modifying

    @Override
    public void addBook(Book book) {
        save();
        super.addBook(book);
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        save();
        super.setBook(target, editedBook);
    }

    @Override
    public void removeBook(Book key) {
        save();
        super.removeBook(key);
    }

    // ===================================== Util Methods =======================================================//
    @Override
    public ObservableList<Book> getBookList() {
        ReadOnlyLibrary currentState = historyManager.getCurrentState();
        return currentState.getBookList();
    }
}
