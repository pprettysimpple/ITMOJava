import java.io.*;
import java.nio.CharBuffer;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Scanner {
    private Reader br;

    private CharBuffer buff;

    private int position = 0;

    private int savedPosition = -1;

    private int hasNextPosition = -1;

    private String hasNextResult = null;

    private boolean hasNextResultInt = false;

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
        buff = CharBuffer.allocate(len);
        buff.limit(0);
    }

    public Scanner(InputStream in, int len) {
        br = new InputStreamReader(in);
        buff = CharBuffer.allocate(len);
        buff.limit(0);
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Can not close reader: " + e.getMessage());
        }
    }

    private void decreaseSavedIndex(int offset) {
        if (savedPosition != -1) {
            savedPosition -= offset;
        }
        if (hasNextPosition != -1) {
            hasNextPosition -= offset;
        }
        position -= offset;
    }

    private void makeSpace() {
        int offset = (savedPosition == -1) ? position : savedPosition;
        int p = buff.position() - offset;
        buff.position(offset);
        if (offset > 0) {
            buff.compact();
            decreaseSavedIndex(offset);
            buff.flip();
            buff.position(p);
            return;
        }
        CharBuffer newBuffer = CharBuffer.allocate(2 * buff.capacity());
        newBuffer.put(buff);
        newBuffer.flip();
        buff = newBuffer;
        buff.position(p);
    }

    private boolean readInput() throws IOException {
        if (buff.limit() == buff.capacity()) {
            makeSpace();
        }
        int p = buff.position();
        buff.position(buff.limit());
        buff.limit(buff.capacity());

        int n = br.read(buff);
        buff.limit(buff.position());
        buff.position(p);
        return n > 0;
    }

    private void clearCache() {
        hasNextResult = null;
        hasNextPosition = -1;
        hasNextResultInt = false;
    }

    private void saveState() {
        savedPosition = position;
    }

    private void revertState() {
        position = savedPosition;
    }

    private void skipBufferToFirstWord() {
        buff.position(position = hasNextPosition);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean findFirstWord() throws IOException {
        if (hasNextPosition != -1) {
            return true;
        }
        while (buff.position() != buff.limit() || readInput()) {
            char c = buff.get();
            if (!Character.isWhitespace(c)) {
                StringBuilder sb = new StringBuilder();
                buff.position(buff.position() - 1);
                while ((buff.position() != buff.limit() || readInput())) {
                    if (!Character.isWhitespace(c = buff.get())) {
                        sb.append(c);
                    } else {
                        break;
                    }
                }
                hasNextPosition = buff.position();
                hasNextResult = sb.toString();
                hasNextResultInt = isInteger(hasNextResult);
                return true;
            }
        }
        return false;
    }

    public boolean hasNext() throws IOException {
        if (hasNextPosition != -1) {
            return true;
        }
        saveState();
        while (buff.position() != buff.limit() || readInput()) {
            if (findFirstWord()) {
                revertState();
                buff.position(position);
                savedPosition = -1;
                return true;
            }
        }
        revertState();
        buff.position(position);
        savedPosition = -1;
        return false;
    }

    public boolean hasNextInt() throws IOException {
        return hasNext() && hasNextResultInt;
    }

    public boolean hasNextLine() throws IOException {
        return hasNext() || (buff.limit() != buff.position());
    }

    private String next() throws IOException {
        if (hasNextPosition == -1) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }
        String nextWord = hasNextResult;
        skipBufferToFirstWord();
        clearCache();
        return nextWord;
    }

    public int nextInt() throws IOException {
        if (hasNextPosition == -1) {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }
        if (!hasNextResultInt) {
            throw new InputMismatchException();
        }
        int nextInteger = Integer.parseInt(hasNextResult);
        skipBufferToFirstWord();
        clearCache();
        return nextInteger;
    }

    public String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (position != buff.limit() || readInput()) {
            char c = buff.get();
            position++;
            sb.append(c);
            if (c == '\n') {
                break;
            }
        }
        clearCache();
        return sb.toString();
    }
}
