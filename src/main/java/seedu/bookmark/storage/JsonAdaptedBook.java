package seedu.bookmark.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Book}.
 */
class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private final String name;
    private final String genre;
    private final String totalPages;
    private final String bookmark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    //    private final List<JsonAdaptedNote> notes = new ArrayList<>();
    //
    //    /**
    //     * Overloaded constructor that includes notes.
    //     * Constructs a {@code JsonAdaptedBook} with the given book details.
    //     */
    //    @JsonCreator
    //    public JsonAdaptedBook(@JsonProperty("name") String name,
    //                           @JsonProperty("genre") String genre,
    //                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
    //                           @JsonProperty("totalPages") String totalPages,
    //                           @JsonProperty("bookmark") String bookmark,
    //                           @JsonProperty("notes") List<JsonAdaptedNote> notes) {
    //        this.name = name;
    //        this.genre = genre;
    //        if (tagged != null) {
    //            this.tagged.addAll(tagged);
    //        }
    //        this.totalPages = totalPages;
    //        this.bookmark = bookmark;
    //        if (notes != null) {
    //            this.notes.addAll(notes);
    //        }
    //    }

    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("name") String name,
                           @JsonProperty("genre") String genre,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("totalPages") String totalPages,
                           @JsonProperty("bookmark") String bookmark) {
        this.name = name;
        this.genre = genre;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.totalPages = totalPages;
        this.bookmark = bookmark;
    }
    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        name = source.getName().fullName;
        genre = source.getGenre().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        totalPages = source.getTotalPages().value;
        bookmark = source.getBookmark().value;
        //        notes.addAll(source.getNotes().stream()
        //                              .map(note -> new JsonAdaptedNote(note.title, note.text))
        //                              .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        final List<Tag> bookTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            bookTags.add(tag.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (genre == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Genre.class.getSimpleName()));
        }
        if (!Genre.isValidGenre(genre)) {
            throw new IllegalValueException(Genre.MESSAGE_CONSTRAINTS);
        }
        final Genre modelGenre = new Genre(genre);

        final Set<Tag> modelTags = new HashSet<>(bookTags);

        if (totalPages == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TotalPages.class.getSimpleName()));
        }
        if (!TotalPages.isValidTotalPages(totalPages)) {
            throw new IllegalValueException(TotalPages.MESSAGE_CONSTRAINTS);
        }
        final TotalPages modelTotalPages = new TotalPages(totalPages);

        if (bookmark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Bookmark.class.getSimpleName()));
        }

        final Bookmark modelBookmark = new Bookmark(bookmark);
        if (!Bookmark.isValidBookmark(modelBookmark, modelTotalPages)) {
            throw new IllegalValueException(Bookmark.MESSAGE_CONSTRAINTS);
        }

        //        final List<Note> bookNotes = new ArrayList<>();
        //        for (JsonAdaptedNote note : notes) {
        //            System.out.println(note.toModelType());
        //            bookNotes.add(note.toModelType());
        //        }
        //
        //        final ArrayList<Note> modelNotes = new ArrayList<>(bookNotes);
        //
        //        return new Book(modelName, modelGenre, modelTags, modelTotalPages, modelBookmark, modelNotes);

        return new Book(modelName, modelGenre, modelTags, modelTotalPages, modelBookmark);
    }

}
