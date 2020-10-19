package seedu.bookmark.model;

import java.util.ArrayDeque;
import java.util.ArrayList;

import seedu.bookmark.model.exceptions.RedoException;
import seedu.bookmark.model.exceptions.UndoException;

/**
 * Represents the history of a Library.
 */
public class HistoryManager {

    private static final int MAX_UNDO_COUNT = 10;

    private final ReadOnlyLibrary currentState;
    private final ArrayDeque<ReadOnlyLibrary> undoDeque;
    private final ArrayDeque<ReadOnlyLibrary> redoDeque;

    /**
     * Initializes a new {@code HistoryManager} with the given {@code ReadOnlyLibrary} as the current state and
     * an empty undo deque and redo deque.
     */
    public HistoryManager(ReadOnlyLibrary currentState) {
        this.currentState = currentState;
        this.undoDeque = new ArrayDeque<>();
        this.redoDeque = new ArrayDeque<>();
    }

    /**
     * Private constructor to facilitate the immutable nature of {@code HistoryManager}
     */
    private HistoryManager(ReadOnlyLibrary currentState, ArrayDeque<ReadOnlyLibrary> undoDeque,
            ArrayDeque<ReadOnlyLibrary> redoDeque) {
        this.currentState = currentState;
        this.undoDeque = undoDeque;
        this.redoDeque = redoDeque;
    }

    public ReadOnlyLibrary getCurrentState() {
        return this.currentState;
    }

    /**
     * Sets a {@code ReadOnlyLibrary} as the currentState, adding the previous currentState to the undoDeque and
     * clearing the redoDeque.
     */
    public HistoryManager addNewState(ReadOnlyLibrary state) {
        addToUndo(currentState);
        redoDeque.clear();
        return new HistoryManager(state, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    /**
     * Undoes the currentState, adding it to the redoDeque and sets the top of the undoDeque as the currentState.
     */
    public HistoryManager undo() throws UndoException {
        if (!canUndo()) {
            throw new UndoException();
        }
        redoDeque.add(currentState);
        ReadOnlyLibrary newCurrentState = undoDeque.pop();
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    /**
     * Redoes the state at the top of the redoDeque, adding the currentState back into the top of the undoDeque.
     */
    public HistoryManager redo() {
        if (!canRedo()) {
            throw new RedoException();
        }
        ReadOnlyLibrary newCurrentState = redoDeque.pop();
        addToUndo(currentState);
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    private boolean canUndo() {
        return undoDeque.size() > 0;
    }

    private boolean canRedo() {
        return redoDeque.size() > 0;
    }

    /**
     * Add a state to the undo deque, removes the oldest states to make space if necessary.
     */
    private void addToUndo(ReadOnlyLibrary state) {
        while (undoDeque.size() >= MAX_UNDO_COUNT) {
            undoDeque.removeFirst();
        }
        undoDeque.add(state);
        assert(undoDeque.size() <= MAX_UNDO_COUNT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof HistoryManager) {
            HistoryManager otherHistory = (HistoryManager) other;
            return currentState.equals(otherHistory.currentState)
                    && compareHistory(otherHistory);
        }
        return false;
    }

    /**
     * Helper method to compare the undoDeque and redoDeque as {@code equals()} is not overridden in java's ArrayDeque.
     */
    private boolean compareHistory(HistoryManager otherHistory) {
        boolean undoEquals = new ArrayList<>(undoDeque).equals(new ArrayList<>(otherHistory.undoDeque));
        boolean redoEquals = new ArrayList<>(redoDeque).equals(new ArrayList<>(otherHistory.redoDeque));
        return undoEquals && redoEquals;
    }
}
