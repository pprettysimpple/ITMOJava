import java.util.Arrays;
import java.util.Scanner;

public class D {
    private static long k;
    private static final long MOD = 998244353;

    private static long bp(long p) {
        if (p == 0) {
            return 1;
        }
        if (p % 2 == 1) {
            return (k * bp(p - 1)) % MOD;
        }
        long b = bp(p / 2);
        return (b * b) % MOD;
    }

    private static int[] push(int[] cur, int size, int value) {
        if (cur.length == size) {
            cur = Arrays.copyOf(cur, 2 * cur.length);
        }
        cur[size] = value;
        return cur;
    }

    private static int[] getDividers(int n) {
        int[] cur = new int[1];
        int cnt = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    cur = push(cur, cnt++, i);
                } else {
                    cur = push(cur, cnt++, i);
                    cur = push(cur, cnt++, n / i);
                }
            }
        }
        return Arrays.copyOf(cur, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        k = sc.nextInt();
        long[] r = new long[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                r[i] = bp((i + 2) / 2) * ((i) / 2) + bp(i / 2) * ((i) / 2);
            } else {
                r[i] = i * bp((i + 1) / 2);
            }
            r[i] %= MOD;
        }
        long[] d = new long[(int) n + 1];
        d[1] = k;
        for (int i = 2; i <= n; i++) {
            int[] divs = getDividers(i);
            for (int l : divs) {
                if (l != i) {
                    d[i] += (i / l) * d[l];
                }
                d[i] %= MOD;
            }
            d[i] = ((r[i] - d[i]) % MOD + MOD) % MOD;
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int[] divs = getDividers(i);
            for (int l : divs) {
                ans += d[l];
                if (ans >= MOD) {
                    ans -= MOD;
                }
            }
        }
        System.out.println(ans);
    }
}
