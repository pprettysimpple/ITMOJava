import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WordStatWords {

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
                while (in.hasInput()) {
                    boolean wordCharacter;
                    StringBuilder word = new StringBuilder("");
                    do {
                        char c = in.nextChar();
                        wordCharacter = (Character.getType(c) == Character.DASH_PUNCTUATION
                                || Character.isLetter(c) || c == '\'');
                        if (wordCharacter) {
                            word.append(c);
                        }
                    } while (wordCharacter && in.hasInput());

                    if (word.length() == 0) {
                        continue;
                    }

                    if (size == arr.length) {
                        arr = Arrays.copyOf(arr, 2 * size);
                    }
                    arr[size++] = word.toString().toLowerCase();
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
                    out.write( new StringBuilder(arr[i]).append(" ").append(cnt).append("\n").toString());
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
