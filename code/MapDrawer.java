import java.awt.Font;
import java.util.ArrayList;

/**
 * Class to draw the map and the routes.
 */
public class MapDrawer {
    int width = 600;
    int height = 600;
    boolean drawPheromones;

    /**
     * Initializes the canvas.
     */
    public void initializeCanvas() {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 8));
    }

    /**
     * Draws the houses on the canvas.
     *
     * @param houses the houses to draw
     */
    public void drawHouses(ArrayList<House> houses) {
        for (House house : houses) {
            drawHouse(house);
        }
        StdDraw.show();
    }

    /**
     * Draws a house on the canvas.
     *
     * @param house the house to draw
     */
    public void drawHouse(House house) {
        StdDraw.setPenColor(house.getIndex() == 0 ? StdDraw.PRINCETON_ORANGE : StdDraw.LIGHT_GRAY);
        StdDraw.filledCircle(house.getX(), house.getY(), 0.015);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(house.getX(), house.getY(), (house.getIndex() + 1)  + "");
    }

    /**
     * Sets whether to draw the pheromones or not.
     * @param drawPheromones whether to draw the pheromones or not
     */
    public void setDrawPheromones(boolean drawPheromones) {
        this.drawPheromones = drawPheromones;
    }

    /**
     * Draws the routes on the canvas.
     *
     * @param houses     the houses
     * @param pheromones the pheromones to determine thickness
     */
    public void drawRoutes(ArrayList<House> houses, double[][] pheromones) {
        if (pheromones != null && drawPheromones) {
            minMaxNormalization(pheromones); // normalizing because it looks cooler
            for (int i = 0; i < House.getNumberOfHouses(); i++) {
                for (int j = i + 1; j < House.getNumberOfHouses(); j++) {
                    if (pheromones[i][j] > 0) {
                        drawRoute(houses.get(i), houses.get(j), (0.006 * pheromones[i][j]));
                    }
                }
            }
        } else {
            for (int i = 0; i < House.getNumberOfHouses() - 1; i++) {
                drawRoute(houses.get(i), houses.get(i + 1), pheromones != null ? 0.015 : 0.006);
            }
            drawRoute(houses.get(House.getNumberOfHouses() - 1), houses.getFirst(), pheromones != null ? 0.015 : 0.006);
        }
    }


    /**
     * Normalizes the pheromones for better visualization.
     *
     * @param pheromones the pheromones to normalize
     */
    public void minMaxNormalization(double[][] pheromones) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < House.getNumberOfHouses(); i++) {
            for (int j = i + 1; j < House.getNumberOfHouses(); j++) {
                if (pheromones[i][j] < min) {
                    min = pheromones[i][j];
                }
                if (pheromones[i][j] > max) {
                    max = pheromones[i][j];
                }
            }
        }
        for (int i = 0; i < House.getNumberOfHouses(); i++) {
            for (int j = i + 1; j < House.getNumberOfHouses(); j++) {
                pheromones[i][j] = (pheromones[i][j] - min) / (max - min);
            }
        }
    }


    /**
     * Draws a route on the canvas.
     *
     * @param house1 the first house
     * @param house2 the second house
     * @param thickness the thickness of the route
     */
    public void drawRoute(House house1, House house2, double thickness) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(thickness);
        StdDraw.line(house1.getX(), house1.getY(), house2.getX(), house2.getY());
    }
}
