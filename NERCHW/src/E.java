import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class E {

    //Read
    ////////////////////////////////////////////////////////

    private static int readInt(BufferedReader br) throws IOException {
        char ch;
        while (Character.isWhitespace(ch = (char)br.read()));
        int res = 0;
        while (!Character.isWhitespace(ch)) {
            res = res * 10 + ch - '0';
            ch = (char) br.read();
        }
        return res;
    }

    //Solution
    ////////////////////////////////////////////////////////

    private static int[][] g;
    private static int[] size;
    private static int n;
    private final static int INF = (int)1e9 + 7;

    private static int[] push(int[] cur, int size, int value) {
        if (cur.length == size) {
            cur = Arrays.copyOf(cur, 2 * cur.length);
        }
        cur[size] = value;
        return cur;
    }

    private static int bfs(int s, int[] d, int[] pos) {
        int[] pr = new int[n];
        Arrays.fill(d, INF);
        d[s] = 0;
        pr[s] = s;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < size[u]; i++) {
                int v = g[u][i];
                if (d[u] + 1 < d[v]) {
                    d[v] = d[u] + 1;
                    pr[v] = u;
                    q.offer(v);
                }
            }
        }
        int maxPosition = pos[0];
        for (int i = 1; i < pos.length; i++) {
            if (d[maxPosition] < d[pos[i]]) {
                maxPosition = pos[i];
            }
        }

        if (d[maxPosition] % 2 != 0) {
            return maxPosition;
        }

        int cnt = 0;
        int cur = maxPosition;
        while (cur != s) {
            cnt++;
            cur = pr[cur];
            if (cnt == d[maxPosition] / 2) {
                return cur;
            }
        }
        return maxPosition;
    }

    public static void main(String[] args) {
        int[] pos;
        int m;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            g = new int[n = readInt(br)][1];
            size = new int[n];
            m = readInt(br);
            pos = new int[m];
            for (int i = 0; i < n - 1; i++) {
                int a = readInt(br) - 1;
                int b = readInt(br) - 1;
                g[a] = push(g[a], size[a]++, b);
                g[b] = push(g[b], size[b]++, a);
            }
            for (int i = 0; i < m; i++) {
                pos[i] = readInt(br) - 1;
            }
        } catch (IOException e) {
            System.err.println("Something goes wrong: " + e.getMessage());
            return;
        }

        int[] d = new int[n];
        int ans;
        bfs(ans = bfs(pos[0], d, pos), d, pos);
        for (int i = 0; i + 1 < m; i++) {
            if (d[pos[i]] != d[pos[i + 1]]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES\n" + (ans + 1));
    }
}
