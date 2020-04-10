package expression.exceptions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var res = new ExpressionParser().parse(sc.nextLine());
        System.out.println(res);
    }
}
