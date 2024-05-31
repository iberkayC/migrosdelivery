import java.util.ArrayList;

/**
 * RouteResult class to store the result of the route
 * stores the route, time taken to find the route, minimum distance of the route, and pheromone levels of the route
 * if pheromone levels are not provided, it is set to null.
 */
public class RouteResult {
    private ArrayList<House> route;
    private double time;
    private double minDistance;
    private double[][] pheromoneLevels;

    /**
     * Constructor for RouteResult without pheromone levels
     *
     * @param route       result of the route
     * @param time        time taken to find the route
     * @param minDistance minimum distance of the route
     */
    RouteResult(ArrayList<House> route, double time, double minDistance) {
        this.route = route;
        this.time = time;
        this.minDistance = minDistance;
        this.pheromoneLevels = null;
    }

    /**
     * Constructor for RouteResult
     *
     * @param route           result of the route
     * @param time            time taken to find the route
     * @param minDistance     minimum distance of the route
     * @param pheromoneLevels pheromone levels of the route
     */
    RouteResult(ArrayList<House> route, double time, double minDistance, double[][] pheromoneLevels) {
        this.route = route;
        this.time = time;
        this.minDistance = minDistance;
        this.pheromoneLevels = pheromoneLevels;
    }

    /**
     * Get the route
     *
     * @return route
     */
    public ArrayList<House> getRoute() {
        return route;
    }

    /**
     * Get the pheromone levels
     *
     * @return pheromone levels
     */
    public double[][] getPheromoneLevels() {
        return pheromoneLevels;
    }

    /**
     * Get the time
     *
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * Set the route
     *
     * @param route route
     */
    public void setRoute(ArrayList<House> route) {
        this.route = route;
    }

    /**
     * Set the minimum distance
     *
     * @return minimum distance
     */
    public double getMinDistance() {
        return minDistance;
    }

    /**
     * Set the minimum distance
     *
     * @param minDistance minimum distance
     */
    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    /**
     * Set the pheromone levels
     *
     * @param pheromoneLevels pheromone levels
     */
    public void setPheromoneLevels(double[][] pheromoneLevels) {
        this.pheromoneLevels = pheromoneLevels;
    }

    /**
     * Set the time
     *
     * @param time time
     */
    public void setTime(double time) {
        this.time = time;
    }

}
