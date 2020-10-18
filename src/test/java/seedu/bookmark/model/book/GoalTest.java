package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.bookmark.testutil.BookBuilder;

import java.time.LocalDate;

public class GoalTest {
    public static final String BEFORE_NOW = "10-10-1999";
    public static final String VALID_PAGE = "8";
    public static final String AFTER_NOW = "10-10-2021";


    @Test
    public void test_isSameGoal() {
        Goal g1 = new Goal("10", "15-10-2021");
        Goal g2 = new Goal("10", "15-10-2021");
        Goal g3 = Goal.defaultGoal();
        assertTrue(g1.equals(g1));
        assertTrue(g1.equals(g2));
        assertTrue(g2.equals(g1));
        assertTrue(g2.equals(g2));

        assertFalse(g1.equals(g3));
        assertFalse(g2.equals(g3));
    }

    @Test
    public void test_overdueDeadline() {
        Goal gOverdue = new Goal(VALID_PAGE, BEFORE_NOW);
        Goal gNotDue = new Goal(VALID_PAGE, AFTER_NOW);
        Book overdueBook = Book.setGoal(new BookBuilder().build(), gOverdue);
        Book notDueBook = Book.setGoal(new BookBuilder().build(), gNotDue);

        assertTrue(overdueBook.getGoal().isOverdue());
        assertFalse(notDueBook.getGoal().isOverdue());
    }

    @Test
    public void test_validGoal() {
        String valid_date = "20-10-2024";
        String invalid_date = "19183109823/11/2024";
        String valid_page = "10";
        String invalid_page = "10b";
        String invalid_year = "20/11/1";

        assertTrue(valid_date.matches(Goal.DEADLINE_REGEX));
        assertTrue(Goal.isValidGoal(valid_page, valid_date));
        assertFalse(Goal.isValidGoal(invalid_page, valid_date));
        assertFalse(Goal.isValidGoal(valid_page, invalid_date));
        assertFalse(Goal.isValidGoal(valid_page, invalid_year));
    }
}
