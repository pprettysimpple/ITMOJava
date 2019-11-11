import java.util.Scanner;
import java.lang.Math;

public class I {
    private static final int INF = (int) (1e9 + 7);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int xl = INF;
        int xr = -INF;
        int yl = INF;
        int yr = -INF;
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int h = sc.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;
        int h = (Math.max(xr - xl, yr - yl) + 1) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}
