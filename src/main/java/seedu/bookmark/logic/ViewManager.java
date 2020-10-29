package seedu.bookmark.logic;
import java.util.HashMap;
import java.util.Map;

import seedu.bookmark.model.history.State;

/**
 * Manages what view the {@code Ui} should be in when given a {@code State} representing the state of the {@code Model}.
 */
public class ViewManager {

    private final Map<State, ViewType> stateToViewMap = new HashMap<State, ViewType>();
    private ViewType currentView = ViewType.DEFAULT;

    /**
     * Returns the current {@code ViewType}.
     */
    public ViewType getCurrentViewType() {
        return currentView;
    }

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
