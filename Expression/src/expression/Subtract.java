package expression;

public class Substract extends AbstractBinaryOperator {
    public Substract(AbstractExpression left, AbstractExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "-";
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected boolean getCommutativity() {
        return false;
    }

    @Override
    protected int getResult(int left, int right) {
        return left - right;
    }
}
