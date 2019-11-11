import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < n; j++) {
                d[i][j] = s.charAt(j) - '0';
            }
        }
        for (int v = 0; v < n; v++) {
            for (int i = v + 1; i < n; i++) {
                if (d[v][i] == 0) {
                    continue;
                }
                for (int j = i + 1; j < n; j++) {
                    d[v][j] -= d[i][j];
                    if (d[v][j] < 0) {
                        d[v][j] += 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j]);
            }
            System.out.println("");
        }
    }
}
