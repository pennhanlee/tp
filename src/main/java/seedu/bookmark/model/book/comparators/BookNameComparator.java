package seedu.bookmark.model.book.comparators;

import java.util.Comparator;
import seedu.bookmark.model.book.Book;

public class BookNameComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {
        String b1name = b1.getName().toString();
        String b2name = b2.getName().toString();
        return b1name.compareTo(b2name);
    }
}
