package seedu.bookmark.model;

public class VersionedLibrary extends Library {

    private HistoryManager historyManager;

    public VersionedLibrary(ReadOnlyLibrary initialState) {
        super(initialState);
        this.historyManager = new HistoryManager(initialState);
    }
}
