import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan{
    //public static ArrayList<City> city = new ArrayList<City>();
    public static City theCity = new City();

    public static void main(String[] args) {
        System.out.println("Welcome user, its time to solve a maze. Please enter the file path.");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine() + ".txt";
        File confirmedFile;
        try {
            confirmedFile = new File(String.valueOf(check(fileName)));
            // Variable that represents the file path
            init(confirmedFile);
            //Initializes the data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File check(String fileName) throws IOException {
        File file = new File(fileName);
        //Creates instance of file object
        BufferedReader print;
        String absolute = file.getAbsolutePath();
        // Finds absolute path of file object
        if (!file.exists()) {
            //Determines if file is valid, if not returns error message and kills program
            System.out.println("Error, file does not exist.");
            System.exit(0);
        } else {
            //Else prints the maze to the terminal
            print = new BufferedReader(new FileReader(absolute));
            String line;
            while (null != (line = print.readLine())) {
                System.out.println(line);
            }
        }
        return file;
    }

    public static void init(File file) throws IOException {
        BufferedReader line = new BufferedReader(new FileReader(file));
        //Isolates the String first line which shows N number of cities
        String ch = line.readLine();
        City.NumCity = Integer.parseInt(ch);
        Random random = new Random();

        City.initCities(City.getCities());//allocating memory to array
        theCity.pop = new int[City.getCities()][City.getCities()];
        int limit = City.getCities();
        //Reads in the values, creating a random Population
        for (int row = 0; row < City.getCities(); row++) {
            String test = line.readLine();
            int randPop = random.nextInt(limit);
            for (int col = 0; col < City.getCities(); col++) {
                theCity.pop[row][randPop] = test.charAt(col);
            }
            System.out.println("Assigned Value @ Col: " + City.pop[row][randPop]);
        }
    }
}
