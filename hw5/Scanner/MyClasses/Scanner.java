package MyClasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Scanner implements AutoCloseable {
    private Reader br;
    private char saved;
    private boolean hasSaved;
    private Checker checker;

    public Scanner(Reader r, Checker checker) {
        this.checker = checker;
        br = r;
        hasSaved = false;
        saved = 0;
    }

    public Scanner(InputStream in, Checker checker) {
        this(new InputStreamReader(in), checker);
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
            saved = (char) res;
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

    public void skipAllLine() throws IOException {
        while (hasInput()) {
            if (saved == '\n') {
                hasSaved = false;
                readInput();
                break;
            }
            hasSaved = false;
        }
    }

    public boolean isEndOfLine() throws IOException {
        skipWhitespacesExceptSeparator(checker);
        return !hasInput() || saved == '\n';
    }

    public boolean isEmpty() throws IOException {
        skipWhitespacesExceptSeparator(checker);
        return !hasInput();
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

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
