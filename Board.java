import java.util.Collection;

public class Board {
    private int[][] tiles;
    private int size;
    private int[][] goal;

    //constructor
    public Board(int[][] tiles){
        this.tiles = tiles;
        this.size = tiles.length;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                goal[i][j] = i;
            }
            goal[size][size] = 0;
        }
    }

    // string representation of this board
    public String toString(){
        String result = "";
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
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
                 if(tiles[i][j] != goal[i][j]){
                     hamming++;
                 }
             }
         }
        return hamming;
     }

     // sum of Manhattan distances between tiles and goal
     public int manhattan(){
        int manhattan = 0;
        int moves;


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
        Collection <Board> neighbors = null;
        int xpos = 0;
        int ypos = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (tiles[i][j] == 0){
                    xpos = i;
                    ypos = j;
                    break;
                }
            }
        }
        if (xpos != 0){
            int [][] n1 = tiles.clone();
            n1[xpos][ypos] = n1[xpos-1][ypos];
            n1[xpos-1][ypos] = 0;
            Board neighbor1 = new Board(n1);
            neighbors.add(neighbor1);
        }
        if (ypos!= 0){
            int [][] n2 = tiles.clone();
            n2[xpos][ypos] = n2[xpos][ypos-1];
            n2[xpos][ypos-1] = 0;
            Board neighbor2 = new Board(n2);
            neighbors.add(neighbor2);
        }
        if (xpos < size){
            int [][] n3 = tiles.clone();
            n3[xpos][ypos] = n3[xpos+1][ypos];
            n3[xpos+1][ypos] = 0;
            Board neighbor3 = new Board(n3);
            neighbors.add(neighbor3);
        }
        if (ypos < size){
            int [][] n4 = tiles.clone();
            n4[xpos][ypos] = n4[xpos][ypos+1];
            n4[xpos][ypos+1] = 0;Board
            neighbor4 = new Board(n4);
            neighbors.add(neighbor4);
        }
        return neighbors;
     }
}
