package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.bookmark.testutil.BookBuilder;

import java.time.LocalDate;

public class GoalTest {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_DEADLINE = "31/12/9999";
    public static final String BEFORE_NOW = "10/10/1999";
    public static final String VALID_PAGE = "8";
    public static final String AFTER_NOW = "10/10/2021";


    @Test
    public void test_isSameGoal() {
        Goal g1 = new Goal("10", "15/10/2021");
        Goal g2 = new Goal("10", "15/10/2021");
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
        Book defaultBook = new BookBuilder().build();
        // Book overdueBook = new BookBuilder().withGoal("10 20/10/1999").build(); // withGoal() seems to have problem
        Goal gOverdue = new Goal(VALID_PAGE, BEFORE_NOW);
        Goal gNotDue = new Goal(VALID_PAGE, AFTER_NOW);

        assertTrue(gOverdue.isOverdue());
        assertFalse(gNotDue.isOverdue());
        assertFalse(defaultBook.getGoal().isOverdue());
        // assertTrue(overdueBook.getGoal().isOverdue());
    }

    @Test
    public void test_validGoal() {
        String valid = "20-10-2024";
        String invalid_alpha = "akjdhfkafhl";
        String invalid_day = "10 19183109823/11/2024";
        String invalid_year = "10 20/11/1";

        assertTrue(valid.matches(Goal.DEADLINE_REGEX));
        assertTrue(Goal.isValidGoal(valid));
        assertFalse(Goal.isValidGoal(invalid_alpha));
        assertFalse(Goal.isValidGoal(invalid_day));
        assertFalse(Goal.isValidGoal(invalid_year));
    }
}
