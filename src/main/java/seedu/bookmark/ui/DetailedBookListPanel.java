package seedu.bookmark.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Note;

/**
 * Panel that shows detailed information about the books.
 */
public class DetailedBookListPanel extends BookListPanel {

    private static final String FXML = "DetailedBookListPanel.fxml";

    @FXML
    private ListView<Note> bookNotesListView;
    @FXML
    private Label notesHeading;


    /**
     * Creates a {@code DetailedBookListPanel} with the given {@code ObservableList}.
     */
    public DetailedBookListPanel(ObservableList<Book> bookList) {
        super(FXML);
        assert(bookList.size() <= 1);

        bookListView.setItems(bookList);
        bookListView.setCellFactory(lv -> new DetailedBookListViewCell());

        showNotes(bookList);
        bookList.addListener((ListChangeListener<Book>) c -> {
            showNotes(bookList);
        });
    }

    private void showNotes(ObservableList<Book> bookList) {
        ObservableList<Note> notes = getNotes(bookList);
        bookNotesListView.setItems(notes);
        bookNotesListView.setCellFactory(lv -> new NoteListViewCell());
        if (notes.size() < 1) {
            notesHeading.setText("Notes: No Notes to display");
        } else {
            notesHeading.setText("Notes");
        }
    }

    /**
     * Returns a {@code ObservableList<Note>} containing the notes in the given book list.
     */
    private ObservableList<Note> getNotes(List<Book> books) {
        List<Note> notes = books.stream()
                .map(Book::getNotes)
                .reduce(new ArrayList<Note>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
        return FXCollections.observableArrayList(notes);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code DetailedBookCard}.
     */
    class DetailedBookListViewCell extends ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);

            if (empty || book == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedBookCard(book, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the {@code Note}(s) of a {@code Book} using a {@code NoteCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }
}
