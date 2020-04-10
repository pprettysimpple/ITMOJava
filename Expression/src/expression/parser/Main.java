package expression.parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ExpressionParser parser = new ExpressionParser();
        var res = parser.parse(in.nextLine());
        System.out.println("x" + " ".repeat(10) +"f");
        for (int x = 0; x < 11; x++) {
            System.out.println(x + " ".repeat(10) + res.evaluate(x));
        }
    }
}
