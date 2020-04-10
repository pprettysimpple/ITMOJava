package expression.exceptions;

import expression.CommonExpression;

public class CheckedSubtract extends AbstractCheckedBinaryOperator {
    public CheckedSubtract(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "-";
    }

    @Override
    protected int getResult(int left, int right) {
        if (right > 0 && Integer.MIN_VALUE + right > left) {
            throw new SubtractOverflowException(left, right);
        } else if (right < 0 && Integer.MAX_VALUE + right < left) {
            throw new SubtractOverflowException(left, right);
        }
        return left - right;
    }
}
