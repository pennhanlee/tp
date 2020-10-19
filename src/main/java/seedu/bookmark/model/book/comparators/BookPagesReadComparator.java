package seedu.bookmark.model.book.comparators;

import java.util.Comparator;
import seedu.bookmark.model.book.Book;

/**
 * Sorts {@code Book}s based on {@code PagesRead} in descending order.
 */
public class BookPagesReadComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {
        int b1PagesRead = b1.getPagesRead();

        int b2PagesRead = b2.getPagesRead();

        return Integer.compare(b2PagesRead, b1PagesRead);
    }

    @Override
    public String toString() {
        return "Pages Read (Bookmark)";
    }
}
