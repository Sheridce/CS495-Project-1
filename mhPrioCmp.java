import java.util.Comparator;

public class mhPrioCmp implements Comparator<Node> {
    public int compare(Node o1, Node o2) {
        if (o1.getH() < o2.getH()){
            return -1;
        }
        else{
            return 1;
        }
    }
}

