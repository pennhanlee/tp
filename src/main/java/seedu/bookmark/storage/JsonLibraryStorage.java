package seedu.bookmark.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.commons.exceptions.DataConversionException;
import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.commons.util.FileUtil;
import seedu.bookmark.commons.util.JsonUtil;
import seedu.bookmark.model.ReadOnlyLibrary;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonLibraryStorage implements LibraryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLibraryStorage.class);

    private Path filePath;

    public JsonLibraryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLibrary> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLibrary> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLibrary> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLibrary.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyLibrary addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyLibrary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyLibrary addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLibrary(addressBook), filePath);
    }

}
