package expression.exceptions;

public class DivByZeroException extends ExpressionException {
    public DivByZeroException() {
        super("Division by zero");
    }
}
