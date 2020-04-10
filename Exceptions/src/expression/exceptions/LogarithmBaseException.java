package expression.exceptions;

public class LogarithmBaseException extends ExpressionException {
    public LogarithmBaseException(int left, int right) {
        super(left + " // " + right + " is undefined");
    }
}
