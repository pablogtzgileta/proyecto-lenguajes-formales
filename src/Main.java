import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Modulo1 modulo1 = new Modulo1();
        Modulo2 modulo2 = new Modulo2();

        // Modulo 1 y 2
        String postfijo = modulo1.exToPostix("(F,f)*r");


        // Modulo 3
        HashMap<Integer, String[]> pattern;
        int initialState;

        pattern = modulo2.afne(postfijo);
        initialState = modulo2.getInitialState();
        Modulo3 modulo3 = new Modulo3(pattern, initialState);
        modulo3.checkAllStates();
        modulo3.printVisitedClausuras();
    }
}