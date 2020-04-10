package expression;

public class Subtract extends AbstractBinaryOperator {
    public Subtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "-";
    }

    @Override
    protected int getResult(int left, int right) {
        return left - right;
    }
}
