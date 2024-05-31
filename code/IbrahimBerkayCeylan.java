import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * For CMPE160 Object-Oriented Programming course in Bogazici University.
 * Program to draw a map of houses and find the optimal route to visit all houses.
 * @author Ibrahim Berkay Ceylan, Student ID: 2023400327
 * @since Date: 06.05.2024
 */
public class IbrahimBerkayCeylan {
    public static void main(String[] args) throws FileNotFoundException {
        // defined here because assignment pdf says so. no point in defining
        // them here instead of the antcolonyroutefinder class otherwise.
        // terrible
        final int NUMBER_OF_ANTS = 75;
        final int ITERATION_COUNT = 400;
        final double DEGRADATION_FACTOR = 0.9;
        final double ALPHA = 0.8;
        final double BETA = 1.5;
        final double INITIAL_PHEROMONE_INTENSITY = 0.1;
        final double Q = 0.0001;

        int chosenMethod = 2; // 1 for bruteforce, 2 for ant colony optimization
        boolean drawPheromones = true; // works only for ant colony optimization

        final String filename = "input01.txt";

        MapDrawer mapDrawer = new MapDrawer();
        mapDrawer.setDrawPheromones(drawPheromones);
        MapManager mapManager = new MapManager(filename);
        ArrayList<House> houses = mapManager.readHouses();

        OptimalRouteFinder optimalRouteFinder = new OptimalRouteFinder(houses, chosenMethod, NUMBER_OF_ANTS, ITERATION_COUNT, DEGRADATION_FACTOR, ALPHA, BETA, INITIAL_PHEROMONE_INTENSITY, Q);
        mapDrawer.initializeCanvas();

        RouteResult optimalRouteResult = optimalRouteFinder.findOptimalRoute();
        optimalRouteFinder.printRouteDetails(optimalRouteResult);

        mapDrawer.drawRoutes(optimalRouteResult.getRoute(), optimalRouteResult.getPheromoneLevels());
        mapDrawer.drawHouses(houses);
        StdDraw.show();
    }
}
