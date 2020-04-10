package expression;

public class Power2 extends AbstractUnoOperator {
    public Power2(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "pow2 ";
    }

    @Override
    protected int getResult(int x) {
        return (1 << x);
    }
}
