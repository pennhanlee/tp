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
    public static final String VALIDATION_REGEX = "[\\d+] [\\d{2}/\\d{2}/\\d{4}]";

    public final String page;
    public final String deadline;

    /**
     * Construct a {@code Goal}.
     */
    public Goal(String page, String deadline) {
        this.page = page;
        this.deadline = deadline;
    }

    private static LocalDate parseDeadline(String deadline) {
        String[] parts = deadline.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return LocalDate.of(year, month, day);
    }

    public static boolean isOverdue(String deadline) {
        LocalDate now = LocalDate.now();
        return now.isBefore(parseDeadline(deadline));
    }

    public static boolean isValidGoal(String test) {
        return test.matches(VALIDATION_REGEX) && !isOverdue(test);
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

}
