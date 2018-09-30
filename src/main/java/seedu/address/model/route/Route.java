package seedu.address.model.route;

import java.util.Objects;

import seedu.address.model.person.Address;

/**
 * Represents a Route in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Route {

    // Identity fields
    private final Address source;
    private final Address destination;
    private final Order order;

    // Data fields
    private final Deliveryman deliveryman;

    /**
     * Creates a new instance of Route with the address of the
     * restaurant as the source address.
     * @param source The address of the restaurant.
     */
    public Route(Address source) {
        Objects.requireNonNull(source);
        this.source = source;
    }

    public Address getSource() {
        return source;
    }

    public Address getDestination() {
        return destination;
    }

    public Order getOrder() {
        return order;
    }

    public Deliveryman getDeliveryman() {
        return deliveryman;
    }

    /**
     * Adds an order to the route.
     * @param order
     */
    public void addOrder(Order order) {
        this.order = order;
        destination = order.getAddress();
    }

    /**
     * Assign the route to a deliveryman.
     * @param deliveryman
     */
    public void assignDeliveryman(Deliveryman deliveryman) {
        this.deliveryman = deliveryman;
    }

    /**
     * Returns true if both routes have all the same identity fields.
     * This defines a weaker notion of equality between two routes.
     */
    public boolean isSameRoute(Route otherRoute) {
        if (otherRoute == this) {
            return true;
        }

        return otherRoute != null
                && otherRoute.getSource().equals(getSource())
                && otherRoute.getDestination().equals(getDestination())
                && otherRoute.getOrder().equals(getOrder());
    }

    /**
     * Returns true if both routes have the same identity and data fields.
     * This defines a stronger notion of equality between two routes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Route)) {
            return false;
        }

        Route otherRoute = (Route) other;
        return otherRoute.getSource().equals(getSource())
                && otherRoute.getDestination().equals(getDestination())
                && otherRoute.getOrder().equals(getOrder())
                && otherRoute.getDeliveryman().equals(getDeliveryman());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(source, destination, order, deliveryman);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Route")
                .append(" Order: ")
                .append(getOrder())
                .append(" Address: ")
                .append(getDestination())
                .append(" Deliveryman: ")
                .append(getDeliveryman());
        return builder.toString();
    }
}
