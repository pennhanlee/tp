package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.bookmark.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f13-2.github.io/tp/";
    public static final String HEADER_MESSAGE = "Welcome to bookmark!\n"
                                                + "Click on the Copy URL button for the link to our Website!\n";

    public static final String ADD_INTRO = "You can now ADD a book! \n";
    public static final String ADD_COMMAND = "add n/{Book Name} g/{Genre} t/{Tags}* t/{Tags}* "
                                            + "tp/{Total Pages} b/{Bookmarked Page} \n";
    public static final String ADD_EXAMPLE = "eg. add n/Harry Potter g/Fiction t/Wizard tp/550 \n";
    public static final String ADD_RESPONSE = "Response: \nNew book added: Harry Potter "
                                            + "Genre: Fiction Total Pages: 550 Bookmarked at: 0 Tags: [Wizard]";

    public static final String DELETE_INTRO = "You can now DELETE a book! \n";
    public static final String DELETE_COMMAND = "delete {Index} \n";
    public static final String DELETE_EXAMPLE = "eg. delete 3 \n";
    public static final String DELETE_RESPONSE = "Response: \nDeleted Book: Tokyo Ghoul Genre: Anime Total Pages: 1000 "
                                            + "Bookmarked at: 3 Tags: [Japanese]";

    public static final String VIEW_INTRO = "You can now VIEW a book's contents! \n";
    public static final String VIEW_COMMAND = "view {Index} \n";
    public static final String VIEW_EXAMPLE = "eg. view 3 \n";
    public static final String VIEW_RESPONSE = "Response: \nViewing 3";

    public static final String LIST_INTRO = "You can now LIST all recorded books! \n";
    public static final String LIST_COMMAND = "list \n";
    public static final String LIST_EXAMPLE = "eg. list \n";
    public static final String LIST_RESPONSE = "Response: \nListing all books";

    public static final String EDIT_INTRO = "You can now EDIT a book's contents! \n";
    public static final String EDIT_COMMAND = "edit {Index} *t/{Tags} *b/{Bookmarked Page} \n";
    public static final String EDIT_EXAMPLE = "eg. edit 3 b/360 t/Anime \n";
    public static final String EDIT_RESPONSE = "Response: \nEdited Book: Haikyuu Genre: Manga Total Pages: 500"
                                            + " Bookmarked at: 360 Tags: [Anime]";

    public static final String ADD_MESSAGE = ADD_INTRO + ADD_COMMAND + ADD_EXAMPLE + ADD_RESPONSE;
    public static final String LIST_MESSAGE = LIST_INTRO + LIST_COMMAND + LIST_EXAMPLE + LIST_RESPONSE;
    public static final String VIEW_MESSAGE = VIEW_INTRO + VIEW_COMMAND + VIEW_EXAMPLE + VIEW_RESPONSE;
    public static final String EDIT_MESSAGE = EDIT_INTRO + EDIT_COMMAND + EDIT_EXAMPLE + EDIT_RESPONSE;
    public static final String DELETE_MESSAGE = DELETE_INTRO + DELETE_COMMAND + DELETE_EXAMPLE + DELETE_RESPONSE;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private Image addImage = new Image(this.getClass().getResourceAsStream("/images/add_command.png"));
    private Image listImage = new Image(this.getClass().getResourceAsStream("/images/list_command.png"));
    private Image viewImage = new Image(this.getClass().getResourceAsStream("/images/view_command.png"));
    private Image editImage = new Image(this.getClass().getResourceAsStream("/images/edit_command.png"));
    private Image deleteImage = new Image(this.getClass().getResourceAsStream("/images/delete_command.png"));

    @FXML
    private Button copyButton;

    @FXML
    private Label welcomeHelp;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox helpContainer;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        welcomeHelp.setText(HEADER_MESSAGE);
        scrollPane.setVvalue(0);
        helpContainer.getChildren().addAll(
                HelpBox.getHelpBox(ADD_MESSAGE, addImage),
                HelpBox.getHelpBox(LIST_MESSAGE, listImage),
                HelpBox.getHelpBox(VIEW_MESSAGE, viewImage),
                HelpBox.getHelpBox(EDIT_MESSAGE, editImage),
                HelpBox.getHelpBox(DELETE_MESSAGE, deleteImage)
        );
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
