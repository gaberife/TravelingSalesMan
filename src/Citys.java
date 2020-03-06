import java.util.Random;

public class Citys {
    public static int col; //column
    public static int row; //row
    public static int NumCities; //Number of cities
    public static int cost;
    public static int pop[][];


    public Citys(int col, int row, int NumCities, int cost) {
        this.col= col;
        this.row = row;
        this.NumCities = NumCities;
        this.cost = cost;
    }

    //public static int getCities(){ return NumCities;}

    public static int[][] initCities(int NumCities){
        return Citys.pop = new int[NumCities][NumCities];
    }

    public static void generateRandPop(){
        Random random = new Random();
        for(int i = 0; i < Citys.NumCities; i++){
            int randPop = random.nextInt(Citys.NumCities);
            Citys.pop[i][randPop] = (randPop);
            System.out.println(Citys.pop[1][randPop]);
            //Citys.pop[i][randPop] = 10;
        }
    }

}
