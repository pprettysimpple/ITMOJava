package expression;

import expression.evaluator.Evaluator;

public abstract class AbstractBinaryOperation<T> implements TripleExpression<T> {
    private TripleExpression<T> first, second;

    AbstractBinaryOperation(TripleExpression<T> first, TripleExpression<T> second) {
        this.first = first;
        this.second = second;
    }

    protected abstract calc(T first, T second, Evaluator<T> evaluator);
}
