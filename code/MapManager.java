import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Class for reading houses from a file.
 */
public class MapManager {
    String filename;

    /**
     * Constructor for the MapManager class.
     *
     * @param filename the name of the file to read from
     */
    public MapManager(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the houses from the file.
     *
     * @return an ArrayList of House objects
     */
    public ArrayList<House> readHouses() throws FileNotFoundException {
        File input = new File(filename);
        Scanner scanner = new Scanner(input);
        ArrayList<House> houses = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            double x = Double.parseDouble(tokens[0]);
            double y = Double.parseDouble(tokens[1]);

            houses.add(new House(x, y));
        }
        scanner.close();
        return houses;
    }
}
