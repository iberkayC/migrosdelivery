import java.util.ArrayList;

/**
 * subclass of RouteFinder and is responsible for finding the optimal route using the brute-force method.
 */
public class BruteForceRouteFinder extends RouteFinder {

    /**
     * Constructor for the BruteForceRouteFinder class.
     *
     * @param houses The list of houses to find the optimal route for.
     */
    public BruteForceRouteFinder(ArrayList<House> houses) {
        super(houses);
    }

    /**
     * Finds the optimal route using the brute-force method.
     *
     * @return The optimal route and the time it takes to find it.
     */
    @Override
    protected RouteResult findOptimalRoute() {
        double startTime = System.currentTimeMillis();
        ArrayList<House> optimalRoute = new ArrayList<>(houses);
        double[] minDistance = new double[]{Double.MAX_VALUE};

        calculateMinDistancePermutations(houses, 1, optimalRoute, minDistance);
        double endTime = System.currentTimeMillis();
        return new RouteResult(optimalRoute, endTime - startTime, minDistance[0]);
    }

    /**
     * Calculates the minimum distance of all permutations of the houses.
     *
     * @param current      The current list of houses.
     * @param start        The starting index.
     * @param optimalRoute The optimal route.
     * @param minDistance  The minimum distance.
     */
    private void calculateMinDistancePermutations(ArrayList<House> current, int start,
                                                  ArrayList<House> optimalRoute, double[] minDistance) {
        if (start >= House.getNumberOfHouses()) {
            double currentDistance = calculateDistanceOfRoute(current);
            if (currentDistance < minDistance[0]) {
                minDistance[0] = currentDistance;
                optimalRoute.clear();
                optimalRoute.addAll(new ArrayList<>(current));
            }
        } else {
            for (int i = start; i < House.getNumberOfHouses(); i++) {
                swapElements(current, i, start);
                calculateMinDistancePermutations(current, start + 1, optimalRoute, minDistance);
                swapElements(current, i, start);
            }
        }
    }

    /**
     * Swaps two elements in the list.
     *
     * @param arr ArrayList of houses.
     * @param i   The first index.
     * @param j   The second index.
     */
    private void swapElements(ArrayList<House> arr, int i, int j) {
        House temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}
