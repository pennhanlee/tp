package seedu.bookmark.model.book;

import java.util.function.Predicate;

public class IsSpecifiedBookPredicate implements Predicate<Book> {
    private final Book otherBook;

    public IsSpecifiedBookPredicate(Book otherBook) {
        this.otherBook = otherBook;
    }

    @Override
    public boolean test(Book book) {
        return book.equals(otherBook);
    }

    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == this) {
            return true;
        } else if (otherObj instanceof IsSpecifiedBookPredicate) {
            return otherBook.equals(((IsSpecifiedBookPredicate) otherObj).otherBook);
        }
        return false;
    }


}
