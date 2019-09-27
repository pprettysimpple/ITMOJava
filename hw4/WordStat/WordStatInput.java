import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.util.Arrays.copyOf;

public class WordStatInput {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please, write filenames.");
            return;
        }
        String[] arr = new String[1];
        int size = 0;
        try {
            File inputFile = new File(args[0]);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8), 1024)) {
                String line;
                while ((line = in.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        int j = 0;
                        while (
                                i + j < line.length()
                                && (Character.getType(line.charAt(i + j)) == Character.DASH_PUNCTUATION
                                || Character.isLetter(line.charAt(i + j))
                                || line.charAt(i + j) == '\'')) {
                            j++;
                        }
                        if (j == 0) {
                            continue;
                        }
                        arr[size++] = line.substring(i, i + j).toLowerCase();
                        if (size == arr.length) {
                            arr = copyOf(arr, 2 * arr.length);
                        }
                        i += j;
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            return;
        } catch(IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            return;
        }
        arr = copyOf(arr, size);
        boolean[] used = new boolean[size];
        try {
            File outputFile = new File(args[1]);
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8), 1024)) {
                for (int i = 0; i < arr.length; i++) {
                    if (used[i]) {
                        continue;
                    }
                    int cnt = 0;
                    for (int j = 0; j < arr.length; j++) {
                        if (arr[i].equals(arr[j])) {
                            used[j] = true;
                            cnt++;
                        }
                    }
                    out.write(arr[i] + " " + cnt + "\n");
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
