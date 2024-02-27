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
                data += read.next() + " ";
            }
            String seperated[] = data.split("\\s+");
            for (int i = 0; i < pSize; i++){
                for (int j = 0; j < pSize; j++){
                    tempInt = Integer.parseInt(seperated[count]);
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
        Node root = new Node(initial, initial.manhattan());
        PriorityQueue<Node> pq = new PriorityQueue<>(new mhPrioCmp());
        List <Board> n;
        boolean dupe = false;
        List <Board> tried = new ArrayList<>();
        pq.add(root);
        Node current;

        while (!pq.isEmpty()){
            current = pq.poll();
            current.printSelf();
            System.out.println(current.h);
            if (current.isGoal()) {
                current.path();
                System.out.println("Solved in " + current.g + " moves!");
                break;
            }
            n = (List<Board>) current.getnBoard().neighbors();
            for (Board b: n){
                dupe = false;
                for (int i = 0; i < tried.size(); i++){
                    if (b.checkEQ(b, tried.get(i))){
                        dupe = true;
                        break;
                    }
                }
                if (!dupe){
                    pq.add(new Node(b, b.manhattan(), current));
                }
            }
            tried.add(current.nBoard);
            }
        System.out.println("A* solver finished");
        }

    static void divAndConqSolver (Board initial){
        Node root = new Node(initial, initial.manhattan());
        PriorityQueue<Node> pq = new PriorityQueue<>(new mhPrioCmp());
        List <Board> n;
        Scanner scan = new Scanner(System.in);
        int DCtop[] = new int [4];
        int currentTop[] = new int[4];
        int DCside [][] = new int[1][4];
        int currentSide[][] = new int [1][4];
        boolean DCgoal = false;
        boolean dupe = false;
        List <Board> tried = new ArrayList<>();
        pq.add(root);
        Node current;
        for (int i = 0; i<3; i++){
            DCtop[i] = i+1;
        }
        DCside[0][0] = 1;
        for (int i = 1; i<3; i++){
            DCside[0][i] = i+4;
        }

        while (!(pq.isEmpty())){
            current = pq.poll();
            for (int i = 0; i<3; i++) {
                currentTop[i] = current.nBoard.tiles[0][i];
            }
            for (int i = 0; i<3; i++) {
                if (Arrays.equals(DCtop, currentTop)) {
                    DCgoal = true;
                    root = current;
                    break;
                }
            }
            if(DCgoal){
                pq.clear();
                break;
            }
            n = (List<Board>) current.getnBoard().neighbors();
            for (Board b: n){
                dupe = false;
                for (int i = 0; i < tried.size(); i++){
                    if (b.checkEQ(b, tried.get(i))){
                        dupe = true;
                        break;
                    }
                }
                if (!dupe){
                    pq.add(new Node(b, b.manhattan(), current));
                }
            }
            tried.add(current.nBoard);
        }
        System.out.print("Found top");
        DCgoal = false;

        pq.add(root);
        while (!(pq.isEmpty())){
            current = pq.poll();
            for (int i = 0; i<3; i++) {
                currentSide[0][i] = current.nBoard.tiles[0][i];
            }
            for (int i = 0; i<3; i++) {
                if (Arrays.equals(DCside, currentSide)) {
                    DCgoal = true;
                    root = current;
                    break;
                }
            }
            if(DCgoal){
                pq.clear();
                break;
            }
            n = (List<Board>) current.getnBoard().neighbors();
            for (Board b: n){
                dupe = false;
                for (int i = 0; i < tried.size(); i++){
                    if (b.checkEQ(b, tried.get(i))){
                        dupe = true;
                        break;
                    }
                }
                if (!dupe){
                    pq.add(new Node(b, b.manhattan(), current));
                }
            }
            tried.add(current.nBoard);
        }
        System.out.println("Found side");
        pq.add(root);
        while (!pq.isEmpty()){
            current = pq.poll();
            if (current.isGoal()) {
                current.path();
                System.out.println("Solved in " + current.g + " moves!");
                break;
            }
            n = (List<Board>) current.getnBoard().neighbors();
            for (Board b: n){
                dupe = false;
                for (int i = 0; i < tried.size(); i++){
                    if (b.checkEQ(b, tried.get(i))){
                        dupe = true;
                        break;
                    }
                }
                if (!dupe){
                    pq.add(new Node(b, b.manhattan(), current));
                }
            }
            tried.add(current.nBoard);
        }
        System.out.println("Divide and Conquer solver finished");

    }


}
