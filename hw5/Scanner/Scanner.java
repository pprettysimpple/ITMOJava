import java.io.*;

interface Checker {
    boolean isWordCharacter(char c);
}

public class Scanner {

    private Reader br;

    private char[] buff;

    private int position = 0;

    private int size = -1;

    public Scanner(Reader r) {
        this(r, 1024);
    }

    public Scanner(InputStream s) {
        this(s, 1024);
    }

    public Scanner(Reader r, int len) {
        br = r;
        buff = new char[len];
    }

    public Scanner(InputStream in, int len) {
        br = new InputStreamReader(in);
        buff = new char[len];
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Can not close reader: " + e.getMessage());
        }
    }

    private boolean readInput() throws IOException {
        size = br.read(buff);
        position = 0;
        return size > 0;
    }

    public boolean hasInput() throws IOException {
        return !(size <= 0 || position >= size) || readInput();
    }

    private void skipWhitespaceExceptSeparator(Checker checker) throws IOException {
        while (hasInput() && !checker.isWordCharacter(buff[position]) && buff[position] != '\n') {
            position++;
        }
    }

    public void skipSeparator(Checker checker) throws IOException {
        while (hasInput()) {
            if (!checker.isWordCharacter(buff[position]) || buff[position++] == '\n') {
                break;
            }
        }
    }

    public boolean isNextLine(Checker checker) throws IOException {
        skipWhitespaceExceptSeparator(checker);
        return !hasInput() || buff[position] == '\n';
    }

    public boolean isEmpty(Checker checker) throws IOException {
        skipWhitespaceExceptSeparator(checker);
        return !hasInput();
    }

    public String next(Checker checker) throws IOException {
        skipWhitespaceExceptSeparator(checker);
        StringBuilder result = new StringBuilder();
        while (hasInput()
                        && position < size
                        && checker.isWordCharacter(buff[position])
        ) {
            result.append(buff[position++]);
        }
        return result.toString();
    }

    public boolean hasNext(Checker checker) throws IOException {
        while (hasInput() && !checker.isWordCharacter(buff[position])) {
            position++;
        }
        return hasInput();
    }

    public int nextInt(Checker checker) throws IOException {
        return Integer.parseInt(next(checker));
    }
}