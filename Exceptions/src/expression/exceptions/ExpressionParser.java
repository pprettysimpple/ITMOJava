package expression.exceptions;

import expression.*;
import expression.parser.Token;
import expression.parser.Tokenizer;

public class ExpressionParser implements Parser {
    private Token curToken;
    private Tokenizer tokenizer;

    private void getNextToken() {
        curToken = tokenizer.nextToken();
    }

    private boolean test(Token token) {
        if (curToken == token) {
            getNextToken();
            return true;
        }
        return false;
    }

    private CommonExpression parsePow() {
        CommonExpression res;
        switch (curToken) {
            case CONST:
                try {
                    res = new Const(Integer.parseInt(tokenizer.getLastToken()));
                    getNextToken();
                    return res;
                } catch (NumberFormatException e) {
                    throw new ConstOverflowException(tokenizer.getLastToken());
                }
            case BRACKET_OPEN:
                getNextToken();
                res = parseExpression();
                if (!test(Token.BRACKET_CLOSE)) {
                    throw new ParserException("Expected closed bracket, found: " + curToken
                            + " as string is: " + tokenizer.getLastToken() + "\n"
                            + tokenizer.getPrefix() + "<-Error starts here");
                }
                return res;
            case VAR:
                res = new Variable(tokenizer.getLastToken());
                getNextToken();
                return res;
        }
        if (test(Token.BRACKET_OPEN)) {
            res = parseExpression();
            if (!test(Token.BRACKET_CLOSE)) {
                throw new ParserException("Expected closed bracket, found: " + curToken
                        + " as string is: " + tokenizer.getLastToken() + "\n"
                        + tokenizer.getPrefix() + "<-Error");
            }
            return res;
        }
        if (test(Token.POW2)) {
            return new Power2(parsePow());
        }
        if (test(Token.LOG2)) {
            return new CheckedLog2(parsePow());
        }
        if (test(Token.SUB)) {
            if (curToken == Token.CONST) {
                try {
                    res = new Const(Integer.parseInt("-" + tokenizer.getLastToken()));
                    getNextToken();
                    return res;
                } catch(NumberFormatException e) {
                    throw new ConstOverflowException("-" + tokenizer.getLastToken());
                }
            } else {
                return new CheckedNegate(parsePow());
            }
        }
        throw new ParserException("Unexpected token, token type: " + curToken
                + " token string is: " + tokenizer.getLastToken() + "\n"
                + tokenizer.getPrefix() + "<-Error starts here");
    }

    private CommonExpression parseMul() {
        CommonExpression res = parsePow();
        while (true) {
            if (test(Token.POW)) {
                res = new CheckedPower(res, parsePow());
            } else
            if (test(Token.LOG)) {
                res = new CheckedLog(res, parsePow());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseItem() {
        CommonExpression res = parseMul();
        while (curToken != Token.END) {
            if (test(Token.MUL)) {
                res = new CheckedMultiply(res, parseMul());
            } else if (test(Token.DIV)) {
                res = new CheckedDivide(res, parseMul());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseExpression() {
        CommonExpression res = parseItem();
        while (true) {
            if (test(Token.ADD)) {
                res = new CheckedAdd(res, parseItem());
            } else
            if (test(Token.SUB)) {
                res = new CheckedSubtract(res, parseItem());
            } else {
                return res;
            }
        }
    }

    @Override
    public CommonExpression parse(String expression) {
        tokenizer = new Tokenizer(expression);
        getNextToken();
        var res = parseExpression();
        if (curToken != Token.END) {
            throw new ParserException("Parse error, token type: " + curToken
                    + " token string: " + tokenizer.getLastToken() + "\n"
                    + tokenizer.getPrefix() + "<-Error starts here");
        } else {
            return res;
        }
    }
}
