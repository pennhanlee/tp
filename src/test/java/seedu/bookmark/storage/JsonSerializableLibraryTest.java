package seedu.bookmark.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.commons.util.JsonUtil;
import seedu.bookmark.model.Library;
import seedu.bookmark.testutil.TypicalBooks;

public class JsonSerializableLibraryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLibraryTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalBooksLibrary.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidBookLibrary.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateBookLibrary.json");

    @Test
    public void toModelType_typicalBooksFile_success() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableLibrary.class).get();
        Library libraryFromFile = dataFromFile.toModelType();
        Library typicalBooksLibrary = TypicalBooks.getTypicalLibrary();
        assertEquals(libraryFromFile, typicalBooksLibrary);
    }

    @Test
    public void toModelType_invalidBookFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateBooks_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLibrary.MESSAGE_DUPLICATE_BOOKS,
                dataFromFile::toModelType);
    }

}
