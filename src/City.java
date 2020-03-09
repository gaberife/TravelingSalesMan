import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.lang.Object;
import java.util.Arrays;

public class City {
    public static int col; //column
    public static int row; //row
    public static int NumCity; //Number of cities
    public static int cost;
    public static int pop[];
    public static int table[][];



    public City() {

    }

    public static int getCities(){ return NumCity;}

    public static int[][] initCities(int NumCity){
        return City.table = new int[NumCity][NumCity];
    }

    public static void getPop(){
        for(int row = 0; row < City.NumCity; row++) {
            System.out.println(City.table[row][0]);
            //System.out.println();
        }
    }
    public static boolean checkRepeats(int[] tracker, int randomColumnNumber){ //checks for y'i repeats within the array
        for(int i = 0; i < City.NumCity; i++){
            if(tracker[i] == randomColumnNumber)
                return false;
        }
        return true;
    }

    public static boolean checkZeroCost(int row, int randomColumnNumber){ //Checks if cost is zero
        if(City.table[row][randomColumnNumber] != 0)
                return true;
        return false;
    }

    public static int convertToInt(String line, int column)  throws IOException {
        char ch = line.charAt(column);
        int number = Character.getNumericValue(ch);
        return number;
    }
}
