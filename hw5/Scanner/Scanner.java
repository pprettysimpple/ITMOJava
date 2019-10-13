import java.io.*;

public class Scanner {
    private Reader br;

    private char[] buff;

    private int position = 0;

    private int size = -1;

    public Scanner(String s) {
        this(s, 1024);
    }

    public Scanner(Reader r) {
        this(r, 1024);
    }

    public Scanner(InputStream s) {
        this(s, 1024);
    }

    public Scanner(String s, int len) {
        this(new ByteArrayInputStream(s.getBytes()), len);
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

    private void skipWhitespaceExceptSeparator() throws IOException {
        while (hasInput() && Character.isWhitespace(buff[position]) && buff[position] != '\n') {
            position++;
        }
    }

    public void skipSeparator() throws IOException {
        while (hasInput()) {
            if (buff[position++] == '\n') {
                break;
            }
        }
    }

    public boolean isNextLine() throws IOException {
        skipWhitespaceExceptSeparator();
        return !hasInput() || buff[position] == '\n';
    }

    public boolean isEmpty() throws IOException {
        skipWhitespaceExceptSeparator();
        return !hasInput();
    }

    public String next() throws IOException {
        skipWhitespaceExceptSeparator();
        StringBuilder result = new StringBuilder();
        while (
                hasInput()
                && position < size
                && !Character.isWhitespace(buff[position])
        ) {
            result.append(buff[position++]);
        }
        return result.toString();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    char nextChar() throws IOException {
        hasInput();
        return buff[position++];
    }
}