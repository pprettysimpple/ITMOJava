package expression.exceptions;

public class OverflowException extends ExpressionException {
    public OverflowException(String where, String what) {
        super(where + " overflowed by " + what);
    }
}
