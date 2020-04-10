package expression.exceptions;

public class MultiplyOverflowException extends OverflowException {
    public MultiplyOverflowException(int left, int right) {
        super("Multiply", left + " " + right);
    }
}
