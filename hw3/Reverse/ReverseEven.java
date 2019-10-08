import java.io.*;
import java.util.*;

public class ReverseEven {

    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);
        int[][] a = new int[10][];
        int n = 0;
        while (text.hasNextLine()) {
            int[] row = new int[10];
            Scanner line = new Scanner(text.nextLine());

            int cnt = 0;
            while (line.hasNextInt()) {
                int val = line.nextInt();
                if (val % 2 == 0) {
                    if (cnt == a[n].length) {
                        row = Arrays.copyOf(row, row.length * 2);
                    }
                    row[cnt++] = val;
                }
            }

            if (n == a.length) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[n++] = Arrays.copyOf(row, cnt);
        }


        for (int i = n - 1; i >= 0; i--) {
            for (int j = a[i].length - 1; j >= 0; j--) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
