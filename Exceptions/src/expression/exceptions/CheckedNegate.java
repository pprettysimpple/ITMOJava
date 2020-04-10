package expression.exceptions;

import expression.CommonExpression;

public class CheckedNegate extends AbstractCheckedUnoOperator {
    public CheckedNegate(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "-";
    }

    @Override
    protected int getResult(int in) {
        if (in == Integer.MIN_VALUE) {
            throw new NegateOverflowException(in);
        }
        return -in;
    }
}
