package expression.exceptions;

public class AddOverflowException extends OverflowException {
    public AddOverflowException(int a, int b) {
        super("Add", a + " " + b);
    }
}
