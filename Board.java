import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Board {
    private int[][] tiles;
    private int size;
    private int[][] goal;

    //constructor
    public Board(int[][] tiles, int[][] goal){
        this.tiles = tiles;
        this.size = tiles.length;
        this.goal = goal;

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

     // is this board the goal board?
     public boolean isGoal(){
        boolean isGoal = false;
        if(tiles.equals(goal)){
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
