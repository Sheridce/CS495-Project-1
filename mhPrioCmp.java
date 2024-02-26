import java.util.Comparator;

public class mhPrioCmp implements Comparator<Board> {
    public int compare(Board o1, Board o2) {
        if (o1.getMHPrio() < o2.getMHPrio()){
            return -1;
        }
        else{
            return 1;
        }
    }
}

