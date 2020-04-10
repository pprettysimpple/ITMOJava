package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "*";
    }

    @Override
    protected int getResult(int left, int right) {
        return left * right;
    }
}
