package expression.exceptions;

public class PowerOverflowException extends OverflowException {
    public PowerOverflowException(int left, int right) {
        super("Power", left + " " + right);
    }
}
