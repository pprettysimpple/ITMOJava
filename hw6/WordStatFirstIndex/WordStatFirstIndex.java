import MyClasses.Checker;
import MyClasses.IntList;
import MyClasses.Scanner;

import java.io.*;
import java.util.*;

class WordStatIndexChecker implements Checker {
    public boolean isWordCharacter(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION
                || c == '\''
                || Character.isLetter(c);
    }
}

public class WordStatFirstIndex {
    private static Checker wordStatIndexChecker = new WordStatIndexChecker();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please, write correct filenames.");
            return;
        }
        Map<String, IntList> map = new LinkedHashMap<>();
        try {
            try (Scanner in = new Scanner(new FileReader(new File(args[0])), wordStatIndexChecker)) {
                int position = 1;
                int lineNum = 1;
                while (in.hasNext()) {
                    String word = in.next().toLowerCase();
                    System.out.print(word + " ");
                    IntList current = map.get(word);
                    if (current == null) {
                        current = new IntList(position, lineNum);
                        map.put(word, current);
                    } else {
                        current.add(position, lineNum);
                    }
                    position++;
                    if (in.isEndOfLine()) {
                        System.out.println('\n');
                        position = 1;
                        lineNum++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
            return;
        }
        try (Writer writer = new BufferedWriter(new FileWriter(new File(args[1])))) {
            for (Map.Entry<String, IntList> current : map.entrySet()) {
                writer.write(current.getKey());
                writer.write(' ');
                writer.write(String.valueOf(current.getValue()));
                writer.write('\n');
            }
        } catch (IOException e) {
            System.out.println("Output error: " + e.getMessage());
        }
    }
}
