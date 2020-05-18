import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Expresión regular a notación postfija
 */
public class Modulo1 {

    static String postfix = "";
    static Stack<Character> stack = new Stack<>();
    static List<Character> operators = Arrays.asList('*', '+', ',', '.');
    static List<Character> parenthesis = Arrays.asList('(', ')');

    public Modulo1() {

    }

    public String exToPostix(String regularExpression) {


        return "";
    }

    public String formattedEx(String regularExpression) {
        StringBuilder formattedEx = new StringBuilder();


        for (int i = 0; i < regularExpression.length(); i++) {
            if (operators.contains(regularExpression.charAt(i)) || regularExpression.charAt(i) == '(') {
                formattedEx.append(regularExpression.charAt(i));
            } else {
                if (i + 1 < regularExpression.length() && !operators.contains(regularExpression.charAt(i + 1)) && regularExpression.charAt(i + 1) != ')') {
                    formattedEx.append(regularExpression.charAt(i)).append(".");
                } else {
                    formattedEx.append(regularExpression.charAt(i));
                }
            }
        }
        return formattedEx.toString();
    }
}
