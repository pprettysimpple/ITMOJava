package expression.exceptions;

import expression.CommonExpression;

public class CheckedLog2 extends AbstractCheckedUnoOperator {
    public CheckedLog2(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "log2 ";
    }

    @Override
    protected int getResult(int x) {
        if (x <= 0) {
            throw new NegativeLogarithm(x);
        }
        int res = 0;
        while (x > 1) {
            res++;
            x /= 2;
        }
        return res;
    }
}
