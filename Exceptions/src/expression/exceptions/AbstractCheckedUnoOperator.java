package expression.exceptions;

import expression.CommonExpression;

import java.util.Objects;

public abstract class AbstractCheckedUnoOperator implements CommonExpression {
    protected final CommonExpression x;

    public AbstractCheckedUnoOperator(CommonExpression in) {
        this.x = in;
    }

    protected abstract String getView();
    protected abstract int getResult(int in);

    @Override
    final public String toString() {
        return getView() + x.toString();
    }

    @Override
    final public int evaluate(int in) {
        return getResult(this.x.evaluate(in));
    }

    @Override
    final public int evaluate(int x, int y, int z) {
        return getResult(this.x.evaluate(x, y, z));
    }

    @Override
    final public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return x.equals(((AbstractCheckedUnoOperator)obj).x);
        }
        return false;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(getClass(), x);
    }
}
