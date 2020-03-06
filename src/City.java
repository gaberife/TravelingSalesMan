import java.util.Random;

public class City {
    public static int col; //column
    public static int row; //row
    public static int NumCity; //Number of cities
    public static int cost;
    public static int pop[][];


    public City() {

    }

    public static int getCities(){ return NumCity;}

    public static int[][] initCities(int NumCity){
        return City.pop = new int[NumCity][NumCity];
    }

    public static void generateRandPop(){
        Random random = new Random();
        int i = 0;
        while(i < City.NumCity){
            int randPop = random.nextInt(City.NumCity);
            City.pop[i][randPop] = (randPop);
            System.out.println(City.pop[1][randPop]);
            //Citys.pop[i][randPop] = 10;
        }
    }
    public static boolean checkRepeats(int y){ //checks for y'i repeats within the array
        for(int i = 0; i < City.NumCity; i++){
            for(int j = 0; j < City.NumCity; j++)
            if(City.pop[i][j] != City.pop[i][y]){
                return true;
            }
        }
        return false;
    }

    public static boolean checkZeroCost(int x, int y){ // Checks if cost is zero
        if(City.pop[x][y] != 0)
            return true;
        return false;
    }
}
