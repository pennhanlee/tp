package seedu.bookmark.model;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.model.wordstore.WordStore;


public class WordBank {

    private final ReadOnlyLibrary library;
    private final WordStore nameWordStore;
    private final WordStore genreWordStore;
    private final WordStore tagWordStore;

    /**
     * Creates a WordBank object
     * @param library library
     */
    public WordBank(ReadOnlyLibrary library) {
        this.library = library;
        this.nameWordStore = new WordStore();
        this.genreWordStore = new WordStore();
        this.tagWordStore = new WordStore();
        initWordBank(library);
    }

    /**
     * Initialises a List that stores unique words
     * in preparation for Usage by the EditDistance algorithm.
     */
    public void initWordBank(ReadOnlyLibrary library) {
        ObservableList<Book> librarylist = library.getBookList();
        for (Book book : librarylist) {
            handleNewBook(book);
        }
    }

    /**
     * Deletes old words and adds new words into the
     * list
     * @param oldBook Book Object that is deleted.
     * @param newBook Book Object that is added.
     */
    public void updateWordBank(Book oldBook, Book newBook) {
        handleOldBook(oldBook);
        handleNewBook(newBook);

    }

    /**
     * Adds unique words from a newly added book into
     * the list.
     * @param book book to be added
     */
    public void addToWordBank(Book book) {
        handleNewBook(book);
    }

    /**
     * Deletes old words of an oldbook from the list
     * @param book book to be deleted
     */
    public void deleteFromWordBank(Book book) {
        handleOldBook(book);
    }

    /**
     * Adds words into the list
     * @param book book to be processed
     */
    public void handleNewBook(Book book) {
        requireNonNull(book);
        String bookName = book.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String genre = book.getGenre().value;
        String[] genreSplit = genre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = book.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }
        nameWordStore.addWords(nameWords);
        genreWordStore.addWords(genreWords);
        tagWordStore.addWords(tagWords);
    }


    /**
     * Adds words into the list
     * @param book book to be processed
     */
    public void handleOldBook(Book book) {
        requireNonNull(book);
        String bookName = book.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String genre = book.getGenre().value;
        String[] genreSplit = genre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = book.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }
        nameWordStore.deleteWords(nameWords);
        genreWordStore.deleteWords(genreWords);
        tagWordStore.deleteWords(tagWords);
    }

    /**
     * Returns a WordStore based on the type indicated.
     * @param type String
     * @return WordStore
     */
    public WordStore getWordStore(String type) {
        if (type.equals(PREFIX_NAME.getPrefix())) {
            return this.nameWordStore;
        } else if (type.equals(PREFIX_GENRE.getPrefix())) {
            return this.genreWordStore;
        } else if (type.equals(PREFIX_TAG.getPrefix())) {
            return this.tagWordStore;
        } else {
            return this.nameWordStore; //by default
        }
    }

}
