package expression;

public class Power extends AbstractBinaryOperator {
    public Power(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "**";
    }

    @Override
    protected int getResult(int left, int right) {
        int res = 1;
        while (right != 0) {
            if ((right & 1) == 1) {
                right--;
                res *= left;
            }
            res *= res;
            left /= 2;
        }
        return res;
    }
}
