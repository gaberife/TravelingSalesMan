import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan {

    public static City theCity = new City();
    public static LinkedList<Integer> trackColNum1 = new LinkedList<Integer>();
    public static LinkedList<Integer> trackColNum2 = new LinkedList<Integer>();
    public static LinkedList<Integer> temp = new LinkedList<Integer>();

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
            info();
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
        City.initCities(City.getCities()); //allocating memory to array
        theCity.table = new int[City.getCities()][City.getCities()];
        for (int row = 0; row < City.NumCity; row++) {
            //Reads String line and removes all non numeric values from it
            String test = line.readLine().replaceAll("[^0-9.]", "");
            for (int col = 0; col < test.length(); col++) {
                theCity.table[row][col] = City.convertToInt(test, col);
            }
        }
    }

    public static void generateRandPop() {
        Random random = new Random();
        int row = 0;
        while (row < City.NumCity) {
            int randomColumnNumber = random.nextInt(City.getCities());
            if (!theCity.checkIfZeroCost(row, randomColumnNumber)) {
                if (!trackColNum1.contains(randomColumnNumber)) {
                    theCity.pop.add(theCity.table[row][randomColumnNumber]);
                    temp.add(theCity.table[row][randomColumnNumber]);
                    trackColNum1.add(randomColumnNumber);
                    row++;
                }
            }
        }
        System.out.println("\nInitial Population");
        theCity.printCurrentPopulation(trackColNum1, theCity.pop);
        System.out.println("Total Cost:\t" + theCity.calcTotalCost(theCity.pop) + "\n");
    }

    public static void generateTemp() {
        trackColNum2 = (LinkedList) trackColNum1.clone();
        int HCRow, HCCol, NextHCRow, NextHCCol;
        HCRow = theCity.returnHighestCostRow();
        HCCol = theCity.returnHighestCostCol(trackColNum1);
        NextHCRow = theCity.returnNextHighestCostRow();
        NextHCCol = theCity.returnNextHighestCostCol(trackColNum1);


        for (int row = 0; row < City.NumCity; row++) {
            if (!theCity.checkIfZeroCost(row, NextHCCol)){
                if (!theCity.checkIfZeroCost(NextHCRow, HCCol)) {
                    if (row == HCRow) {
                        trackColNum2.set(row, NextHCCol);
                        temp.set(row, theCity.table[row][NextHCCol]);
                    } else if (row == NextHCRow) {
                        trackColNum2.set(NextHCRow, HCCol);
                        temp.set(NextHCRow, theCity.table[row][HCCol]);
                    } else {
                        trackColNum2.set(row, trackColNum1.get(row));
                        theCity.pop.set(row, temp.get(row));
                    }
                }
            }
        }
        System.out.println("This is the alternate Population");
        theCity.printCurrentPopulation(trackColNum2, temp);
        System.out.println("Alternate Total Cost: " + theCity.calcTotalCost(temp)+ "\n");

    }

    public static void geneticAlgorithm() {
        int iterations = 0;
        while (iterations < City.NumCity) {
            generateTemp();
            if (theCity.calcTotalCost(theCity.pop) > theCity.calcTotalCost(temp)) { //If TC of initPOP is greater than the TC of tempPOP
                for (int row = 0; row < City.NumCity; row++) {
                    trackColNum1 = (LinkedList) trackColNum2.clone();
                    theCity.pop.set(row, temp.get(row));
                    info();
                }
                iterations++;
            }
            else if(theCity.calcTotalCost(theCity.pop) < theCity.calcTotalCost(temp)){
                iterations = 25;
            }
            else{
                iterations = 25;
            }
        }
    }


    public static void info(){
        System.out.println("\nNew Population");
        theCity.printCurrentPopulation(trackColNum1, theCity.pop);
        System.out.println("New Total Cost:\t" + theCity.calcTotalCost(theCity.pop)+ "\n");
        /*
        System.out.println("Row HC:       \t" + theCity.returnHighestCostRow());
        System.out.println("Column HC:    \t" + theCity.returnHighestCostCol(trackColNum1));
        System.out.println("Highest Cost: \t" + theCity.returnHighestCost());
        System.out.println();


        System.out.println("\nStarting Population");
        theCity.printCurrentPopulation(trackColNum1, theCity.pop);
        System.out.println("Starting Cost:\t" + theCity.calcTotalCost(theCity.pop) + "\n");


        System.out.println("Row 2nd HC:   \t" + theCity.returnNextHighestCostRow());
        System.out.println("Column 2nd HC:\t" + theCity.returnNextHighestCostCol(trackColNum1));
        System.out.println("2nd HC:       \t" + theCity.returnNextHighestCost());
         */
    }
}

