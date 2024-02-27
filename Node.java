public class Node {
    int g;
    int h;
    Board nBoard;
    Node parent;

    public Node (Board b, int mh){
        nBoard = b;
        g = 0;
        h = mh;
        parent = null;
    }
    public Node (Board b, int mh, Node p){
        parent = p;
        nBoard = b;
        g = parent.g+1;
        h = mh+g;
    }
    public boolean isGoal(){
        boolean isGoal = false;
        if (nBoard.isGoal()){
            isGoal = true;
        }
        return isGoal;
    }
    public int getH(){
        return h;
    }
    public Board getnBoard(){
        return nBoard;
    }
    public void path(){
        if (this.parent != null){
            parent.path();
        }
        this.printSelf();
    }
    public void printSelf(){
        System.out.println(nBoard.toString());
    }

}
