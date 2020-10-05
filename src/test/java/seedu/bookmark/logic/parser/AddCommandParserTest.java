package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.commands.CommandTestUtil.*;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bookmark.testutil.TypicalBooks.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.AddCommand;
import seedu.bookmark.model.book.*;
import seedu.bookmark.model.tag.Tag;
import seedu.bookmark.testutil.BookBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Book expectedBook = new BookBuilder(FULL_JANE_EYRE).withTags(VALID_TAG_GOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                 + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE + BOOKMARK_DESC_JANE_EYRE, new AddCommand(expectedBook));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_1984 + NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                 + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE + BOOKMARK_DESC_JANE_EYRE, new AddCommand(expectedBook));

        // multiple genres - last genres accepted
        assertParseSuccess(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_1984 + GENRE_DESC_JANE_EYRE
                 + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE + BOOKMARK_DESC_JANE_EYRE, new AddCommand(expectedBook));

        // multiple tags - all accepted
        Book expectedBookMultipleTags = new BookBuilder(FULL_JANE_EYRE).withTags(VALID_TAG_BAD, VALID_TAG_GOOD)
                .build();
        assertParseSuccess(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                + TAG_DESC_BAD + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE + BOOKMARK_DESC_JANE_EYRE, new AddCommand(expectedBookMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Book expectedBook = new BookBuilder(COMPULSORY_NINETEEN_EIGHTY_FOUR).withTags().build();
        assertParseSuccess(parser, NAME_DESC_1984 + GENRE_DESC_1984 + TOTAL_PAGES_DESC_1984,
                new AddCommand(expectedBook));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_JANE_EYRE + GENRE_DESC_JANE_EYRE + TOTAL_PAGES_DESC_JANE_EYRE,
                expectedMessage);

        // missing genre prefix
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + VALID_GENRE_JANE_EYRE + TOTAL_PAGES_DESC_JANE_EYRE,
                expectedMessage);

        // missing total prefix
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE + VALID_TOTAL_PAGES_JANE_EYRE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_JANE_EYRE + VALID_GENRE_JANE_EYRE + VALID_TOTAL_PAGES_JANE_EYRE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + GENRE_DESC_JANE_EYRE
                + TAG_DESC_BAD + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE, Name.MESSAGE_CONSTRAINTS);

        // invalid genre
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + INVALID_GENRE_DESC
                + TAG_DESC_BAD + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE, Genre.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                + INVALID_TAG_DESC + VALID_TAG_BAD + TOTAL_PAGES_DESC_JANE_EYRE, Tag.MESSAGE_CONSTRAINTS);

        // invalid total pages
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                + INVALID_TOTAL_PAGES_DESC, TotalPages.MESSAGE_CONSTRAINTS);

        // invalid bookmark
        assertParseFailure(parser, NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                + TOTAL_PAGES_DESC_JANE_EYRE + INVALID_BOOKMARK_DESC, Bookmark.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + GENRE_DESC_JANE_EYRE + TOTAL_PAGES_DESC_JANE_EYRE,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_JANE_EYRE + GENRE_DESC_JANE_EYRE
                        + TAG_DESC_BAD + TAG_DESC_GOOD + TOTAL_PAGES_DESC_JANE_EYRE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
