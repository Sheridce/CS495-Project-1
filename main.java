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
                aStarSolver(b, goal);

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
    static void aStarSolver(Board initial, int[][] goal){
        PriorityQueue<Board> pq = new PriorityQueue<>(new mhPrioCmp());
        HashMap<Integer, Board> gameTree = new HashMap<>();
        List<Board> n;
        String tempStr;
        Scanner input = new Scanner(System.in);
        int quit = 0;
        int tempInt;
        int count = 0;
        int moves = 0;
        int [][] currentState = new int[initial.dimension()][initial.dimension()];

        pq.add(initial);
            while (!Arrays.deepEquals(currentState, goal)) {
                initial = pq.poll();
                if (gameTree.containsKey(moves - 1)) {
                    tempStr = gameTree.get(moves-1).toString();
                    //System.out.println(tempStr);
                    if (gameTree.get(moves - 1).toString() == initial.toString()) {
                        continue;
                    }
                }
                System.out.println(initial.toString());
                tempStr = initial.toString();
                tempStr = tempStr.replace("\n", "");
                tempStr = tempStr.replace(" ", "");
                for (int i = 0; i < initial.dimension(); i++) {
                    for (int j = 0; j < initial.dimension(); j++) {
                        tempInt = Character.getNumericValue(tempStr.charAt(count));
                        currentState[i][j] = tempInt;
                        count++;
                    }
                }
                count = 0;
                moves++;
                gameTree.put(moves, new Board(currentState, goal));
                n = (List<Board>) initial.neighbors(moves);
                pq.addAll(n);
                //input.nextLine();
        }



    }
    static void divAndConqSolver (Board initial){

    }
    public boolean isSolvable(){
        boolean isSolvable = false;
        return isSolvable;
    }


}
