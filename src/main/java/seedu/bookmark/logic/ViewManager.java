package seedu.bookmark.logic;
import java.util.LinkedHashMap;
import java.util.Map;

import seedu.bookmark.model.history.HistoryManager;
import seedu.bookmark.model.history.State;

/**
 * Manages what view the {@code Ui} should be in given a {@code State} representing the state of the {@code Model}.
 * Implementation is backed by a LRU cache using java's {@code LinkedHashMap}.
 */
public class ViewManager {

    private static final int MAX_CACHE_SIZE = HistoryManager.MAX_UNDO_COUNT;
    /**
     * Cache that maps {@code State} to a {@code ViewType}.
     * Deletes entries for states that would have been deleted to prevent excessive memory usage.
     */
    private final Map<State, ViewType> stateToViewMap = new LinkedHashMap<>() {
        protected boolean removeEldestEntry(final Map.Entry eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };
    private ViewType currentView = ViewType.DEFAULT;

    /**
     * Returns the correct {@code ViewType} for the given {@code State}.
     */
    public ViewType getViewType(State state) {
        if (stateToViewMap.get(state) == null) {
            return ViewType.DEFAULT;
        }
        return stateToViewMap.get(state);
    }

    /**
     * Sets the current {@code ViewType} to the given ViewType.
     */
    public void setCurrentView(ViewType newView) {
        currentView = newView;
    }

    /**
     * Adds a new pairing between the given {@code State} and {@code ViewType}.
     */
    public void addViewTypePairing(State state, ViewType viewType) {
        if (viewType == ViewType.MOST_RECENTLY_USED) {
            stateToViewMap.put(state, currentView);
        } else {
            stateToViewMap.put(state, viewType);
        }
    }
}
