import java.util.ArrayList;

/**
 * Abstract class that represents a route finder.
 */
public abstract class RouteFinder {
    protected ArrayList<House> houses;

    /**
     * Constructor for the RouteFinder class.
     *
     * @param houses the list of houses to find the optimal route for
     */
    public RouteFinder(ArrayList<House> houses) {
        this.houses = new ArrayList<>(houses);
    }

    /**
     * Abstract method to find the optimal route.
     *
     * @return the optimal route
     */
    protected abstract RouteResult findOptimalRoute();

    protected String buildRouteDescription(ArrayList<House> route) {
        StringBuilder routeDescription = new StringBuilder("[");
        if (route.getFirst().getIndex() != 0) {
            normalizeRoute(route);
        }
        for (House house : route) {
            if (routeDescription.length() > 1) {
                routeDescription.append(", ");
            }
            routeDescription.append(house.getIndex() + 1);
        }
        routeDescription.append(", ").append(route.getFirst().getIndex() + 1).append("]");
        return routeDescription.toString();
    }

    /**
     * Normalizes the route so that the house with index 0 is the first house in the route.
     *
     * @param route the route to normalize
     */
    protected void normalizeRoute(ArrayList<House> route) {
        int indexOfHouseWithIndexZero = 0;
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i).getIndex() == 0) {
                indexOfHouseWithIndexZero = i;
                break;
            }
        }
        for (int i = 0; i < indexOfHouseWithIndexZero; i++) {
            route.add(route.removeFirst());
        }
    }

    /**
     * Calculates the distance of a route.
     *
     * @param houses the list of houses to calculate the distance of the route for
     * @return       the distance of the route
     */
    protected static double calculateDistanceOfRoute(ArrayList<House> houses) {
        double distance = 0;
        for (int i = 0; i < houses.size() - 1; i++) {
            distance += houses.get(i).distanceTo(houses.get(i + 1));
        }
        distance += houses.get(House.getNumberOfHouses() - 1).distanceTo(houses.getFirst());
        return distance;
    }
}
