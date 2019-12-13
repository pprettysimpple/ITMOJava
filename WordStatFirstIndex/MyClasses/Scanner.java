package MyClasses;

import java.io.*;

public class Scanner implements AutoCloseable {
    private Reader br;
    private Checker checker;
    private char saved;
    private boolean hasSaved;

    public Scanner(Reader r, Checker checker) {
        this.checker = checker;
        br = r;
        hasSaved = false;
        saved = 0;
    }

    public void close() throws IOException {
        br.close();
    }

    private boolean readInput() throws IOException {
        if (!hasSaved) {
            int res = br.read();
            if (res < 0) {
                return false;
            }
            saved = (char)res;
            hasSaved = true;
        }
        return true;
    }

    private boolean hasInput() throws IOException {
        return (hasSaved) || readInput();
    }

    private void skipWhitespacesExceptSeparator(Checker checker) throws IOException {
        while (hasInput() && !checker.isWordCharacter(saved) && saved != '\n') {
            hasSaved = false;
        }
    }

    public boolean isEndOfLine() throws IOException {
        skipWhitespacesExceptSeparator(checker);
        return !hasInput() || saved == '\n';
    }

    public String next() throws IOException {
        skipWhitespacesExceptSeparator(checker);
        StringBuilder result = new StringBuilder();
        while (hasInput() && checker.isWordCharacter(saved)) {
            result.append(saved);
            hasSaved = false;
        }
        return result.toString();
    }

    public boolean hasNext() throws IOException {
        while (hasInput() && !checker.isWordCharacter(saved)) {
            hasSaved = false;
        }
        return hasInput();
    }
}