package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.route.Route;

/**
 * The API of the Model component.
 */
public interface RouteModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Route> PREDICATE_SHOW_ALL_ROUTES = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyRouteBook newData);

    /** Returns the RouteBook */
    ReadOnlyRouteBook getRouteBook();

    /**
     * Returns true if a route with the same identity as {@code route} exists in the route book.
     */
    boolean hasRoute(Route route);

    /**
     * Deletes the given route.
     * The route must exist in the route book.
     */
    void deleteRoute(Route target);

    /**
     * Adds the given route.
     * {@code route} must not already exist in the route book.
     */
    void addRoute(Route route);

    /**
     * Replaces the given route {@code target} with {@code editedRoute}.
     * {@code target} must exist in the route book.
     * The route identity of {@code editedRoute} must not be the same as another existing route in the route book.
     */
    void updateRoute(Route target, Route editedRoute);

    /** Returns an unmodifiable view of the filtered route list */
    ObservableList<Route> getFilteredRouteList();

    /**
     * Updates the filter of the filtered route list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRouteList(Predicate<Route> predicate);

    /**
     * Returns true if the model has previous route book states to restore.
     */
    boolean canUndoRouteBook();

    /**
     * Returns true if the model has undone route book states to restore.
     */
    boolean canRedoRouteBook();

    /**
     * Restores the model's route book to its previous state.
     */
    void undoRouteBook();

    /**
     * Restores the model's route book to its previously undone state.
     */
    void redoRouteBook();

    /**
     * Saves the current route book state for undo/redo.
     */
    void commitRouteBook();
}
