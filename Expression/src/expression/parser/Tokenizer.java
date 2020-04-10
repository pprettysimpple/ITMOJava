package myParser;

public class Tokenizer {
    private String source;
    private int curPos = 0;
    private final String search[] = {"**", "//", "+", "-", "*", "/", "pow2", "log2"};
    private final Token token[] = {
            Token.POW, Token.LOG, Token.ADD, Token.SUB,
            Token.MUL, Token.DIV, Token.POW2, Token.LOG2
    };
    private int N = search.length;
    private String lastToken;

    Tokenizer(String source) {
        this.source = source;
    }

    private void skipBlanks() {
        while (curPos < source.length()
                && Character.isWhitespace(source.charAt(curPos))) {
            curPos++;
        }
    }

    Token nextToken() {
        skipBlanks();
        if (curPos >= source.length()) {
            throw new IllegalStateException("End of source.");
        }
        if (source.charAt(curPos) >= '0' && source.charAt(curPos) <= '9') {
            StringBuilder res = new StringBuilder();
            while (curPos < source.length()
                    && source.charAt(curPos) >= '0'
                    && source.charAt(curPos) <= '9') {
                res.append(source.charAt(curPos++));
            }
            lastToken = res.toString();

        }
        for (int i = 0; i < N; i++) {
            if (curPos + search[i].length() < source.length()
                    && source.startsWith(search[i], curPos)) {
                lastToken = source.substring(curPos, curPos + search[i].length());
                curPos += search[i].length();
                return token[i];
            }
            curPos++;
        }
        throw new IllegalStateException("End of source string.");
    }

    String getLastToken() {
        return lastToken;
    }
}
