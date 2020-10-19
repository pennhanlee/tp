package seedu.bookmark.model.book.comparators;

import java.util.Comparator;
import seedu.bookmark.model.book.Book;

public class BookReadingProgressComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {
        float b1PagesRead = b1.getPagesRead();
        float b1TotalPages = b1.getTotalPagesNumber();
        float b1ReadingProgress = ((b1PagesRead / b1TotalPages) * 100);

        float b2PagesRead = b2.getPagesRead();
        float b2TotalPages = b2.getTotalPagesNumber();
        float b2ReadingProgress = ((b2PagesRead / b2TotalPages) * 100);

        return Float.compare(b1ReadingProgress, b2ReadingProgress);
    }
}
