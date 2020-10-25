package seedu.bookmark.model;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.sortingPrefixGenerator;
import static seedu.bookmark.model.book.comparators.ComparatorGenerator.comparatorGenerator;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.model.book.Book;

/**
 * Represents the in-memory model of the bookmark data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final UserPrefs userPrefs;
    private FilteredList<Book> filteredBooks;
    private Comparator<Book> comparator;
    private final WordBank wordBank;

    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with library: " + library + " and user prefs " + userPrefs);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        this.comparator = comparatorGenerator(sortingPrefixGenerator(userPrefs.getSortingPreference()));
        filteredBooks = new FilteredList<>(this.library.getBookList());
        this.wordBank = new WordBank(library);
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
    public String getSortingPreference() {
        return userPrefs.getSortingPreference();
    }

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
    public WordBank getWordBank() {
        return wordBank;
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return library.hasBook(book);
    }

    @Override
    public void deleteBook(Book target) {
        library.removeBook(target);
        wordBank.deleteFromWordBank(target);

    }

    @Override
    public void addBook(Book book) {
        library.addBook(book);
        wordBank.addToWordBank(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        sortByDefaultComparator();
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);
        library.setBook(target, editedBook);
        wordBank.updateWordBank(target, editedBook);
    }

    @Override
    public void sortByDefaultComparator() {
        if (this.comparator != null) { //should be removed, should sort by date added field
            sortFilteredBookList(this.comparator);
        }
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
        this.comparator = comparator;
        this.library.sortBooks(comparator);
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
