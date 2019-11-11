import java.util.*;
import java.io.*;

import java.lang.Math;

public class H {
    private static int[] pr;

    private static int get(int t) {
        int cnt = 0;
        for (int i = 0; i < pr.length; i++) {
            int l = i, r = pr.length - 1;
            int before = (i == 0) ? 0 : pr[i - 1];
            while (l < r) {
                int m = r - (r - l) / 2;
                if (pr[m] - before > t) {
                    r = m - 1;
                } else {
                    l = m;
                }
            }
            i = l;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        pr = new int[sc.nextInt()];
        int mx = 0;
        for (int i = 0; i < pr.length; i++) {
            pr[i] = sc.nextInt();
            mx = Math.max(mx, pr[i]);
            pr[i] += (i == 0 ? 0 : pr[i - 1]);
        }
        int q = sc.nextInt();
        int[] mem = new int[1000_001];
        Arrays.fill(mem, -1);
        while (q --> 0) {
            int t = sc.nextInt();
            if (mem[t] != -1) {
                System.out.println(mem[t]);
                continue;
            }
            if (t < mx) {
                System.out.println("Impossible");
                continue;
            }
            System.out.println(mem[t] = get(t));
        }
    }
}
