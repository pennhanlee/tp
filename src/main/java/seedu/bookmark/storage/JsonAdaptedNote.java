package seedu.bookmark.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedNote {

    private final String title;
    private final String text;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code title} and {@code text}.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("title") String title, @JsonProperty("text") String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        title = source.title;
        text = source.text;
    }

    @JsonValue
    public String getTitle() {
        return title;
    }

    @JsonValue
    public String getText() {
        return text;
    }
    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Note toModelType() throws IllegalValueException {
        if (!Note.isValidNote(title, text)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Note(title, text);
    }

}
