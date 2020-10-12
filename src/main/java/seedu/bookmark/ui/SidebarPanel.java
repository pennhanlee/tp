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

    public SidebarPanel(ObservableList<Book> books) {
        super(FXML);
        this.books = books;
    }

}
