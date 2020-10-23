package seedu.bookmark.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.model.wordstore.Word;
import seedu.bookmark.model.wordstore.WordStore;
import seedu.bookmark.testutil.TypicalBooks;
import seedu.bookmark.testutil.TypicalWords;

public class WordBankTest {

    private WordBank wordBank = TypicalWords.getEmptyWordBank();

    //-------------------- initWordBank() ------------------------//

    //    @Test
    //    public void initWordBank() {
    //        wordBank.initWordBank(TypicalBooks.getTypicalLibrary());
    //
    //    }

    //--------------------- handleNewBook() -----------------------//

    @Test
    public void handleNew_nullBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wordBank.handleNewBook(null));
    }

    @Test
    public void handle_newBook() {
        Book book = TypicalBooks.FULL_JANE_EYRE;

        wordBank.handleNewBook(book);
        String bookName = book.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String bookGenre = book.getGenre().value;
        String[] genreSplit = bookGenre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = book.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }

        WordStore nameStore = wordBank.getWordStore(PREFIX_NAME.getPrefix());
        WordStore genreStore = wordBank.getWordStore(PREFIX_GENRE.getPrefix());
        WordStore tagStore = wordBank.getWordStore(PREFIX_TAG.getPrefix());
        for (String name : nameWords) {
            assertTrue(nameStore.contains(name));
        }
        for (String genre : genreWords) {
            assertTrue(genreStore.contains(genre));
        }
        for (String tag : tagWords) {
            assertTrue(tagStore.contains(tag));
        }
    }

    //--------------------- handleOldBook() -----------------------//

    @Test
    public void handleOld_nullBook_throwsNullPointerExcepition() {
        assertThrows(NullPointerException.class, () -> wordBank.handleOldBook(null));
    }

    @Test
    public void handle_oldBook() {
        Book book = TypicalBooks.FULL_JANE_EYRE;

        wordBank.handleNewBook(book);

        String bookName = book.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String bookGenre = book.getGenre().value;
        String[] genreSplit = bookGenre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = book.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }

        wordBank.handleOldBook(book);

        WordStore nameStore = wordBank.getWordStore(PREFIX_NAME.getPrefix());
        WordStore genreStore = wordBank.getWordStore(PREFIX_GENRE.getPrefix());
        WordStore tagStore = wordBank.getWordStore(PREFIX_TAG.getPrefix());
        for (String name : nameWords) {
            assertFalse(nameStore.contains(name));
        }
        for (String genre : genreWords) {
            assertFalse(genreStore.contains(genre));
        }
        for (String tag : tagWords) {
            assertFalse(tagStore.contains(tag));
        }
    }

    //------------------- updateWordBank() ----------------------//

    @Test
    public void updateWordBank() {

        Book originalBook = TypicalBooks.COMPULSORY_JANE_EYRE; // Have: Name, Genre, Do not have: Tags
        Book editedBook = TypicalBooks.FULL_JANE_EYRE; // Have: Name, Genre, Tags

        String bookName = editedBook.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String bookGenre = editedBook.getGenre().value;
        String[] genreSplit = bookGenre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = editedBook.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }

        WordStore nameStore = wordBank.getWordStore(PREFIX_NAME.getPrefix());
        WordStore genreStore = wordBank.getWordStore(PREFIX_GENRE.getPrefix());
        WordStore tagStore = wordBank.getWordStore(PREFIX_TAG.getPrefix());

        wordBank.addToWordBank(originalBook);

        for (String name : nameWords) {
            assertTrue(nameStore.contains(name));
        }
        for (String genre : genreWords) {
            assertTrue(genreStore.contains(genre));
        }
        for (String tag : tagWords) {
            assertFalse(tagStore.contains(tag));
        }

        wordBank.updateWordBank(originalBook, editedBook); //Tags should now be present.

        for (String name : nameWords) {
            assertTrue(nameStore.contains(name));
        }
        for (String genre : genreWords) {
            assertTrue(genreStore.contains(genre));
        }
        for (String tag : tagWords) {
            assertTrue(tagStore.contains(tag));
        }

    }


    @Test
    public void update_wordBank_checkWordCount() {

        Book originalBook = TypicalBooks.COMPULSORY_JANE_EYRE; // Have: Name, Genre, Do not have: Tags
        Book editedBook = TypicalBooks.FULL_JANE_EYRE; // Have: Name, Genre, Tags

        String bookName = editedBook.getName().fullName;
        String[] nameSplit = bookName.split("\\s+");
        List<String> nameWords = Arrays.asList(nameSplit);
        String bookGenre = editedBook.getGenre().value;
        String[] genreSplit = bookGenre.split("\\s+");
        List<String> genreWords = Arrays.asList(genreSplit);
        Set<Tag> tags = editedBook.getTags();
        List<String> tagWords = new ArrayList<>();
        for (Tag tag : tags) {
            tagWords.add(tag.getTagName());
        }

        WordStore nameStore = wordBank.getWordStore(PREFIX_NAME.getPrefix());
        WordStore genreStore = wordBank.getWordStore(PREFIX_GENRE.getPrefix());
        WordStore tagStore = wordBank.getWordStore(PREFIX_TAG.getPrefix());

        wordBank.addToWordBank(originalBook);

        for (String name : nameWords) {
            assertTrue(nameStore.contains(name));

            Word targetWord = nameStore.getWordStore().stream()
                                .filter(word -> word.getWord().equals(name)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
        for (String genre : genreWords) {
            assertTrue(genreStore.contains(genre));

            Word targetWord = genreStore.getWordStore().stream()
                    .filter(word -> word.getWord().equals(genre)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
        for (String tag : tagWords) {
            assertFalse(tagStore.contains(tag));
        }

        wordBank.updateWordBank(originalBook, editedBook); //Tags should now be present.

        //Since oldbook & newbook have the same name and genre,
        //oldbook removed & newbook added == name & genre count = 1
        //tag was newly added so tag count = 1.

        for (String name : nameWords) {
            assertTrue(nameStore.contains(name));

            Word targetWord = nameStore.getWordStore().stream()
                    .filter(word -> word.getWord().equals(name)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
        for (String genre : genreWords) {
            assertTrue(genreStore.contains(genre));

            Word targetWord = genreStore.getWordStore().stream()
                    .filter(word -> word.getWord().equals(genre)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }
        for (String tag : tagWords) {
            assertTrue(tagStore.contains(tag));

            Word targetWord = tagStore.getWordStore().stream()
                    .filter(word -> word.getWord().equals(tag)).findFirst().get();
            assertEquals(1, targetWord.getCount());
        }

    }

}

