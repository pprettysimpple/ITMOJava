import java.util.*;
import java.io.*;

public class M {
    public static void main(String[] args) {
        int[] a;
        int n;
        Scanner sc = new Scanner(System.in);
        int testCount = sc.nextInt();
        while (testCount --> 0) {
            a = new int[n = sc.nextInt()];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Map<Integer, Integer> mp = new HashMap<>();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int cur = 2 * a[i] - a[j];
                    if (mp.containsKey(cur)) {
                        ans += mp.get(cur);
                    }
                }
                if (mp.containsKey(a[i])) {
                    mp.put(a[i], mp.get(a[i]) + 1);
                } else {
                    mp.put(a[i], 1);
                }
            }
            System.out.println(ans);
        }
    }
}
