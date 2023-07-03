package Etapa3;
import java.util.Scanner;

public class ListaEstatica {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma expressão matemática: ");

        String str = sc.nextLine();

        StaticStack<Character> stack = stringToStack(str);

        System.out.printf("A expressão matemática %sé regular.",
                ((checkBrackets(stack) ? "" : "não ")));
    }

    public static StaticStack<Character> stringToStack(String str) {

        final int TAM = str.length();
        StaticStack<Character> stack = new StaticStack<>(TAM);

        for (int i = TAM - 1; i >= 0; i--) {
            stack.push((Character) str.charAt(i));
        }

        return stack;
    }

    public static boolean checkBrackets(StaticStack<Character> s1) {

        final int TAM = s1.numElements();
        StaticStack<Character> stack = new StaticStack<>(TAM);

        boolean rule1Violated = false;
        boolean rule2Violated = false;

        for (int i = 0; i < TAM; i++) {

            if (s1.top() == '(') {
                stack.push(s1.top());
                s1.pop();
            } else if (s1.top() == ')') {
                if (stack.isEmpty()) {
                    rule1Violated = true;
                } else {
                    stack.pop();
                }
                s1.pop();
            } else {
                s1.pop();
            }
        }

        if (!stack.isEmpty()) {
            rule1Violated = true;
        }

        if (rule1Violated) {
            rule2Violated = true;
        }

        return !(rule1Violated || rule2Violated);
    }
}
