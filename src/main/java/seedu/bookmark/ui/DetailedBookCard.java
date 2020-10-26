package seedu.bookmark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;

/**
 * An UI component that displays more detailed information of a {@code Book}.
 */
public class DetailedBookCard extends BookCard {

    private static final String FXML = "DetailedBookCard.fxml";

    @FXML
    private Label percentageCompletion;

    @FXML
    private Label goal;

    /**
     * Creates a {@code DetailedBookCard} with the given {@code Book} and index to display.
     */
    public DetailedBookCard(Book book, int displayedIndex) {
        super(book, FXML);
        initialize(book, displayedIndex);
    }

    @Override
    protected void initialize(Book book, int displayedIndex) {
        super.initialize(book, displayedIndex);
        int percentageCompleted = calculateCompletion(book);
        String progressDisplay = "Progress: " + String.valueOf(percentageCompleted) + "%";
        String goalText = "Goal: " + book.getGoal().toString();
        goalText = determineGoalText(book, goalText);
        percentageCompletion.setText(progressDisplay);
        goal.setText(goalText);
        goal.setTextFill(determineGoalColour(book));
    }

    private int calculateCompletion(Book book) {
        float pagesRead = book.getPagesRead();
        float totalPages = book.getTotalPagesNumber();
        return (int) ((pagesRead / totalPages) * 100);
    }

    private Color determineGoalColour(Book book) {
        if (book.goalCompleted()) {
            return Color.GREEN;
        } else if (book.goalInProgress()) {
            return Color.YELLOW;
        } else if (book.goalOverdue()) {
            return Color.RED;
        } else { // No goal
            return Color.WHITE;
        }
    }

    private String determineGoalText(Book book, String goalTextOriginal) {
        String goalText = goalTextOriginal;
        if (book.goalCompleted()) {
            goalText += Goal.UI_COMPLETED;
        } else if (book.goalOverdue()) {
            goalText += Goal.UI_OVERDUE;
        } else if (book.goalInProgress()) {
            goalText += Goal.UI_IN_PROGRESS;
        }
        return goalText;
    }
}
