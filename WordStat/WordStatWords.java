import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WordStatWords {

    public static void main(String[] args) {
        String[] arr = new String[1];
        int size = 0;
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(args[0])),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        int j = 0;
                        while (
                                (i + j < line.length()
                                        && (Character.getType(line.charAt(i + j)) == Character.DASH_PUNCTUATION
                                        || Character.isLetter(line.charAt(i + j))
                                        || line.charAt(i + j) == '\''))) {
                            j++;
                        }
                        if (j == 0) {
                            continue;
                        }
                        if (size == arr.length) {
                            arr = Arrays.copyOf(arr, 2 * size);
                        }
                        arr[size++] = line.substring(i, i + j).toLowerCase();
                        i += j;
                    }
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Input file error: " + e.getMessage());
            return;
        }

        arr = Arrays.copyOf(arr, size);
        Arrays.sort(arr);
        try {
            File outputFile = new File(args[1]);
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFile),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                for (int i = 0; i < arr.length; ) {
                    int cnt = 1;
                    while (i + cnt < arr.length && arr[i].equals(arr[i + cnt])) {
                        cnt++;
                    }
                    out.write(arr[i] + " " + cnt + "\n");
                    i += cnt;
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Output file error: " + e.getMessage());
        }
    }
}
