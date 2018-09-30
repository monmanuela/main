package seedu.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.route.Route;

import java.util.function.Predicate;
import java.util.logging.Logger;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManagerRoute extends ComponentManager implements RouteModel {
    private static final Logger logger = LogsCenter.getLogger(ModelManagerRoute.class);

    private final VersionedRouteBook versionedRouteBook;
    private final FilteredList<Route> filteredRoutes;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManagerOrder(ReadOnlyRouteBook routeBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(routeBook, userPrefs);

        logger.fine("Initializing with route book: " + routeBook + " and user prefs " + userPrefs);

        versionedRouteBook = new VersionedRouteBook(routeBook);
        filteredRoutes = new FilteredList<>(versionedRouteBook.getOrderList());
    }

    public ModelManagerRoute() {
        this(new RouteBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyRouteBook newData) {
        versionedRouteBook.resetData(newData);
        indicateRouteBookChanged();
    }

    @Override
    public ReadOnlyRouteBook getRouteBook() {
        return versionedRouteBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateRouteBookChanged() {
        raise(new RouteBookChangedEvent(versionedRouteBook));
    }

    @Override
    public boolean hasRoute(Route route) {
        requireNonNull(route);
        return versionedRouteBook.hasRoute(route);
    }

    @Override
    public void deleteRoute(Route target) {
        versionedRouteBook.removeRoute(target);
        indicateRouteBookChanged();
    }

    @Override
    public void addRoute(Route route) {
        versionedRouteBook.addRoute(route);
        updateFilteredRouteList(PREDICATE_SHOW_ALL_ORDERS);
        indicateRouteBookChanged();
    }

    @Override
    public void updateRoute(Route target, Route editedRoute) {
        requireAllNonNull(target, editedRoute);
        versionedRouteBook.updateRoute(target, editedRoute);
        indicateRouteBookChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Route> getFilteredRouteList() {
        return FXCollections.unmodifiableObservableList(filteredRoutes);
    }

    @Override
    public void updateFilteredRouteList(Predicate<Route> predicate) {
        requireNonNull(predicate);
        filteredRoutes.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoRouteBook() {
        return versionedRouteBook.canUndo();
    }

    @Override
    public boolean canRedoRouteBook() {
        return versionedRouteBook.canRedo();
    }

    @Override
    public void undoRouteBook() {
        versionedRouteBook.undo();
        indicateRouteBookChanged();
    }

    @Override
    public void redoRouteBook() {
        versionedRouteBook.redo();
        indicateRouteBookChanged();
    }

    @Override
    public void commitRouteBook() {
        versionedRouteBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManagerRoute)) {
            return false;
        }

        // state check
        ModelManagerRoute other = (ModelManagerRoute) obj;
        return versionedRouteBook.equals(other.versionedRouteBook)
                && filteredRoutes.equals(other.filteredRoutes);
    }

}
