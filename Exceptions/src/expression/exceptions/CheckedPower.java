package expression.exceptions;

import expression.CommonExpression;

public class CheckedPower extends AbstractCheckedBinaryOperator {
    public CheckedPower(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "**";
    }

    @Override
    protected int getResult(int left, int right) {
        if (right < 0) {
            throw new NegatePowException(left, right);
        }

        try {
            int res = 1;
            while (right != 0) {
                if ((right & 1) == 1) {
                    right--;
                    CheckedMultiply.checkException(res, left);
                    res *= left;
                }
                CheckedMultiply.checkException(res, res);
                res *= res;
                left /= 2;
            }
            return res;
        } catch (MultiplyOverflowException e) {
            throw new PowerOverflowException(left, right);
        }
    }
}
