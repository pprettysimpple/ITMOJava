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

public class WordStatIndex {
    private static Checker wordStatIndexChecker = new WordStatIndexChecker();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please, write correct filenames.");
            return;
        }
        Map<String, IntList> map = new LinkedHashMap<>();
        try {
            try (Scanner in = new Scanner(new FileReader(new File(args[0])))) {
                int position = 1;
                while (in.hasNext(wordStatIndexChecker)) {
                    String word = in.next(wordStatIndexChecker).toLowerCase();
                    IntList current = map.get(word);
                    if (current == null) {
                        map.put(word, new IntList(position));
                    } else {
                        current.add(position);
                    }
                    position++;
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
                StringBuilder result = new StringBuilder();
                result.append(current.getKey()).append(' ');
                result.append(current.getValue().getSize()).append(' ');
                for (int i = 0; i < current.getValue().getSize(); i++) {
                    result.append(current.getValue().get(i));
                    if (i + 1 != current.getValue().getSize()) {
                        result.append(' ');
                    }
                }
                result.append('\n');
                writer.write(result.toString());
            }
        } catch (IOException e) {
            System.out.println("Output error: " + e.getMessage());
        }
    }
}
