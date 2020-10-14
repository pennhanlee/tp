package seedu.bookmark.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.bookmark.model.book.Book;

public class SidebarPanel extends UiPart<Region> {

    private static final String FXML = "SidebarPanel.fxml";

    private final ObservableList<Book> books;

    @FXML
    private ImageView logo;
    @FXML
    private Label numBooks;
    @FXML
    private Label numBookmarks;
    @FXML
    private Label numPagesRead;

    /**
     * Initializes a {@code SidebarPanel} with the given list of books.
     */
    public SidebarPanel(ObservableList<Book> books) {
        super(FXML);
        this.books = books;
        update(books);
    }

    /**
     * Update the sidebar's Label texts.
     */
    public void update(ObservableList<Book> books) {
        int length = books.size();
        int bookmarkCount = (int) books.stream()
                .filter(Book::hasStartedReading)
                .count();
        int pagesReadCount = books.stream()
                .map(Book::getPagesRead)
                .reduce(0, Integer::sum);

        String book = length == 1 ? " book" : " books";
        String bookmark = bookmarkCount == 1 ? " bookmark placed" : " bookmarks placed";
        String pagesRead = pagesReadCount == 1 ? " page read" : " pages read";

        numBooks.setText(length + book);
        numBookmarks.setText(bookmarkCount + bookmark);
        numPagesRead.setText(pagesReadCount + pagesRead);
    }

}
