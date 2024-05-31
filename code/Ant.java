import java.util.ArrayList;

/**
 * Class that represents an ant, which is used to traverse the houses in the map
 * and find the optimal route using the ant colony optimization method.
 */
public class Ant {
    private ArrayList<House> travelledHouses;

    /**
     * Constructor for the Ant class.
     * @param startingHouse The house where the ant starts its journey.
     */
    public Ant(House startingHouse) {
        travelledHouses = new ArrayList<>();
        travel(startingHouse);
    }

    /**
     * Get the list of houses that the ant has visited.
     * @return The list of houses that the ant has visited.
     */
    public ArrayList<House> getTravelledHouses() {
        return travelledHouses;
    }

    /**
     * Get the total length of the route that the ant has travelled.
     * @return The total length of the route that the ant has travelled.
     */
    public double getRouteLength() {
        return RouteFinder.calculateDistanceOfRoute(travelledHouses);
    }

    /**
     * Reset the route of the ant.
     */
    public void resetRoute() {
        travelledHouses.clear();
    }

    /**
     * Get the current house that the ant is at.
     * @return The current house that the ant is at.
     */
    public House getCurrentHouse() {
        return travelledHouses.getLast();
    }

    /**
     * Move the ant to the next house in the route.
     * @param nextHouse The next house to travel to.
     */
    public void travel(House nextHouse) {
        travelledHouses.add(nextHouse);
    }
}
