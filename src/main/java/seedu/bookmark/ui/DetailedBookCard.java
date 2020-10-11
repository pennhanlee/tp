package seedu.bookmark.ui;

import seedu.bookmark.model.book.Book;

/**
 * An UI component that displays more detailed information of a {@code Book}.
 */
public class DetailedBookCard extends BookCard {

    private static final String FXML = "DetailedBookCard.fxml";

    public DetailedBookCard(Book book, int displayedIndex) {
        super(book, FXML);
        initialize(book, displayedIndex);
    }

    @Override
    protected void initialize(Book book, int displayedIndex) {
        super.initialize(book, displayedIndex);
    }
}
