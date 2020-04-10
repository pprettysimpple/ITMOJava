package expression;

public class Log2 extends AbstractUnoOperator {
    public Log2(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "log2 ";
    }

    @Override
    protected int getResult(int x) {
        int res = 0;
        while (x > 1) {
            res++;
            x /= 2;
        }
        return res;
    }
}
