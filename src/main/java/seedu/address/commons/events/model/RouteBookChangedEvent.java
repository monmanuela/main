package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyRouteBook;

/** Indicates the RouteBook in the model has changed*/
public class RouteBookChangedEvent extends BaseEvent {

    public final ReadOnlyRouteBook data;

    public RouteBookChangedEvent(ReadOnlyRouteBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of routes " + data.getRouteList().size();
    }
}
