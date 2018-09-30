package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.route.Route;

/**
 * Unmodifiable view of a route book
 */
public interface ReadOnlyRouteBook {

    /**
     * Returns an unmodifiable view of the routes list.
     * This list will not contain any duplicate routes.
     */
    ObservableList<Route> getRouteList();

}
