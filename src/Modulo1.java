import java.util.*;

/**
 * Expresión regular a notación postfija
 */
public class Modulo1 {

    // Concatenacion = .
    // Salto de linea = #

    static String postfix = "";
    static Stack<Character> stack = new Stack<>();
    static List<Character> operators = Arrays.asList('*', '+', ',', '.');
    static List<Character> parenthesis = Arrays.asList('(', ')');

    public Modulo1() {

    }


    public String exToPostix(String regularExpression) {
        //Texto con el formato
        String formattedEx = formattedEx(regularExpression);

        //Creacion de pila
        Stack<Character> stack = new Stack<>();

        for (Character c : formattedEx.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;

                case ')':
                    while (!stack.peek().equals('(')) {
                        postfix += stack.pop();
                    }
                    stack.pop();
                    break;

                default:
                    while (stack.size() > 0) {
                        Character peekedChar = stack.peek();

                        Integer peekedCharPrecedence = getPrecedence(peekedChar);
                        Integer currentCharPrecedence = getPrecedence(c);

                        if (peekedCharPrecedence >= currentCharPrecedence)
                            postfix += stack.pop();
                        else break;
                    }
                    stack.push(c);
                    break;
            }
        }
        while (stack.size() > 0)
            postfix += stack.pop();
        return postfix;
    }

    private static Integer getPrecedence(Character c) {
        Integer precedence = precedenceMap.get(c);
        return precedence == null ? 6 : precedence;
    }

    private static final Map<Character, Integer> precedenceMap;

    static {
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put(',', 1);
        map.put('.', 2);
        map.put('+', 3);
        map.put('*', 3);

        precedenceMap = Collections.unmodifiableMap(map);
    }

    public String formattedEx(String regularExpression) {
        StringBuilder formattedEx = new StringBuilder();

        for (int i = 0; i < regularExpression.length(); i++) {
            if (operators.contains(regularExpression.charAt(i)) || regularExpression.charAt(i) == '(') {
                formattedEx.append(regularExpression.charAt(i));
            } else {
                if (i + 1 < regularExpression.length() && regularExpression.charAt(i) == '\\' && regularExpression.charAt(i + 1) == 'n') {
                    formattedEx.append("#");
                    i++;
                } else if (i + 1 < regularExpression.length() && !operators.contains(regularExpression.charAt(i + 1)) && regularExpression.charAt(i + 1) != ')') {
                    if (Character.isWhitespace(regularExpression.charAt(i))) {
                        formattedEx.append("$");
                    } else {
                        formattedEx.append(regularExpression.charAt(i)).append(".");
                    }
                    if (i + 1 < regularExpression.length() &&
                            Character.isWhitespace(regularExpression.charAt(i)) &&
                            regularExpression.charAt(i + 1) != ')' &&
                            (regularExpression.charAt(i + 1) == '(' || !operators.contains(regularExpression.charAt(i + 1)))
                    ) {
                        formattedEx.append(".");
                    }
                } else {
                    if (Character.isWhitespace(regularExpression.charAt(i))) {
                        formattedEx.append("$");
                    } else {
                        formattedEx.append(regularExpression.charAt(i));
                    }
                }
            }
        }
        return formattedEx.toString();
    }
}
