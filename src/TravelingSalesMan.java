import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan{

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
        Citys.NumCities = Integer.parseInt(ch);
        Random random = new Random();
        Citys.initCities(Citys.NumCities);//allocating memory to array

        for(int i = 0; i < Citys.NumCities; i++){
            int randPop = random.nextInt(Citys.NumCities);
            Citys.pop[i][randPop] = (randPop);
            System.out.println(Citys.pop[1][randPop]);
        }
    }
}
