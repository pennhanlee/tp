package seedu.bookmark.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.bookmark.model.book.Name;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void consturctor_tooLogTagName_throwsIllegalArgumentException() {
        StringBuilder tooLong = new StringBuilder();
        // tooLong is one character too long
        tooLong.append("a".repeat(Tag.MAX_TAG_LENGTH + 2));

        assertThrows(IllegalArgumentException.class, () -> new Tag(tooLong.toString()));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // too long tag name
        StringBuilder tooLong = new StringBuilder();
        // tooLong is one character too long
        tooLong.append("a".repeat(Tag.MAX_TAG_LENGTH + 2));

        assertFalse(Tag.isValidTagName(tooLong.toString()));
    }

}
