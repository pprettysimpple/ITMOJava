package expression;

public class Const implements CommonExpression {
    private final int val;

    public Const(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public int evaluate(int x) throws ArithmeticException {
        return val;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return val == ((Const) obj).val;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return val;
    }
}
