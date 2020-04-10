package expression.exceptions;

import expression.CommonExpression;

public class CheckedLog extends AbstractCheckedBinaryOperator {
    public CheckedLog(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "//";
    }

    @Override
    protected int getResult(int left, int right) {
        if (left < 0 || left == 1) {
            throw new LogarithmBaseException(left, right);
        }
        if (right <= 0) {
            throw new NegativeLogarithm(right);
        }
        int res = 0;
        while (left > 1) {
            left /= right;
            res++;
        }
        return res;
    }
}
