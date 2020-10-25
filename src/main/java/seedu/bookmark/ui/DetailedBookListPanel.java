package seedu.bookmark.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Note;

/**
 * Panel that shows detailed information about the books.
 */
public class DetailedBookListPanel extends BookListPanel {

    private static final String FXML = "DetailedBookListPanel.fxml";

    /**
     * Creates a {@code DetailedBookListPanel} with the given {@code ObservableList}.
     */
    public DetailedBookListPanel(ObservableList<Book> bookList) {
        super(FXML);
        bookListView.setItems(bookList);
        bookListView.setCellFactory(lv -> new DetailedBookListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code DetailedBookCard}.
     */
    class DetailedBookListViewCell extends ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);

            if (empty || book == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedBookCard(book, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the notes of a {@code Book} using a {@code NoteCard}.
     */
    class noteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }
}
