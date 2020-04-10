package expression;

public abstract class CommonBinaryOperator implements CommonExpression {
    protected final CommonExpression left, right;

    public CommonBinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getView();
    protected abstract int getPriority();
    protected abstract boolean getCommutativity();
    protected abstract int getResult(int left, int right);

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("(").append(left).append(")");
        res.append(getView());
        res.append("(").append(right).append(")");
        return res.toString();
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluate(int x) throws ArithmeticException {
        return getResult(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getResult(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }
}
