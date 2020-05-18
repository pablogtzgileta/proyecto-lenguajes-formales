import java.util.*;

public class Modulo3 {
    private HashMap<Integer, String[]> pattern;
    Stack<Integer> stack = new Stack<>();
    private ArrayList<Integer> finalStates = new ArrayList<>();
    private int initialGlobalState = 0;

    static List<Character> operators = Arrays.asList('*', '+', ',', '.');
    int statePos = 0;

    public Modulo3() {
        pattern = new HashMap<>();
    }

}
