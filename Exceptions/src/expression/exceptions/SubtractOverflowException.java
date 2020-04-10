package expression.exceptions;

public class SubtractOverflowException extends OverflowException {
    public SubtractOverflowException(int a, int b) {
        super("Subtract", a + " " + b);
    }
}
