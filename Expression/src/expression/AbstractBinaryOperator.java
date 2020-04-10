package expression;

import java.util.Objects;

public abstract class AbstractBinaryOperator implements CommonExpression {
    protected final CommonExpression left, right;

    public AbstractBinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getView();
    protected abstract int getResult(int left, int right);

    @Override
    final public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("(").append(left).append(" ");
        res.append(getView());
        res.append(" ").append(right).append(")");
        return res.toString();
    }

    @Override
    final public String toMiniString() {
        return toString();
    }

    @Override
    final public int evaluate(int x) throws ArithmeticException {
        return getResult(left.evaluate(x), right.evaluate(x));
    }

    @Override
    final public int evaluate(int x, int y, int z) {
        return getResult(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    final public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return ((AbstractBinaryOperator)obj).left.equals(left)
                    && ((AbstractBinaryOperator)obj).right.equals(right);
        }
        return false;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}
