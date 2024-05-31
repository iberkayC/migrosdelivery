/**
 * Represents a house with a location in 2D space.
 */
public class House {
    private final double x;
    private final double y;
    private final int index;
    private static int numberOfHouses = 0;

    /**
     * Creates a new house with the given location.
     *
     * @param x the x-coordinate of the house
     * @param y the y-coordinate of the house
     */
    public House(double x, double y) {
        this.x = x;
        this.y = y;
        this.index = numberOfHouses;
        numberOfHouses++;
    }

    /**
     * Returns the number of houses that have been created.
     *
     * @return the number of houses that have been created
     */
    public static int getNumberOfHouses() {
        return numberOfHouses;
    }

    /**
     * Returns the x-coordinate of the house.
     *
     * @return the x-coordinate of the house
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the house.
     *
     * @return the y-coordinate of the house
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the index of the house.
     *
     * @return the index of the house
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the Euclidean distance between this house and another house.
     *
     * @param other the other house
     * @return      the distance between this house and the other house
     */
    public double distanceTo(House other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Returns a string representation of the house.
     *
     * @return a string representation of the house
     */
    @Override
    public String toString() {
        return "House at (" + x + ", " + y + ")";
    }
}
