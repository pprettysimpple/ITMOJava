package expression;

public class Minus extends AbstractUnoOperator {
    public Minus(CommonExpression in) {
        super(in);
    }

    @Override
    protected String getView() {
        return "-";
    }

    @Override
    protected int getResult(int in) {
        return -in;
    }
}
