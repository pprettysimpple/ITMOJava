package expression.generic;

/**
 * Count, min, max over unchecked int, float, byte test.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class GenericCmmUfbTest extends GenericUfbTest {
    public GenericCmmUfbTest() {
        GenericCmmTest.addCmm(this);
        ufbConst("2 min 3", 2);
        ufbConst("4 min 2 min 3", 2);
        ufbConst("2 max 3", 3);
        ufbConst("4 max 2 max 3", 4);
        ufbConst("20 min 3 + 3", 6);
        ufbConst("3 * 3 min 20", 9);
        ufbConst("4 min 2 max 3", 3);
        ufbConst("1 max 2 min 3", 2);
        ufbConst("6 - 10 min-4", -4);
        ufbConst("6 - 10 min-5", -5);
        ufb(
                "x min y * z",
                (x, y, z) -> Math.min(x, y * z),
                (x, y, z) -> Math.min(x, y * (float) z),
                (x, y, z) -> (byte) Math.min(x, (byte) (y * z))
        );
        ufb(
                "2 min x + 1",
                (x, y, z) -> Math.min(2, x + 1),
                (x, y, z) -> Math.min(2, x + 1.0f),
                (x, y, z) -> (byte) (Math.min(2, x + 1))
        );
        ufb(
                "x min y min z",
                (x, y, z) -> Math.min(Math.min(x, y), z),
                (x, y, z) -> (float) Math.min(Math.min(x, y), z),
                (x, y, z) -> (byte) Math.min(Math.min(x, y), z)
        );
        ufb(
                "x max y max z",
                (x, y, z) -> Math.max(Math.max(x, y), z),
                (x, y, z) -> (float) Math.max(Math.max(x, y), z),
                (x, y, z) -> (byte) Math.max(Math.max(x, y), z)
        );
        ufb(
                "x min y max z",
                (x, y, z) -> Math.max(Math.min(x, y), z),
                (x, y, z) -> (float) Math.max(Math.min(x, y), z),
                (x, y, z) -> (byte) Math.max(Math.min(x, y), z)
        );
        ufb(
                "x max y min z",
                (x, y, z) -> Math.min(Math.max(x, y), z),
                (x, y, z) -> (float) Math.min(Math.max(x, y), z),
                (x, y, z) -> (byte) Math.min(Math.max(x, y), z)
        );

        ufb(
                "count 5",
                (x, y, z) -> 2,
                (x, y, z) -> fCount(5),
                (x, y, z) -> bCount(5)
        );
        ufb(
                "count -5",
                (x, y, z) -> uCount(-5),
                (x, y, z) -> fCount(-5),
                (x, y, z) -> bCount(-5)
        );
        ufb("count (x - y)",
                (x, y, z) -> uCount(x - y),
                (x, y, z) -> fCount(x - (float) y),
                (x, y, z) -> bCount(x - y)
        );
        ufb(
                "x -count y",
                (x, y, z) -> x - Integer.bitCount(y),
                (x, y, z) -> x - fCount(y),
                (x, y, z) -> (byte) (x - bCount(y))
        );
        ufb(
                "count -y",
                (x, y, z) -> uCount(-y),
                (x, y, z) -> fCount(-(float) y),
                (x, y, z) -> bCount(-y)
        );
        ufb(
                "count(y * z)",
                (x, y, z) -> uCount(y * z),
                (x, y, z) -> fCount(y * (float) z),
                (x, y, z) -> bCount(y * z)
        );
    }

    private void ufbConst(final String expression, final int v) {
        ufb(expression, (x, y, z) -> v, (x, y, z) -> (float) v, (x, y, z) -> (byte) v);
    }

    private static Byte bCount(final int v) {
        return (byte) Integer.bitCount(v & 0xff);
    }

    private static Integer uCount(final int v) {
        return Integer.bitCount(v);
    }

    private static Float fCount(final float v) {
        return (float) Integer.bitCount(Float.floatToIntBits(v));
    }

    public static void main(final String[] args) {
        new GenericCmmUfbTest().run();
    }
}
