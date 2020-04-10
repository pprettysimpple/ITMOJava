package expression.parser;

public class Tokenizer {
    private String source;
    private int curPos = 0;
    private final String[] search = {"**", "//", "+", "-", "*", "/", "pow2", "log2", "(", ")"};
    private final Token[] token = {
            Token.POW, Token.LOG, Token.ADD, Token.SUB,
            Token.MUL, Token.DIV, Token.POW2, Token.LOG2,
            Token.BRACKET_OPEN, Token.BRACKET_CLOSE
    };
    private int N = search.length;
    private String lastToken;

    public Tokenizer(String source) {
        this.source = source;
    }

    public int getCurPos() {
        return curPos;
    }

    private void skipBlanks() {
        while (curPos < source.length()
                && (test(' ') || test('\t') || test('\n') || test('\r'))) {
            // skip
        }
    }

    private boolean test(char c) {
        if (source.charAt(curPos) == c) {
            curPos++;
            return true;
        }
        return false;
    }

    public String getPrefix() {
        return source.substring(0, curPos);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private String getConst() {
        StringBuilder res = new StringBuilder();
        while (curPos < source.length() && isDigit(source.charAt(curPos))) {
            res.append(source.charAt(curPos++));
        }
        return lastToken = res.toString();
    }

    public Token nextToken() {
        skipBlanks();
        if (curPos >= source.length()) {
            lastToken = null;
            return Token.END;
        }
        if (isDigit(source.charAt(curPos))) {
            getConst();
            return Token.CONST;
        }
        for (int i = 0; i < N; i++) {
            if (source.startsWith(search[i], curPos)) {
                if ((search[i].equals("log2") || search[i].equals("pow2")) // if for log2x test
                        && (isDigit(source.charAt(curPos + search[i].length()))
                            || isLetter(source.charAt(curPos + search[i].length())))) {
                    lastToken = null;
                    return Token.UNDEFINED;
                }
                lastToken = source.substring(curPos, curPos + search[i].length());
                curPos += search[i].length();
                return token[i];
            }
        }
        if (test('x') || test('y') || test('z')) {
            lastToken = Character.toString(source.charAt(curPos - 1));
            return Token.VAR;
        }
        lastToken = null;
        return curPos < source.length() ? Token.UNDEFINED : Token.END;
    }

    public String getLastToken() {
        return lastToken;
    }
}
