import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.*;

// epsilon = %
// final = |

public class Modulo2 {

    private HashMap<Integer, String[]> pattern;
    Stack<Integer> stack = new Stack<>();
    private ArrayList<Integer> finalStates = new ArrayList<>();
    private int initialGlobalState = 0;

    static List<Character> operators = Arrays.asList('*', '+', ',', '.');
    int statePos = 0;

    public Modulo2() {
        pattern = new HashMap<>();
    }

    public String afne(String postfix) {
        for (Character c : postfix.toCharArray()) {
            if (!operators.contains(c)) {
                initValue(c);
            } else {
                switch (c) {
                    case '*':
                        break;
                    case '+':
                        break;
                    case ',':
                        unionOperation();
                        break;
                    case '.':
                        concatenationOperation();
                        break;
                    default:
                }
            }
        }

        return "";
    }

    private void initValue(char c) {
        int initialState = statePos;
        pattern.put(statePos, new String[]{String.valueOf(c), String.valueOf(++statePos)}); // Example 0 -> ['f', '1']
        pattern.put(statePos, new String[]{"|"}); // 2 -> ['|']
        finalStates.add(statePos);
        statePos++;
        stack.push(initialState);
    }

    private void unionOperation() {
        int right = stack.pop();
        int left = stack.pop();
        int initialState = statePos;

        // Parte que abre a dos estados posibles mediante un epsilon
        pattern.put(statePos, new String[]{"%", String.valueOf(left), String.valueOf(right)});
        statePos++;

        initialGlobalState = initialState;
        stack.push(initialState);
    }

    private void concatenationOperation() {
        int right = stack.pop();
        int left = stack.pop();
        int initialState = statePos;

        concatModifyFinalStates(left, right, new ArrayList<>());
        // Parte que abre a dos estados posibles mediante un epsilon
        pattern.put(statePos, new String[]{"%", String.valueOf(left), String.valueOf(right)});
        statePos++;

        initialGlobalState = initialState;
        stack.push(initialState);
    }

    private void concatModifyFinalStates(int state, int appendState, ArrayList<String> alreadyVisited) {
        String[] check = pattern.get(state);

        for (int i = 0; i < check.length; i++) {
            if (i == 0 && check[i].equals("|")) {
                pattern.put(state, new String[]{"%", String.valueOf(appendState)});
                break;
            } else if (i != 0) {
                if (!alreadyVisited.contains(check[i])) {
                    alreadyVisited.add(check[i]);
                    concatModifyFinalStates(Integer.parseInt(check[i]), appendState, alreadyVisited);
                }
            }
        }

    }

    private void printPattern() {
        pattern.forEach((k,v) -> {
            System.out.println(k + "[" + v + "]");
        });
    }
}
