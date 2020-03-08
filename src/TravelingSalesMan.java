import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan {
    //public static ArrayList<City> city = new ArrayList<City>();
    public static City theCity = new City();

    public static void main(String[] args) {
        System.out.println("Welcome user, its time to solve a maze. Please enter the file path.");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine() + ".txt";
        File confirmedFile;
        try {
            confirmedFile = new File(String.valueOf(check(fileName))); // Variable that represents the file path
            init(confirmedFile); //Initializes the data into a table
        }catch (IOException e) {
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
        City.initCities(City.getCities()); //allocating memory to array
        theCity.table = new int[City.getCities()][City.getCities()];

        //int randPop = random.nextInt(City.getCities());
        System.out.println();
        for (int row = 0; row < City.NumCity; row++) {
            //Reads String line and removes all non numeric values from it
            String test = line.readLine().replaceAll("[^0-9.]", "");
            for(int col = 0; col < test.length(); col++){
                theCity.table[row][col] = City.convertToInt(test, col);
                System.out.print(theCity.table[row][col]);
            }
            System.out.println();
        }
    }

    public static void generateRandPop(){
        //Random random = new Random();
        System.out.println(theCity.table[0][1]);

    }
}
