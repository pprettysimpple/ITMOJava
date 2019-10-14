import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class WordStatChecker implements Checker {
    public boolean isWordCharacter(char c) {
        return (Character.getType(c) == Character.DASH_PUNCTUATION
                || Character.isLetter(c) || c == '\'');
    }
}

public class WordStatWords {

    private static WordStatChecker wordStatChecker = new WordStatChecker();

    public static void main(String[] args) {
        String[] arr = new String[1];
        int size = 0;
        try {
            Scanner in = new Scanner(
                    new InputStreamReader(
                            new FileInputStream(new File(args[0])),
                            StandardCharsets.UTF_8
                    )
            );
            try {
                while (in.hasNext(wordStatChecker)) {
                    if (size == arr.length) {
                        arr = Arrays.copyOf(arr, 2 * size);
                    }
                    arr[size++] = in.next(wordStatChecker).toLowerCase();
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
            try (BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outputFile),
                            StandardCharsets.UTF_8
                    )
            )) {
                for (int i = 0; i < arr.length; ) {
                    int cnt = 1;
                    while (i + cnt < arr.length && arr[i].equals(arr[i + cnt])) {
                        cnt++;
                    }
                    out.write(new StringBuilder(arr[i]).append(" ").append(cnt).append("\n").toString());
                    i += cnt;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Output file error: " + e.getMessage());
        }
    }
}
