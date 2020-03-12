import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan{

    public static City theCity = new City();
    public static LinkedList<Integer> trackColNum1 = new LinkedList<Integer>();
    public static LinkedList<Integer> trackColNum2 = new LinkedList<Integer>();


    public static void main(String[] args) {
        System.out.println("Welcome user, its time to solve a maze. Please enter the file path.");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine() + ".txt";
        File confirmedFile;
        try {
            confirmedFile = new File(String.valueOf(check(fileName))); // Variable that represents the file path
            init(confirmedFile); //Initializes the data into a table
            generateRandPop(); // Generates a random population
            geneticAlgorithm(); //Returns information about the population
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
                if (!theCity.checkRepeats(trackColNum1,randomColumnNumber)) {
                    theCity.pop[row] = theCity.table[row][randomColumnNumber];
                    trackColNum1.add(randomColumnNumber);
                    row++;
                }
            }
        }
        System.out.println("\nStarting Population");
        theCity.printCurrentPopulation(trackColNum1, theCity.pop);
        System.out.println("Starting Cost:\t" + theCity.calcTotalCost(theCity.pop) + "\n");

    }

    public static void generateTemp() {
        theCity.temp = new int[City.getCities()];
        trackColNum2 = (LinkedList) trackColNum1.clone();
        boolean legal = false;
        int HighestCostColumn = theCity.returnHighestCostCol(trackColNum1);
        int NextHighestCostColumn = theCity.returnNextHighestCostCol(trackColNum1);
        int HighestCostRow = theCity.returnHighestCostRow();
        int NextHighestCostRow = theCity.returnNextHighestCostRow();
        for(int row = 0; row < City.NumCity; row ++) {

            if(theCity.returnNextHighestCost() != 0)
                legal = true;

            if(row == HighestCostRow && legal) {
                trackColNum2.set(row, NextHighestCostColumn);
                theCity.temp[row] = theCity.table[row][NextHighestCostColumn];
            }
            else if (row == NextHighestCostRow && legal) {
                trackColNum2.set(row, HighestCostColumn);
                theCity.temp[row] = theCity.table[row][HighestCostColumn];
            }
            else{
                trackColNum2.set(row, trackColNum1.get(row));
                theCity.temp[row] = theCity.pop[row];
            }
        }
        System.out.println("Row 2nd HC:   \t" + theCity.returnNextHighestCostRow());
        System.out.println("Column 2nd HC:\t" + theCity.returnNextHighestCostCol(trackColNum1));
        System.out.println("2nd HC:       \t" + theCity.returnNextHighestCost());
    }


        public static void geneticAlgorithm(){
        boolean lowest = false;
        while(!lowest) {
            generateTemp();
            int totalCost = theCity.calcTotalCost(theCity.pop);
            int newTotalCost = theCity.calcTotalCost(theCity.temp);
            if(totalCost == City.NumCity + 1)
                lowest = true;
            if(totalCost > newTotalCost){
                for (int row = 0; row < City.NumCity; row++) {
                    theCity.pop[row] = theCity.temp[row];
                    trackColNum1.set(row, trackColNum2.get(row));
                }
            }
            info();
        }
    }

    public static void info(){
        System.out.println("\nNew Population");
        theCity.printCurrentPopulation(trackColNum1, theCity.pop);
        System.out.println("New Total Cost:\t" + theCity.calcTotalCost(theCity.temp)+ "\n");
        /*
        System.out.println("Row HC:       \t" + theCity.returnHighestCostRow());
        System.out.println("Column HC:    \t" + theCity.returnHighestCostCol(trackColNum1));
        System.out.println("Highest Cost: \t" + theCity.returnHighestCost());
        System.out.println();
        System.out.println("Row 2nd HC:   \t" + theCity.returnNextHighestCostRow());
        System.out.println("Column 2nd HC:\t" + theCity.returnNextHighestCostCol(trackColNum1));
        System.out.println("2nd HC:       \t" + theCity.returnNextHighestCost());
         */
    }
}

