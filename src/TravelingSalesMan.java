import java.util.*;
import java.io.*;
import java.io.File;

public class TravelingSalesMan {
    public static void main(String[] args) {
        System.out.println("Welcome user, its time to solve a maze. Please enter the file path.");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine() + ".txt";
        File confirmedFile;
        try {
            confirmedFile = new File(String.valueOf(check(fileName)));
            // Variable that represents the file path
            //init(confirmedFile);
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
/*
    public static void init(File file) throws IOException {
        Maze theMaze = new Maze();
        BufferedReader line = new BufferedReader(new FileReader(file));
        String ch = line.readLine();
        //Isolates the first line which shows the values of rows and columns
        String[] splt = ch.split(" ", 0);
        //Supposed to split the two string values into a string array
        numCol = Integer.parseInt(splt[1]);
        numRow = Integer.parseInt(splt[0]);
        //Assigns appropriate Column and Row values based on whichever maze file is called

        theMaze.maze = new char[numCol][numRow];
        for (int y = 0; y < numRow; y++) {
            String test = line.readLine();
            for (int x = 0; x < test.length(); x++) {
                theMaze.maze[x][y] = test.charAt(x);
            }
        }
        //To assign data from the file to each dimension of the array
        //2d-array intended to represent the maze/

        //Finds the start Position and places marker there
        for (int y = 0; y < numRow; y++) {
            if (' ' == (theMaze.maze[0][y])) {
                theMaze.start = new Position(0, y);
            }
        }
        //Finds the end Position
        for (int y = 0; y < numRow; y++) {
            if (' ' == theMaze.maze[numCol - 1][y]) {
                theMaze.end = new Position(numCol - 1, y);
            }
        }
        mazeSolver(theMaze);
    }

 */

}
