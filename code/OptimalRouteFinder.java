import java.util.ArrayList;

/**
 * Class responsible for finding the optimal route using the chosen method.
 * is mostly a facade class that delegates the task of finding the optimal route to the chosen method.
 */
public class OptimalRouteFinder {
    private final int chosenMethod;
    private final ArrayList<House> houses;
    private final int NUMBER_OF_ANTS;
    private final int ITERATION_COUNT;
    private final double DEGRADATION_FACTOR;
    private final double ALPHA;
    private final double BETA;
    private final double INITIAL_PHEROMONE_INTENSITY;
    private final double Q;
    RouteFinder routeFinder = null;


    /**
     * Constructor for the OptimalRouteFinder class.
     *
     * @param houses       The list of houses to find the optimal route for.
     * @param chosenMethod The method chosen to find the optimal route,
     *                     1 for brute-force method and 2 for ant colony optimization method.
     */
    public OptimalRouteFinder(ArrayList<House> houses, int chosenMethod, int numberOfAnts, int iterationCount, double degradationFactor, double alpha, double beta, double initialPheromoneIntensity, double q) {
        this.houses = houses;
        this.chosenMethod = chosenMethod;
        this.NUMBER_OF_ANTS = numberOfAnts;
        this.ITERATION_COUNT = iterationCount;
        this.DEGRADATION_FACTOR = degradationFactor;
        this.ALPHA = alpha;
        this.BETA = beta;
        this.INITIAL_PHEROMONE_INTENSITY = initialPheromoneIntensity;
        this.Q = q;
    }

    /**
     * Finds the optimal route using the chosen method.
     *
     * @return RouteResult object containing the optimal route and the time it takes to find it.
     */
    public RouteResult findOptimalRoute() {
        if (chosenMethod == 1) {
            routeFinder = new BruteForceRouteFinder(houses);
        }
        if (chosenMethod == 2) {
            routeFinder = new AntColonyRouteFinder(houses, NUMBER_OF_ANTS, ITERATION_COUNT, DEGRADATION_FACTOR, ALPHA, BETA, INITIAL_PHEROMONE_INTENSITY, Q);
        }
        return routeFinder.findOptimalRoute();
    }

    /**
     * Prints the details of the optimal route.
     *
     * @param optimalRouteResult The optimal route to print the details of.
     */
    public void printRouteDetails(RouteResult optimalRouteResult) {
        if (chosenMethod == 1) {
            System.out.println("Method: Brute-Force Method");
        }
        if (chosenMethod == 2) {
            System.out.println("Method: Ant Colony Optimization Method");
        }
        System.out.println("Shortest distance: " + optimalRouteResult.getMinDistance());
        System.out.println("Shortest Path: " + routeFinder.buildRouteDescription(optimalRouteResult.getRoute()));
        System.out.println("Time it takes to find the shortest path: " + optimalRouteResult.getTime() / 1000.0 + " seconds");
    }
}
