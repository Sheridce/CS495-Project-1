import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board{
    int[][] tiles;
    private int size;
    private int[][] goal;
    int topGoal[] = {1, 2, 3, 4};
    int sideGoal[][] = new int [1][4];
    int currentTop[] = new int[4];
    int[][] currentSide = new int [1][4];



    //constructor
    public Board(int[][] tiles, int[][] goal){
        this.tiles = tiles;
        this.size = tiles.length;
        this.goal = goal;
        sideGoal[0][0] = 1;
        for (int i = 1; i<size; i++){
            sideGoal[0][i] = goal[i][0];
        }
        for (int i = 0; i<size; i++){
            currentTop [i] = tiles [0][i];
        }
        for (int i = 0; i < size; i++){
            currentSide[0][i] = tiles[i][0];
        }

    }

    public boolean equals(Board b1, Board b2) {
        boolean eq = false;
        if (b1.equals(b2)){
            eq = true;
        }
        return eq;
    }

    // string representation of this board
    public String toString(){
        String result = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result += tiles[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
    public boolean checkEQ(Board b1, Board b2){
        boolean eq = false;
        if (Arrays.deepEquals(b1.tiles, b2.tiles)){
            eq = true;
        }
        return eq;
    }

    // board dimension n
    public int dimension(){
        return size;
    }

     // number of tiles out of place
     public int hamming(){
        int hamming = 0;
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 if((tiles[i][j] != goal[i][j]) && tiles[i][j] != 0){
                     hamming++;
                 }
             }
         }
        return hamming;
     }

     // sum of Manhattan distances between tiles and goal
     public int manhattan(){
        int manhattan = 0;
        int x0 = 0;
        int x1 = 0;
        int y0 = 0;
        int y1 = 0;
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 if ((tiles[i][j] != goal[i][j]) && tiles[i][j] != 0){
                     x1 = i+1;
                     y1 = j+1;
                     for (int k = 0; k < size; k++) {
                         for (int l = 0; l < size; l++) {
                            if(goal[k][l] == tiles[x1-1][y1-1]){
                                x0 = k+1;
                                y0 = l+1;
                            }
                         }
                     }
                     manhattan += Math.abs(x1-x0) + Math.abs(y1-y0);
                 }
             }
         }


        return manhattan;
     }
     //determines the manhattan values of the top of the board
     public int mhTop(){
        int mhTop = 0;
        int x0 = 0;
        int x1 = 1;
         for (int i = 0; i < size; i++) {
             if ((currentTop[i] != topGoal[i]) && currentTop[i]!= 0){
                 x1 = i+1;
                 for (int j = 0; j < size; j++) {
                     if(topGoal[j] == currentTop[x1-1]){
                         x0 = j+1;
                     }
                 }
                 mhTop += Math.abs(x1-x0);
             }
         }
         return mhTop;
    }

    //determines the manhattan values of the left side of the board
    public int mhSide(){
        int mhSide = 0;
        int y0 = 0;
        int y1 = 0;
        for (int i = 0; i < size; i++) {
                if ((currentSide[0][i] != sideGoal[0][i]) && currentSide[0][i] != 0){
                    y1 = i+1;
                    for (int k = 0; k < size; k++) {
                            if(sideGoal[0][k] == currentSide[0][y1-1]){
                                y0 = k+1;
                            }
                        }
                    }
                    mhSide += Math.abs(y1-y0);
                }
        return mhSide;
    }

     // is this board the goal board?
     public boolean isGoal(){
        boolean isGoal = false;
        if(Arrays.deepEquals(tiles, goal)){
            isGoal = true;
        }
        return isGoal;
     }

     // all neighboring boards
     public Iterable<Board> neighbors(){
        List<Board> neighbors = new ArrayList<>();
        int xpos = 0;
        int ypos = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (tiles[i][j] == 0){
                    xpos = i;
                    ypos = j;
                    break;
                }
                if (tiles[xpos][ypos] == 0){
                    break;
                }
            }
            if (tiles[xpos][ypos] == 0){
                break;
            }
        }
        if (xpos != 0){
            int [][] n1 = new int [size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++) {
                    n1[i][j] = tiles[i][j];
                }
            }
            n1[xpos][ypos] = n1[xpos-1][ypos];
            n1[xpos-1][ypos] = 0;
            Board neighbor1 = new Board(n1, goal);
            neighbors.add(neighbor1);
        }
        if (ypos!= 0){
            int [][] n2 = new int [size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++) {
                    n2[i][j] = tiles[i][j];
                }
            }
            n2[xpos][ypos] = n2[xpos][ypos-1];
            n2[xpos][ypos-1] = 0;
            Board neighbor2 = new Board(n2, goal);
            neighbors.add(neighbor2);
        }
        if (xpos+1 < size){
            int [][] n3 = new int [size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++) {
                    n3[i][j] = tiles[i][j];
                }
            }
            n3[xpos][ypos] = n3[xpos+1][ypos];
            n3[xpos+1][ypos] = 0;
            Board neighbor3 = new Board(n3, goal);
            neighbors.add(neighbor3);
        }
        if (ypos+1 < size){
            int [][] n4 = new int [size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++) {
                    n4[i][j] = tiles[i][j];
                }
            }
            n4[xpos][ypos] = n4[xpos][ypos+1];
            n4[xpos][ypos+1] = 0;Board
            neighbor4 = new Board(n4, goal);
            neighbors.add(neighbor4);
        }
        return neighbors;
     }

}
