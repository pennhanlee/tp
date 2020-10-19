package seedu.bookmark.model;

import java.util.ArrayDeque;

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
     * Undos the currentState, adding it to the redoDeque and sets the top of the undoDeque as the currentState.
     */
    public HistoryManager undo() {
        redoDeque.add(currentState);
        ReadOnlyLibrary newCurrentState = undoDeque.pop();
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    public HistoryManager redo() {
        ReadOnlyLibrary newCurrentState = redoDeque.pop();
        addToUndo(currentState);
        return new HistoryManager(newCurrentState, new ArrayDeque<>(undoDeque), new ArrayDeque<>(redoDeque));
    }

    private void addToUndo(ReadOnlyLibrary state) {
        while (undoDeque.size() >= MAX_UNDO_COUNT) {
            undoDeque.removeFirst();
        }
        undoDeque.add(state);
        assert(undoDeque.size() <= MAX_UNDO_COUNT);
    }
}
