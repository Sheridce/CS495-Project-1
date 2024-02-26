import java.util.*;
import java.io.*;
public class main {

    public static void main(String [] args){
        String fileName = "";
        Scanner input = new Scanner(System.in);
        int pSize;
        int tempInt;
        int count = 0;
        int moves;
        char method = ' ';
        System.out.print("Input filename of puzzle to be solved: ");
        fileName = input.next();
        File f = new File(fileName);
        try{
            Scanner read = new Scanner(f);
            pSize = read.nextInt();
            int [][] puzzle = new int[pSize][pSize];
            int [][] goal = new int[pSize][pSize];
            String data = "";

            while (read.hasNext()) {
                data += read.next();
            }
            for (int i = 0; i < pSize; i++){
                for (int j = 0; j < pSize; j++){
                    tempInt = Character.getNumericValue(data.charAt(count));
                    puzzle[i][j] = tempInt;
                    count++;
                }
            }
            count = 1;
            for (int i = 0; i < pSize; i++){
                for (int j = 0; j < pSize; j++){
                    goal[i][j] = count;
                    count++;
                }
                goal[pSize-1][pSize-1] = 0;
            }

            Board b = new Board(puzzle, goal);
            System.out.print("Which solving method would you like to use? (A for A*, B for Divide and Conquer, " +
                    "any other key to quit): ");
            method = input.next().charAt(0);
            if (method == 'A' ||method == 'a'){
                aStarSolver(b);

            }
            else if (method == 'B' || method == 'b'){
                divAndConqSolver(b);
            }

        }
        catch(FileNotFoundException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }


    }

    static void aStarSolver(Board initial){

    }
    static void divAndConqSolver (Board initial){

    }
    public boolean isSolvable(){
        boolean isSolvable = false;
        return isSolvable;
    }


}
