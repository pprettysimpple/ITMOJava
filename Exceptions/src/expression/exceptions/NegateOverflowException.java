package expression.exceptions;

public class NegateOverflowException extends OverflowException {
    public NegateOverflowException(int a) {
        super("Minus", Integer.toString(a));
    }
}
