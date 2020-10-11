package seedu.bookmark.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.bookmark.model.book.Book;

/**
 * Panel that shows detailed information about the books.
 */
public class DetailedBookListPanel extends BookListPanel {

    /**
     * Creates a {@code DetailedBookListPanel} with the given {@code ObservableList}.
     */
    public DetailedBookListPanel(ObservableList<Book> bookList) {
        super();
        bookListView.setItems(bookList);
        bookListView.setCellFactory(lv -> new BookListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code BookCard}.
     */
    class BookListViewCell extends ListCell<Book> {
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
}
