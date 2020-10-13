package seedu.bookmark.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.util.AppUtil.checkArgument;

public class Note {
    public static final String MESSAGE_CONSTRAINTS =
            "Notes should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String title;
    public final String text;

    /**
     * Constructs an {@code Note}.
     *
     * @param note A valid note string.
     */
    public Note(String title, String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        this.title = title;
        this.text = note;
    }

    /**
     * Returns if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return title + ":\n" + text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof Note // instanceof handles nulls
                                   && text.equals(((Note) other).text)
                                   && title.equals(((Note) other).title)); // state check
    }

    @Override
    public int hashCode() {
        return (title + text).hashCode();
    }
}
