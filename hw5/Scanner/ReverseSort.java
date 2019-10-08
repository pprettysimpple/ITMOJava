import java.io.IOException;
import java.util.*;

class Tuple {
    long sum;
    int idx;
    String line;

    public Tuple(long sum, int idx, String line) {
        this.sum = sum;
        this.idx = idx;
        this.line = line;
    }
}

public class ReverseSort {

    public static void main(String[] args) {
        Tuple[] a = new Tuple[1];
        int n = 0;
        Scanner text = new Scanner(System.in);
        try {
            while (text.hasNextLine()) {
                Integer[] row = new Integer[1];
                String s = text.nextLine();
                Scanner line = new Scanner(s);
                int cnt = 0;
                long sum = 0;
                while (line.hasNextInt()) {
                    int val = line.nextInt();
                    if (cnt == row.length) {
                        row = Arrays.copyOf(row, row.length * 2);
                    }
                    row[cnt++] = val;
                    sum += val;
                }
                row = Arrays.copyOf(row, cnt);
                Arrays.sort(row, Collections.reverseOrder());
                StringBuilder curRow = new StringBuilder();
                for (Integer integer : row) {
                    curRow.append(integer).append(' ');
                }
                curRow.append('\n');
                if (n == a.length) {
                    a = Arrays.copyOf(a, a.length * 2);
                }
                a[n++] = new Tuple(sum, n, curRow.toString());
            }
        } catch(IOException e) {
            System.out.println("Input Error: " + e.getMessage());
        } finally {
            text.close();
        }

        a = Arrays.copyOf(a, n);

        Arrays.sort(a, new Comparator<>() {
            public int compare(Tuple o1, Tuple o2) {
                if (o1.sum > o2.sum) {
                    return -1;
                } else if (o1.sum == o2.sum) {
                    return Integer.compare(o2.idx, o1.idx);
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.print(a[i].line);
        }
    }
}
