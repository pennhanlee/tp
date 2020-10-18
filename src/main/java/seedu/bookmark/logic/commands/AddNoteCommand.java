package seedu.bookmark.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TEXT;
import static seedu.bookmark.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.bookmark.commons.core.Messages;
import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.Note;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * Adds a Book into a Library.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the book. \n"
                                                       + "Parameters: INDEX (must be a positive integer) "
                                                       + PREFIX_NAME + "TITLE "
                                                       + PREFIX_NOTE_TEXT + "TEXT";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added Note to Book: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the library.";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists for the book.";

    private final Index index;
    private final Note note;
    /**
     * Creates a NoteCommand to add a {@code note} to the specified {@code book}
     */
    public AddNoteCommand(Index index, Note note) {
        requireNonNull(index);
        requireNonNull(note);
        this.index = index;
        this.note = note;
    }

    /**
     * Creates and returns a {@code Book} with the details of {@code bookToEdit}
     * edited with {@code editBookDescriptor}.
     */
    protected static Book createEditedBook(Book bookToEdit, Note note) throws CommandException {
        assert bookToEdit != null;

        Name updatedName = bookToEdit.getName();
        Genre updatedGenre = bookToEdit.getGenre();
        Set<Tag> updatedTags = bookToEdit.getTags();
        TotalPages updatedTotalPages = bookToEdit.getTotalPages();
        Bookmark updatedBookmark = bookToEdit.getBookmark();
        List<Note> updatedNotes = new ArrayList<>(bookToEdit.getNotes());
        if (bookToEdit.containsNote(note)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }
        updatedNotes.add(note);

        return new Book(updatedName, updatedGenre, updatedTags, updatedTotalPages, updatedBookmark, updatedNotes);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = createEditedBook(bookToEdit, note);
        if (!bookToEdit.isSameBook(editedBook) && model.hasBook(editedBook)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }
        if (!Bookmark.isValidBookmark(editedBook.getBookmark(), editedBook.getTotalPages())) {
            throw new CommandException(Bookmark.MESSAGE_CONSTRAINTS);
        }
        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS);
        return new CommandResult(String.format(MESSAGE_ADD_NOTE_SUCCESS, editedBook));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddNoteCommand)) {
            return false;
        }

        // state check
        AddNoteCommand e = (AddNoteCommand) other;
        return index.equals(e.index)
                       && note.equals(e.note);

    }
}
