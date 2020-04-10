package expression.exceptions;

import expression.CommonExpression;

public class Power2 extends AbstractCheckedUnoOperator {
    public Power2(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "pow2 ";
    }

    @Override
    protected int getResult(int x) {
        if (x < 0 || x > 31) {
            throw new PowerOverflowException(2, x);
        }
        return (1 << x);
    }
}
