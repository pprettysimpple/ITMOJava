import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class K {
    private static int[][] pr;

    private static int[] push(int[] cur, int size, int val) {
        if (size == cur.length) {
            cur = Arrays.copyOf(cur, 2 * cur.length);
        }
        cur[size] = val;
        return cur;
    }

    private static int get(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return pr[i][j];
    }

    private static int getSum(int i1, int j1, int i2, int j2) {
        if (i1 > i2) {
            i1 ^= i2;
            i2 ^= i1;
            i1 ^= i2;
        }
        if (j1 > j2) {
            j1 ^= j2;
            j2 ^= j1;
            j1 ^= j2;
        }
        return get(i2, j2) - get(i1 - 1, j2)
                - get(i2, j1 - 1) + get(i1 - 1, j1 - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] a = new char[n][m];
        pr = new int[n][m];
        int ii = 0, jj = 0;
        int[] iip = new int[1], jjp = new int[1];
        int szp = 0;
        for (int i = 0; i < n; i++) {
            String cur = sc.nextLine().toLowerCase();
            for (int j = 0; j < m; j++) {
                a[i][j] = cur.charAt(j);
                pr[i][j] = get(i - 1, j) + get(i, j - 1) - get(i - 1, j - 1)
                        + ((cur.charAt(j) != '.') ? 1 : 0);
                if (cur.charAt(j) == 'a') {
                    ii = i;
                    jj = j;
                } else if (cur.charAt(j) != '.') {
                    iip = push(iip, szp, i);
                    jjp = push(jjp, szp++, j);
                }
            }
        }
        int optPosi = 0, optPosj = 0, optLeni = 0, optLenj = 0;
        for (int i1 = 0; i1 <= ii; i1++) {
            for (int i2 = ii; i2 < n; i2++) {
                if (getSum(i1, jj, i2, jj) > 1) {
                    continue;
                }
                int l = 1, r = jj + 1;
                while (l < r) {
                    int mid = r - (r - l) / 2;
                    if (getSum(i1, jj - mid + 1, i2, jj) > 1) {
                        r = mid - 1;
                    } else {
                        l = mid;
                    }
                }
                int left = l;
                l = 1;
                r = m - jj;
                while (l < r) {
                    int mid = r - (r - l) / 2;
                    if (getSum(i1, jj, i2, jj + mid - 1) > 1) {
                        r = mid - 1;
                    } else {
                        l = mid;
                    }
                }
                int right = l;
                if ((i2 - i1 + 1) * (left + right - 1) > (optLeni * optLenj)) {
                    optPosi = i1;
                    optPosj = jj - left + 1;
                    optLeni = i2 - i1 + 1;
                    optLenj = left + right - 1;
                }
            }
        }
//        System.out.println(optPosi + " " + optPosj + " " + optLeni + " " + optLenj);
        for (int i = optPosi; i < optPosi + optLeni; i++) {
            for (int j = optPosj; j < optPosj + optLenj; j++) {
                a[i][j] = 'a';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i][j - 1] == 'a') {
                    continue;
                }
                if (a[i][j] == '.') {
                    a[i][j] = a[i][j - 1];
                }
            }
            for (int j = m - 2; j >= 0; j--) {
                if (a[i][j + 1] == 'a') {
                    continue;
                }
                if (a[i][j] == '.') {
                    a[i][j] = a[i][j + 1];
                }
            }
        }
        next1: for (int i = 1; i < n; i++) {
             for (int j = 0; j < m; j++) {
                int k = 1;
                while (j + k < m && a[i - 1][j] == a[i - 1][j + k]) {
                    k++;
                }
                for (int pos = j; pos < j + k; pos++) {
                    if (a[i - 1][pos] != a[i][pos] && (a[i - 1][pos] == '.' || a[i][pos] != '.')) {
                        continue next1;
                    }
                }
             }
             a[i] = Arrays.copyOf(a[i - 1], m);
        }
        next2: for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                int k = 1;
                while (j + k < m && a[i + 1][j] == a[i + 1][j + k]) {
                    k++;
                }
                for (int pos = j; pos < j + k; pos++) {
                    if (a[i + 1][pos] != a[i][pos] && (a[i + 1][pos] == '.' || a[i][pos] != '.')) {
                        continue next2;
                    }
                }
                j += k - 1;
            }
            a[i] = Arrays.copyOf(a[i + 1], m);
        }
        for (int i = 0; i < szp; i++) {
            a[iip[i]][jjp[i]] = Character.toUpperCase(a[iip[i]][jjp[i]]);
        }
        a[ii][jj] = Character.toUpperCase(a[ii][jj]);
        PrintWriter pp = new PrintWriter(System.out);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pp.print(a[i][j]);
            }
            pp.print('\n');
        }
        pp.close();
    }
}
