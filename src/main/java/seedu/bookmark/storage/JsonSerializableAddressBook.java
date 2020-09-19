package seedu.bookmark.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.model.BookList;
import seedu.bookmark.model.ReadOnlyBookList;
import seedu.bookmark.model.person.Book;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedBook> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedBook> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyBookList source) {
        persons.addAll(source.getBookList().stream().map(JsonAdaptedBook::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BookList toModelType() throws IllegalValueException {
        BookList bookList = new BookList();
        for (JsonAdaptedBook jsonAdaptedBook : persons) {
            Book book = jsonAdaptedBook.toModelType();
            if (bookList.hasPerson(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bookList.addPerson(book);
        }
        return bookList;
    }

}
