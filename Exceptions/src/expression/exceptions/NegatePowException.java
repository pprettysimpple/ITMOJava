package expression.exceptions;

public class NegatePowException extends ExpressionException {
    public NegatePowException(int left, int right) {
        super(left + "^" + right + " is undefined");
    }
}
