package expression;

public class Log extends AbstractBinaryOperator {
    public Log(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "//";
    }

    @Override
    protected int getResult(int left, int right) {
        int res = 0;
        while (left > 1) {
            left /= right;
            res++;
        }
        return res;
    }
}
