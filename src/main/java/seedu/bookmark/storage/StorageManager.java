package seedu.bookmark.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.commons.exceptions.DataConversionException;
import seedu.bookmark.model.ReadOnlyBookList;
import seedu.bookmark.model.ReadOnlyUserPrefs;
import seedu.bookmark.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BookmarkStorage bookmarkStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookmarkStorage bookmarkStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.bookmarkStorage = bookmarkStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return bookmarkStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBookList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(bookmarkStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBookList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bookmarkStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyBookList addressBook) throws IOException {
        saveAddressBook(addressBook, bookmarkStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyBookList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bookmarkStorage.saveAddressBook(addressBook, filePath);
    }

}
