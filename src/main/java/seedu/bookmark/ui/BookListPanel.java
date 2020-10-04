package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.model.book.Book;

/**
 * Panel containing the list of books.
 */
public class BookListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(BookListPanel.class);

    @FXML
    private ListView<Book> personListView;

    /**
     * Creates a {@code BookListPanel} with the given {@code ObservableList}.
     */
    public BookListPanel(ObservableList<Book> bookList) {
        super(FXML);
        personListView.setItems(bookList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code BookCard}.
     */
    class PersonListViewCell extends ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);

            if (empty || book == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BookCard(book, getIndex() + 1).getRoot());
            }
        }
    }

}
