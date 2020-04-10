package expression;

import java.util.Objects;

public abstract class AbstractUnoOperator implements CommonExpression {
    protected final CommonExpression in;

    public AbstractUnoOperator(CommonExpression in) {
        this.in = in;
    }

    protected abstract String getView();
    protected abstract int getResult(int in);

    @Override
    final public String toString() {
        return getView() + in.toString();
    }

    @Override
    final public int evaluate(int x) {
        return getResult(in.evaluate(x));
    }

    @Override
    final public int evaluate(int x, int y, int z) {
        return getResult(in.evaluate(x, y, z));
    }

    @Override
    final public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return in.equals(((AbstractUnoOperator)obj).in);
        }
        return false;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(getClass(), in);
    }
}
