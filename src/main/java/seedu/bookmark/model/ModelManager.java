package seedu.bookmark.model;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.model.book.Book;

import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_READING_PROGRESS;

import seedu.bookmark.model.book.comparators.BookGenreComparator;
import seedu.bookmark.model.book.comparators.BookNameComparator;
import seedu.bookmark.model.book.comparators.BookPagesReadComparator;
import seedu.bookmark.model.book.comparators.BookReadingProgressComparator;

/**
 * Represents the in-memory model of the bookmark data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final UserPrefs userPrefs;
    private FilteredList<Book> filteredBooks;
    private Comparator<Book> comparator;

    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with library: " + library + " and user prefs " + userPrefs);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        this.comparator = comparatorGenerator(prefixGenerator(userPrefs.getSortingPreference()));
        filteredBooks = new FilteredList<>(this.library.getBookList());
    }

    public ModelManager() {
        this(new Library(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBookmarkFilePath() {
        return userPrefs.getBookmarkFilePath();
    }

    @Override
    public void setBookmarkFilePath(Path bookMarkFilePath) {
        requireNonNull(bookMarkFilePath);
        userPrefs.setBookmarkFilePath(bookMarkFilePath);
    }

    @Override
    public String getSortingPreference() { return userPrefs.getSortingPreference(); }

    @Override
    public void setSortingPreference(String newSortingPreference) {
        requireNonNull(newSortingPreference);
        userPrefs.setSortingPreference(newSortingPreference);
    }

    //=========== Library ================================================================================

    @Override
    public void setLibrary(ReadOnlyLibrary library) {
        this.library.resetData(library);
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return library.hasBook(book);
    }

    @Override
    public void deleteBook(Book target) {
        library.removeBook(target);
    }

    @Override
    public void addBook(Book book) {
        library.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        sortByDefaultComparator();
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);

        library.setBook(target, editedBook);
    }

    @Override
    public void sortByDefaultComparator() {
        sortFilteredBookList(this.comparator);
    }

    //=========== Filtered Book List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Book} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
    }

    @Override
    public void sortFilteredBookList(Comparator<Book> comparator) {
        if (comparator instanceof BookNameComparator) {
            setSortingPreference(PREFIX_NAME.toString());
        } else if (comparator instanceof BookGenreComparator) {
            setSortingPreference(PREFIX_GENRE.toString());
        } else if (comparator instanceof BookPagesReadComparator) {
            setSortingPreference(PREFIX_BOOKMARK.toString());
        } else if (comparator instanceof BookReadingProgressComparator) {
            setSortingPreference(PREFIX_READING_PROGRESS.toString());
        }
        this.comparator = comparator;
        this.library.sortBooks(comparator);
    }

    /**
     * Returns a comparator based on the input prefix
     * @return Comparator based on input prefix
     */
    public Comparator<Book> comparatorGenerator(Prefix inputPrefix) {
        Comparator<Book> comparator = null;
        if (inputPrefix == PREFIX_NAME) {
            comparator = new BookNameComparator();
        } else if (inputPrefix == PREFIX_GENRE) {
            comparator = new BookGenreComparator();
        } else if (inputPrefix == PREFIX_BOOKMARK) {
            comparator = new BookPagesReadComparator();
        } else if (inputPrefix == PREFIX_READING_PROGRESS) {
            comparator = new BookReadingProgressComparator();
        }
        return comparator;
    }

    /**
     * Returns a prefix based on the input string.
     * @return Prefix based on the input string.
     */
    public Prefix prefixGenerator(String prefix) {
        Prefix result;
        switch (prefix) {
        case "n/":
            result = PREFIX_NAME;
            break;
        case "g/":
            result = PREFIX_GENRE;
            break;
        case "b/":
            result = PREFIX_BOOKMARK;
            break;
        case "rp/":
            result = PREFIX_READING_PROGRESS;
            break;
        default:
            return PREFIX_NAME;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return library.equals(other.library)
                && userPrefs.equals(other.userPrefs)
                && filteredBooks.equals(other.filteredBooks);
    }

}
