package seedu.bookmark.model.history;

import seedu.bookmark.model.Library;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.ReadOnlyUserPrefs;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.Book;

import java.util.function.Predicate;

/**
 * Represents the state of the {@code Model}
 */
public class State {

    private final ReadOnlyLibrary library;
    private final ReadOnlyUserPrefs userPrefs;
    private final Predicate<? super Book> filterPredicate;

    /**
     * Static method to create a {@code State} with the given {@code ReadOnlyLibrary}, {@code ReadOnlyUserPrefs} and
     * {@code Predicate}
     *
     * Create and uses copies of the arguments provided.
     */
    public static State createState(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs,
                                    Predicate<? super Book> filterPredicate) {
        ReadOnlyLibrary copiedLibrary = new Library(library);
        ReadOnlyUserPrefs copiedUserPrefs = new UserPrefs(userPrefs);
        return new State(copiedLibrary, copiedUserPrefs, filterPredicate);
    }

    /**
     * Constructs a {@code State} with the given {@code ReadOnlyLibrary}, {@code ReadOnlyUserPrefs} and
     * {@code Predicate}
     */
    private State(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs, Predicate<? super Book> filterPredicate) {
        this.library = library;
        this.userPrefs = userPrefs;
        this.filterPredicate = filterPredicate;
    }

    public ReadOnlyLibrary getLibrary() {
        return this.library;
    }

    public ReadOnlyUserPrefs getUserPrefs() {
        return this.userPrefs;
    }

    public Predicate<? super Book> getPredicate() {
        return this.filterPredicate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof State) {
            State otherState = (State) other;
            return this.library.equals(otherState.library)
                    && this.userPrefs.equals(otherState.userPrefs)
                    && this.filterPredicate.equals(otherState.filterPredicate);
        }
        return false;
    }
}
