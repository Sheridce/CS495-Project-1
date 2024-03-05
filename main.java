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
            //current.printSelf();
            //System.out.println(current.h);
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
        Node root = new Node(initial, initial.mhTop());
        PriorityQueue<Node> pq = new PriorityQueue<>(new mhPrioCmp());
        List <Board> n;
        Scanner scan = new Scanner(System.in);

        boolean DCgoal = false;
        boolean dupe = false;
        List <Board> tried = new ArrayList<>();
        pq.add(root);
        Node current;

        while (!(pq.isEmpty())){
            current = pq.poll();
            for (int i = 0; i<3; i++) {
                if (Arrays.equals(current.nBoard.topGoal, current.nBoard.currentTop)) {
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
        System.out.println("Found top");
        DCgoal = false;
        if(root.parent == null){
            root = new Node(root.nBoard, root.nBoard.mhSide());
        }
        else{
            root = new Node(root.nBoard, root.nBoard.mhSide(), root.parent);
        }
        pq.add(root);
        while (!(pq.isEmpty())){
            current = pq.poll();
            for (int i = 0; i<3; i++) {
                if (Arrays.deepEquals(current.nBoard.sideGoal, current.nBoard.currentSide)) {
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
        if(root.parent == null){
            root = new Node(root.nBoard, root.nBoard.mhSide());
        }
        else{
            root = new Node(root.nBoard, root.nBoard.mhSide(), root.parent);
        }
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
