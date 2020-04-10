package expression.exceptions;

public class DivOverflowException extends OverflowException {
    public DivOverflowException(int left, int right) {
        super("Divide", left + " " + right);
    }
}
