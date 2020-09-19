package seedu.bookmark.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.bookmark.commons.exceptions.DataConversionException;
import seedu.bookmark.model.BookList;
import seedu.bookmark.model.ReadOnlyBookList;

/**
 * Represents a storage for {@link BookList}.
 */
public interface BookmarkStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyBookList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookList> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyBookList> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBookList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyBookList addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyBookList)
     */
    void saveAddressBook(ReadOnlyBookList addressBook, Path filePath) throws IOException;

}
