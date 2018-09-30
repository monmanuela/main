package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code RouteBook} that keeps track of its own history.
 */
public class VersionedRouteBook extends RouteBook {

    private final List<ReadOnlyRouteBook> routeBookStateList;
    private int currentStatePointer;

    public VersionedRouteBook(ReadOnlyRouteBook initialState) {
        super(initialState);

        routeBookStateList = new ArrayList<>();
        routeBookStateList.add(new RouteBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code RouteBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        routeBookStateList.add(new RouteBook(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        routeBookStateList.subList(currentStatePointer + 1, routeBookStateList.size()).clear();
    }

    /**
     * Restores the route book to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(routeBookStateList.get(currentStatePointer));
    }

    /**
     * Restores the route book to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(routeBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has route book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has route book states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < routeBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedRouteBook)) {
            return false;
        }

        VersionedRouteBook otherVersionedRouteBook = (VersionedRouteBook) other;

        // state check
        return super.equals(otherVersionedRouteBook)
                && routeBookStateList.equals(otherVersionedRouteBook.routeBookStateList)
                && currentStatePointer == otherVersionedRouteBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of routeBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of routeBookState list, unable to redo.");
        }
    }
}
