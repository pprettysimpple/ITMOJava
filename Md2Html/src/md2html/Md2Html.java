package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Missed filenames\n");
            return;
        }
        StringBuilder result = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File(args[0])),
                            StandardCharsets.UTF_8))) {
                String s = "";
                StringBuilder paragraph = new StringBuilder();
                while (s != null && (s = reader.readLine()) != null) {
                    while (s != null && !s.equals("")) {
                        paragraph
                                .append(s)
                                .append('\n');
                        s = reader.readLine();
                    }
                    if (paragraph.length() != 0) {
                        paragraph.setLength(paragraph.length() - 1);
                        new BlockParser(paragraph).toHtml(result);
                        result.append('\n');
                        paragraph = new StringBuilder();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Read error: " + e.getMessage());
        }
        try {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    new File(args[1])),
                            StandardCharsets.UTF_8))) {
                writer.write(result.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }
}
