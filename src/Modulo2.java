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

    public HashMap<Integer, String[]> afne(String postfix) {
        for (Character c : postfix.toCharArray()) {
            if (!operators.contains(c)) {
                initValue(c);
            } else {
                switch (c) {
                    case '*':
                        starOperation();
                        break;
                    case '+':
                        plusOperation();
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

        printPattern();
        System.out.println("Initial state: " + initialGlobalState);

        return pattern;
    }

    public int getInitialState() {
        return initialGlobalState;
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

        ArrayList<String> alreadyVisited = new ArrayList<>();
        alreadyVisited.add(String.valueOf(right));

        concatModifyFinalStates(left, right, alreadyVisited);
        // Parte que abre a dos estados posibles mediante un epsilon
        pattern.put(statePos, new String[]{"%", String.valueOf(left), String.valueOf(right)});
        statePos++;

        initialGlobalState = initialState;
        stack.push(initialState);
    }

    private void starOperation() {
        int state = stack.pop();
        int initialState = statePos;

        starModifyFinalStates(state, state, new ArrayList<>());
        // Parte que abre a dos estados posibles mediante un epsilon
        pattern.put(statePos, new String[]{"|", "%", String.valueOf(state)});
        statePos++;

        initialGlobalState = initialState;
        stack.push(initialState);
    }

    private void plusOperation() {
        int state = stack.pop();
        int initialState = statePos;

        starModifyFinalStates(state, state, new ArrayList<>());

        stack.push(initialState);
    }

    private void concatModifyFinalStates(int state, int appendState, ArrayList<String> alreadyVisited) {
        String[] check = pattern.get(state);

        // Recorremos array para bucsar final
        for (int i = 0; i < check.length; i++) {
            if (!alreadyVisited.contains(check[i])) {
                if (i == 0 && check[i].equals("|")) {   // Si detectamos un final
                    if (i + 1 >= check.length) { // Si no tiene moviemientos ese final
                        pattern.put(state, new String[]{"%", String.valueOf(appendState)}); // Agregamos epsilon y el valor al que se puede mover
                    } else {
                        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(check)); // Convertimos array checado a lista para manejar con facilidad
                        arrayList.remove(0); // Lo removemos como estado final quitando el primero que contiene  '|'
                        arrayList.add(String.valueOf(appendState)); // Agregamos el nuevo estado al que se puede movere
                        pattern.put(state, arrayList.toArray(new String[0])); // Actualizamos cambios al estado actual
                    }
//                    alreadyVisited.add(check[i]); // Agregar a ya visitado
                } else if ((i != 0 && !check[0].equals("|")) || i > 1) {
                    alreadyVisited.add(check[i]); // Agregar a ya visitado
                    concatModifyFinalStates(Integer.parseInt(check[i]), appendState, alreadyVisited); // Pasar nuevo estado a checar
                }
            }
        }
    }

    private void starModifyFinalStates(int state, int appendState, ArrayList<String> alreadyVisited) {
        String[] check = pattern.get(state);

        // Recorremos array para bucsar final
        for (int i = 0; i < check.length; i++) {
            if (!alreadyVisited.contains(check[i])) {
                if (i == 0 && check[i].equals("|")) {   // Si detectamos un final
                    if (i + 1 >= check.length) { // Si no tiene moviemientos ese final
                        pattern.put(state, new String[]{"|", "%", String.valueOf(appendState)}); // Agregamos epsilon y el valor al que se puede mover
                    } else {
                        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(check)); // Convertimos array checado a lista para manejar con facilidad
                        arrayList.add(String.valueOf(appendState)); // Agregamos el nuevo estado al que se puede movere
                        pattern.put(state, arrayList.toArray(new String[0])); // Actualizamos cambios al estado actual
                    }
//                    alreadyVisited.add(check[i]); // Agregar a ya visitado
                } else if ((i != 0 && !check[0].equals("|")) || i > 1) {
                    alreadyVisited.add(check[i]); // Agregar a ya visitado
                    starModifyFinalStates(Integer.parseInt(check[i]), appendState, alreadyVisited); // Pasar nuevo estado a checar
                }
            }
        }

    }

    private void printPattern() {
        pattern.forEach((k, v) -> {
            System.out.print(k);
            System.out.println(Arrays.toString(v));
        });
    }
}
