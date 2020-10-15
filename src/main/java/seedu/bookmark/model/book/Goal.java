package seedu.bookmark.model.book;

import java.time.LocalDate;

public class Goal {
    public static final String MESSAGE_CONSTRAINTS =
            "Goals should only contain numeric characters for page, and Date format DD/MM/YYYY for a deadline" +
                    ", and it should not be blank";
    /*
        Goal must be in the format: [page] [deadline]
        Deadline must be specified as dd/mm/yyyy
        Both fields must not be empty.
     */
    public static final String VALIDATION_REGEX = "\\d+\\s\\d{2}/\\d{2}/\\d{4}";
    public static final String DEADLINE_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    public final String page;
    public final String deadline;

    /**
     * Construct a {@code Goal}.
     */
    public Goal(String page, String deadline) {
        this.page = page;
        this.deadline = deadline;
    }

    public static Goal defaultGoal() { // constructor for default goal
        return new Goal("0", "31/12/9999");
    }

    public static LocalDate parseDeadline(String deadline) {
        String[] parts = deadline.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return LocalDate.of(year, month, day);
    }

    private LocalDate getDeadline() {
        return parseDeadline(deadline);
    }

    private int getPage() {
        return Integer.parseInt(page);
    }

    public boolean isOverdue() {
        LocalDate now = LocalDate.now();
        return parseDeadline(deadline).isBefore(now); // apparently this returns the wrong thing...
    }

    /**
     * Returns if a given string is a valid genre.
     */
    public static boolean isValidGoal(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Goal) {
            String otherPage = ((Goal) other).page;
            String otherDeadline = ((Goal) other).deadline;

            return deadline.equals(otherDeadline) && page.equals(otherPage);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s %s", page, deadline);
    }

}
