package expression.exceptions;

public class ConstOverflowException extends OverflowException {
    public ConstOverflowException(String num) {
        super("Constant", num);
    }
}
