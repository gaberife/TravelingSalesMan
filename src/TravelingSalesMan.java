import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan{

    public static City theCity = new City();
    public static LinkedList<Integer> trackColNum = new LinkedList<Integer>();

    public static void main(String[] args) {
        System.out.println("Welcome user, its time to solve a maze. Please enter the file path.");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine() + ".txt";
        File confirmedFile;
        try {
            confirmedFile = new File(String.valueOf(check(fileName))); // Variable that represents the file path
            init(confirmedFile); //Initializes the data into a table
            generateRandPop(); // Generates a random population
            info(); //Returns information about the population
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
        for (int row = 0; row < City.NumCity; row++) {
            //Reads String line and removes all non numeric values from it
            String test = line.readLine().replaceAll("[^0-9.]", "");
            for(int col = 0; col < test.length(); col++){
                theCity.table[row][col] = City.convertToInt(test, col);
            }
        }
    }

    public static void generateRandPop() {
        Random random = new Random();
        theCity.pop = new int[City.getCities()];
        int row = 0;
        while(row < City.NumCity){
            int randomColumnNumber = random.nextInt(City.getCities());
            if (!theCity.checkIfZeroCost(row, randomColumnNumber)){
                if (!theCity.checkRepeats(trackColNum,randomColumnNumber)) {
                    theCity.pop[row] = theCity.table[row][randomColumnNumber];
                    trackColNum.add(randomColumnNumber);
                    row++;
                }
            }
        }
    }

    public static void geneticAlgorithm(){
        info();

    }

    public static void info(){
        System.out.println();
        theCity.printCurrentPopulation(trackColNum);
        System.out.println();
        System.out.println("Total Cost:\t" + theCity.calcTotalCost());
        System.out.println();
        System.out.println("Row w/ HC: \t" + theCity.returnHighestCostRow());
        System.out.println("HC:        \t" + theCity.returnHighestCost());
        System.out.println("2ndHC Row: \t" + theCity.returnNextHighestCostRow());
        System.out.println("2ndHC:    \t" + theCity.returnNextHighestCost());
    }
}
