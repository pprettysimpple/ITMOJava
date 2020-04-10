package expression.exceptions;

import expression.CommonExpression;

public class CheckedDivide extends AbstractCheckedBinaryOperator {
    public CheckedDivide(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "/";
    }

    @Override
    protected int getResult(int left, int right) {
        if (right == 0) {
            throw new DivByZeroException();
        }
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new DivOverflowException(left, right);
        }
        return left / right;
    }
}
