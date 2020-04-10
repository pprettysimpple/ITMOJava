package expression.exceptions;

public class NegativeLogarithm extends ExpressionException {
    public NegativeLogarithm(int right) {
        super("Logarithm of negative value:" + right);
    }
}
