import java.util.*;

public class Modulo3 {
    private HashMap<Integer, String[]> pattern;
    private static  HashMap<Integer, String[]> clausuras;
    private int initialGlobalState;

    public Modulo3(HashMap<Integer, String[]> pattern, int initialState) {
        this.pattern = pattern;
        clausuras = new HashMap<>();
        this.initialGlobalState = initialState;
    }

    public void checkAllStates() {
        ArrayList<String> visitedClausuras;
        ArrayList<String> addClausuras;
        for (int i = 0; i < pattern.size(); i++) {
            visitedClausuras = new ArrayList<>();
            addClausuras = checkEpsilons(i, visitedClausuras);
            if (addClausuras.size() > 0) {
                addClausuras.add(String.valueOf(i));
                clausuras.put(i, addClausuras.toArray(new String[0]));
            }
        }
    }

    private ArrayList<String> checkEpsilons(int state, ArrayList<String> visitedClausaras) {
        String[] check = pattern.get(state);

        // Recorremos array para bucsar final
        for (int i = 0; i < check.length; i++) {
            if (check.length > 1 && !visitedClausaras.contains(check[i])) {
                if (check[0].equals("%") && i > 0) {
                    visitedClausaras.add(String.valueOf(check[i]));
                    visitedClausaras = checkEpsilons(Integer.parseInt(check[i]), visitedClausaras);
                } else if (check[1].equals("%") && i > 1) {
                    visitedClausaras.add(String.valueOf(check[i]));
                    visitedClausaras = checkEpsilons(Integer.parseInt(check[i]), visitedClausaras);
                }
            }
        }

        return visitedClausaras;
    }

    public void printVisitedClausuras() {
        if (clausuras.size() > 0) {
            clausuras.forEach((k, v) -> {
                System.out.print(k);
                System.out.println(Arrays.toString(v));
            });
        }
    }
}
