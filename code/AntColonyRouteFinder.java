import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *  subclass of RouteFinder that uses the Ant Colony Optimization algorithm to find the optimal route.
 */
public class AntColonyRouteFinder extends RouteFinder {
    private final int ITERATION_COUNT;
    private final int NUMBER_OF_ANTS;
    private final double DEGRADATION_FACTOR;
    private final double ALPHA;
    private final double BETA;
    private final double INITIAL_PHEROMONE_INTENSITY;
    private final double Q;
    private double[][] pheromones;
    ArrayList<Ant> ants = new ArrayList<>();
    private double minDistance = Double.MAX_VALUE;
//    private double[][] bestRoutePheromones = new double[House.getNumberOfHouses()][House.getNumberOfHouses()];
    Random random = new Random();

    /**
     * Constructor for the AntColonyRouteFinder class.
     *
     * @param houses The list of houses to find the optimal route for.
     */
    public AntColonyRouteFinder(ArrayList<House> houses, int numberOfAnts, int iterationCount, double degradationFactor, double alpha, double beta, double initialPheromoneIntensity, double q) {
        super(houses);
        this.NUMBER_OF_ANTS = numberOfAnts;
        this.ITERATION_COUNT = iterationCount;
        this.DEGRADATION_FACTOR = degradationFactor;
        this.ALPHA = alpha;
        this.BETA = beta;
        this.INITIAL_PHEROMONE_INTENSITY = initialPheromoneIntensity;
        this.Q = q;
        // initialize pheromones
        pheromones = new double[House.getNumberOfHouses()][House.getNumberOfHouses()];
        for(int i = 0; i < House.getNumberOfHouses(); i++) {
            Arrays.fill(pheromones[i], INITIAL_PHEROMONE_INTENSITY);
        }
    }

    /**
     * Finds the optimal route using the Ant Colony Optimization algorithm.
     *
     * @return The optimal route and the time it takes to find it.
     */
    @Override
    protected RouteResult findOptimalRoute() {
        double startTime = System.currentTimeMillis();
        antColonyOptimization();
        double endTime = System.currentTimeMillis();
        return new RouteResult(houses, endTime - startTime, calculateDistanceOfRoute(houses), pheromones);
    }

    /**
     * Runs the Ant Colony Optimization algorithm.
     */
    private void antColonyOptimization() {
        initializeAnts();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            for (Ant ant : ants) {
                antCycle(ant);
            }
            updatePheromones(ants);
//            if (updateBestRoute()) {
//                System.out.println("New optimal route found: " + minDistance + " at iteration " + i);
//            }
            updateBestRoute();
        }
    }

    /**
     * Updates the best route if a new optimal route is found.
     *
     * @return true if a new optimal route is found, false otherwise.
     */
    private boolean updateBestRoute() {
        boolean foundNewBest = false;
        for (Ant ant : ants) {
            if (ant.getRouteLength() < minDistance) {
                minDistance = ant.getRouteLength();
                houses = new ArrayList<>(ant.getTravelledHouses());
//                bestRoutePheromones = pheromones.clone();
                foundNewBest = true;
            }
        }
        return foundNewBest;
    }

    /**
     * Updates the pheromones based on the routes taken by the ants.
     *
     * @param ants The list of ants.
     */
    private void updatePheromones(ArrayList<Ant> ants) {
        for (int i = 0; i < House.getNumberOfHouses(); i++) {
            for (int j = 0; j < House.getNumberOfHouses(); j++) {
                pheromones[i][j] *= DEGRADATION_FACTOR; // evaporation
            }
        }
        for (Ant ant : ants) {
            double pheromoneToAdd = Q / ant.getRouteLength();
            ArrayList<House> route = ant.getTravelledHouses();
            for (int i = 0; i < route.size() - 1; i++) {
                int fromIndex = route.get(i).getIndex();
                int toIndex = route.get(i + 1).getIndex();
                pheromones[fromIndex][toIndex] += pheromoneToAdd; // add pheromone to the edge symmetrically
                pheromones[toIndex][fromIndex] += pheromoneToAdd;
            }
        }
    }

    /**
     * Initializes the ants by placing them in random houses.
     */
    private void initializeAnts() {
        for (int i = 0; i < NUMBER_OF_ANTS; i++) {
            int randomIndex = random.nextInt(House.getNumberOfHouses());
            ants.add(new Ant(houses.get(randomIndex)));
        }
    }

    /**
     * Runs a cycle for an ant so that the ant visits all the houses, thus forming a hamiltonian cycle.
     *
     * @param ant The ant to run the cycle for.
     */
    private void antCycle(Ant ant) {
        ant.resetRoute();
        ant.travel(houses.get(random.nextInt(House.getNumberOfHouses())));
        for (int i = 0; i < House.getNumberOfHouses(); i++) {
            antRandomWalk(ant);
        }
    }

    /**
     * Makes the ant randomly walk to the next house based on the pheromones and distances.
     *
     * @param ant The ant to make the random walk for.
     */
    private void antRandomWalk(Ant ant) {
        House currentHouse = ant.getCurrentHouse();
        ArrayList<House> unvisitedHouses = new ArrayList<>(houses);
        unvisitedHouses.removeAll(ant.getTravelledHouses());

        double totalProbability = 0;
        double[] probabilities = new double[unvisitedHouses.size()];
        for (int i = 0; i < unvisitedHouses.size(); i++) {
            House house = unvisitedHouses.get(i);
            double pheromone = pheromones[currentHouse.getIndex()][house.getIndex()];
            double distance = currentHouse.distanceTo(house);
            probabilities[i] = Math.pow(pheromone, ALPHA) * Math.pow(1 / distance, BETA);
            totalProbability += probabilities[i];
        }

        double randomValue = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0;
        for (int i = 0; i < unvisitedHouses.size(); i++) {
            cumulativeProbability += probabilities[i];
            if (cumulativeProbability > randomValue) {
                ant.travel(unvisitedHouses.get(i));
                break;
            }
        }
    }
}
