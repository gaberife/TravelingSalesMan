
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class City {
    public static int col; //column
    public static int row; //row
    public static int NumCity; //Number of cities
    public static int cost;
    public static int pop[];
    public static int table[][];

    public static int getCities(){ return NumCity;} //Mutator Methods

    public static int[][] initCities(int NumCity){
        return City.table = new int[NumCity][NumCity];
    } //Mutator Methods

    public static int convertToInt(String line, int column)  throws IOException {
        char ch = line.charAt(column);
        int number = Character.getNumericValue(ch);
        return number;
    } //Mutator Method

    public static boolean checkRepeats(LinkedList tracker, int randomColumnNumber){
        //If Linked list does not contain the random Number returns False, else returns true
        if(!tracker.contains(randomColumnNumber))
                return false;
        return true;
    } //Accessor Method

    public static boolean checkIfZeroCost(int row, int randomColumnNumber){
        //If the cost of the amount does not equals 0, returns true
        if(City.table[row][randomColumnNumber] == 0)
                return true;
        return false;
    } //Accessor Method

    public static int calcTotalCost(){
        int totalCost= 0;
        for(int i = 0; i < NumCity; i++)
            totalCost = totalCost + pop[i];
        return totalCost;
    } //Accessor Method

    public static int returnHighestCostRow(){
        int highestCost = 0;
        int highestCostRow = 0;
        int i = 0;
        while(i < NumCity){
           if(highestCost < pop[i]){
               highestCost = pop[i];
               highestCostRow = i;
           }
            i++;
        }
        return highestCostRow;
    } //Accessor Method

    public static int returnHighestCost(){
        int highestCost = 0;
        int i = 0;
        while(i < NumCity){
            if(highestCost < pop[i]){
                highestCost = pop[i];
            }
            i++;
        }
        return highestCost;
    } //Accessor Method

    public static int indexOfSmallest(){
        int index = 0;
        int min = pop[index];
        for (int i = 1; i < pop.length; i++){
            if (pop[i] <= min){
                min = pop[i];
                index = i;
            }
        }
        return index;
    } //Accessor Method

    public static int returnNextHighestCostRow() {
        int largest, secondLargest, largestRow, secondLargestRow;
        largest = secondLargest = Collections.min(Arrays.asList(City.returnHighestCostRow())); //first
        largestRow = secondLargestRow  = indexOfSmallest();
        for (int i = 0; i < NumCity; i++)
            if (pop[i] > largest) {
                secondLargest = largest;
                secondLargestRow = largestRow;
                largestRow = i;
                largest = pop[i];
            } else if (pop[i] > secondLargest && pop[i] != largest) {
                secondLargest = pop[i];
                secondLargestRow = i;
            }
        return secondLargestRow;
    } //Accessor Method

    public static int returnNextHighestCost() {
        int largest, secondLargest;
                //secondRow;
        largest = secondLargest = Collections.min(Arrays.asList(City.returnHighestCostRow())); //first
        //secondRow = 0;
        for (int i = 0; i < NumCity; i++)
            if (pop[i] > largest) {
                secondLargest = largest;
                //secondRow = i;
                largest = pop[i];
            } else if (pop[i] > secondLargest && pop[i] != largest) {
                secondLargest = pop[i];
                //secondRow = i;
            }
        return secondLargest;
    } //Accessor Method

    public static void switchMethod(int highest, int secondHighest, LinkedList tracker){

    }

    public static void printCurrentPopulation(LinkedList tracker){
        for(int i = 0; i < NumCity; i++){
            System.out.println("The cost of " + i + "," + tracker.get(i) + " is " + City.pop[i]);
        }
    }

}
