package seedu.bookmark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.bookmark.model.book.Book;

/**
 * An UI component that displays more detailed information of a {@code Book}.
 */
public class DetailedBookCard extends BookCard {

    private static final String FXML = "DetailedBookCard.fxml";

    @FXML
    private Label percentageCompletion;

    /**
     * Creates a {@code DetailedBookCard} with the given {@code Book} and index to display.
     */
    public DetailedBookCard(Book book, int displayedIndex) {
        super(book, FXML);
        initialize(book, displayedIndex);
    }

    @Override
    protected void initialize(Book book, int displayedIndex) {
        super.initialize(book, displayedIndex);
        int percentageCompleted = calculateCompletion(book);
        String progressDisplay = "Progress: " + String.valueOf(percentageCompleted) + "%";
        percentageCompletion.setText(progressDisplay);
    }

    private int calculateCompletion(Book book) {
        int pagesRead = book.getPagesRead();
        int totalPages = book.getTotalPagesNumber();
        return (int) (((float) pagesRead / totalPages) * 100);
    }
}
