package expression.exceptions;

import expression.CommonExpression;

public class CheckedMultiply extends AbstractCheckedBinaryOperator {
    public CheckedMultiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "*";
    }

    @Override
    protected int getResult(int left, int right) {
        checkException(left, right);
        return left * right;
    }

    public static void checkException(int left, int right) {
        if (left != 0 && right != 0) {
            if (left == -1 && right == Integer.MIN_VALUE || right == -1 && left == Integer.MIN_VALUE) {
                throw new MultiplyOverflowException(left, right);
            } else if (left * right == Integer.MIN_VALUE && Integer.MIN_VALUE / right == left) {
                // return
            } else if (left == Integer.MIN_VALUE || right == Integer.MIN_VALUE) {
                throw new MultiplyOverflowException(left, right);
            } else if (Integer.MAX_VALUE / abs(left) < abs(right)) {
                throw new MultiplyOverflowException(left, right);
            }
        }
    }

    private static int abs(int x) {
        return x > 0 ? x : -x;
    }
}
