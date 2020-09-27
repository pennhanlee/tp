package seedu.bookmark.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.bookmark.testutil.Assert.assertThrows;
import static seedu.bookmark.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;

public class JsonAdaptedBookTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENRE = "F@ACTS0nLY";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENRE = BENSON.getGenre().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedBook person = new JsonAdaptedBook(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBook person =
                new JsonAdaptedBook(INVALID_NAME, VALID_GENRE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBook person = new JsonAdaptedBook(null, VALID_GENRE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGenre_throwsIllegalValueException() {
        JsonAdaptedBook person =
                new JsonAdaptedBook(VALID_NAME, INVALID_GENRE, VALID_TAGS);
        String expectedMessage = Genre.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGenre_throwsIllegalValueException() {
        JsonAdaptedBook person = new JsonAdaptedBook(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBook person =
                new JsonAdaptedBook(VALID_NAME, VALID_GENRE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
