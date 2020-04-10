package expression;

public class Divide extends AbstractBinaryOperator {
    public Divide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "/";
    }

    @Override
    protected int getResult(int left, int right) {
        return left / right;
    }
}
