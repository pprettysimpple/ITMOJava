package expression.parser;

import java.util.List;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 * @author Georgiy Korneev
 */
public class ParserReverseDigitsTest extends ParserShiftsTest {
    protected ParserReverseDigitsTest() {
        unary.add(op("reverse", ParserReverseDigitsTest::reverse));
        unary.add(op("digits", ParserReverseDigitsTest::digits));

        tests.addAll(List.of(
                op("reverse 12345", (x, y, z) -> 54321L),
                op("reverse -12345", (x, y, z) -> -54321L),
                op("reverse (x - y)", (x, y, z) -> reverse(x - y)),
                op("x - reverse -y", (x, y, z) -> x - reverse(-y)),
                op("reverse -x", (x, y, z) -> reverse(-x)),
                op("reverse(x+y)", (x, y, z) -> reverse(x + y)),
                op("digits -12345", (x, y, z) -> 15L),
                op("digits (x - y)", (x, y, z) -> digits(x - y)),
                op("x - digits y", (x, y, z) -> x - digits(y)),
                op("digits -x", (x, y, z) -> digits(-x)),
                op("digits(x+y)", (x, y, z) -> digits(x + y))
        ));
    }

    private static long digits(final long v) {
        return Math.abs(reduce(v, Long::sum));
    }

    private static long reverse(final long v) {
        return reduce(v, (a, b) -> a * 10 + b);
    }

    private static long reduce(final long v, final LongBinaryOperator op) {
        return LongStream.iterate(v, n -> n != 0, n -> n / 10).map(n -> n % 10).reduce(0, op);
    }

    public static void main(final String[] args) {
        new ParserReverseDigitsTest().run();
    }
}
