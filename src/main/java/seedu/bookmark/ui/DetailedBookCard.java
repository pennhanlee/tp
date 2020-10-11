package seedu.bookmark.ui;

import java.util.Comparator;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import seedu.bookmark.MainApp;
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
        id.setText(displayedIndex + ". ");
        name.setText(book.getName().fullName);
        genre.setText(book.getGenre().value);
        totalPages.setText(book.getTotalPages().value + " pages");
        if (!book.getBookmark().value.equals("0")) {
            bookmark.setText("Bookmarked @ page " + book.getBookmark().value);
            Image image = new Image(MainApp.class.getResourceAsStream(BOOKMARK_ICON_PATH));
            bookmarkIcon.setImage(image);
        } else {
            bookmark.setText("No bookmark for this book yet");
            Image image = new Image(MainApp.class.getResourceAsStream(NO_BOOKMARK_ICON_PATH));
            bookmarkIcon.setImage(image);
        }
        book.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
