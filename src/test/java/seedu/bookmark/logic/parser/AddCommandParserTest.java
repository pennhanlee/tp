package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_AMY;
import static seedu.bookmark.logic.commands.CommandTestUtil.GENRE_DESC_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.bookmark.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.bookmark.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.bookmark.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.bookmark.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_GENRE_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalPersons.AMY;
import static seedu.bookmark.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.AddCommand;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Book expectedBook = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + GENRE_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedBook));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + GENRE_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedBook));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + GENRE_DESC_AMY + GENRE_DESC_BOB
                 + TAG_DESC_FRIEND, new AddCommand(expectedBook));

        // multiple tags - all accepted
        Book expectedBookMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB  + GENRE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedBookMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Book expectedBook = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + GENRE_DESC_AMY,
                new AddCommand(expectedBook));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + GENRE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_GENRE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_GENRE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC  + GENRE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_GENRE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Genre.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + GENRE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + GENRE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + GENRE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
