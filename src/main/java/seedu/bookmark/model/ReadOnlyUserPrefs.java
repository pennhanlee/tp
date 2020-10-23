package seedu.bookmark.model;

import java.nio.file.Path;

import seedu.bookmark.commons.core.GuiSettings;
import seedu.bookmark.logic.parser.Prefix;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBookmarkFilePath();

    String getSortingPreference();
}
