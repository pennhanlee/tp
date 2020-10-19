package seedu.bookmark.model.book.comparators;

import java.util.Comparator;
import seedu.bookmark.model.book.Book;

public class BookGenreComparator implements Comparator<Book>{

    @Override
    public int compare(Book b1, Book b2) {
        String b1genre = b1.getGenre().toString();
        String b2genre = b2.getGenre().toString();
        return b1genre.compareTo(b2genre);
    }
}
