package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.bookmark.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HEADER_MESSAGE = "Welcome to bookmark! These are the commands currently avaliable! \n"
                                                + "(* means it is optional!) \n";
    public static final String HELP_ADD = "Add a book!: ";
    public static final String ADD_COMMAND = "add n/{Book Name} g/{Genre} t/{Tags}* t/{Tags}* "
                                            + "tp/{Total Pages} b/{Bookmarked Page} \n";
    public static final String ADD_EXAMPLE = "eg. add n/Harry Potter g/Fiction t/Wizard tp/550 \n";
    public static final String ADD_RESPONSE = "Response: New book added: Harry Potter Genre: Fiction Total Pages: 550"
                                            + "Bookmarked at: 0 Tags: [Wizard]";
    public static final String HELP_DELETE = "Delete a book!: ";
    public static final String DELETE_COMMAND = "delete {Index} \n";
    public static final String DELETE_EXAMPLE = "eg. delete 3 \n";
    public static final String DELETE_RESPONSE = "";
    public static final String HELP_VIEW = "View a book!: ";
    public static final String VIEW_COMMAND = "view {Index} \n";
    public static final String VIEW_EXAMPLE = "eg. view 3 \n";
    public static final String VIEW_RESPONSE = "";
    public static final String HELP_LIST = "List all books!: ";
    public static final String LIST_COMMAND = "list \n";
    public static final String LIST_EXAMPLE = "eg. list \n";
    public static final String LIST_RESPONSE = "";
    public static final String HELP_EDIT = "Edit a book!: ";
    public static final String EDIT_COMMAND = "edit {Index} *t/{Tags} *b/{Bookmarked Page} \n";
    public static final String EDIT_EXAMPLE = "eg. edit 3 b/360 \n";
    public static final String EDIT_RESPONSE = "";
    public static final String LINE_BREAK = " _________________________________________________________________ \n";

    //public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;
    public static final String HELP_MESSAGE = HEADER_MESSAGE + HELP_ADD + ADD_COMMAND + ADD_EXAMPLE
                                              + LINE_BREAK
                                              + HELP_LIST + LIST_COMMAND + LIST_EXAMPLE
                                              + LINE_BREAK
                                              + HELP_VIEW + VIEW_COMMAND + VIEW_EXAMPLE
                                              + LINE_BREAK
                                              + HELP_EDIT + EDIT_COMMAND + EDIT_EXAMPLE
                                              + LINE_BREAK
                                              + HELP_DELETE + DELETE_COMMAND + DELETE_EXAMPLE;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
