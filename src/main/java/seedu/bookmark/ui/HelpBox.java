package seedu.bookmark.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpBox extends VBox {
    @FXML
    private Label exampleCommand;
    @FXML
    private ImageView examplePic;

    private HelpBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exampleCommand.setText(text);
        examplePic.setImage(img);
    }

    /**
     * Creates a HelpBox object
     * @param text Text that is to be included in the Help Box
     * @param img A picture as an example
     * @return a HelpBox object
     */
    public static HelpBox getHelpBox(String text, Image img) {
        return new HelpBox(text, img);
    }
}
