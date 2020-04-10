package expression.parser;

import expression.CommonExpression;
import expression.exceptions.Parser;
import expression.exceptions.*;

public class ExpressionParser implements Parser {
    private Token curToken;
    private Tokenizer tokenizer;

    private void getNextToken() {
        curToken = tokenizer.nextToken();
    }

    private CommonExpression parsePow() {
        CommonExpression res;
        switch (curToken) {
            case CONST:
                res = new Const(Integer.parseInt(tokenizer.getLastToken()));
                getNextToken();
                return res;
            case BRACKET_OPEN:
                getNextToken();
                res = parseExpression();
                if (curToken != Token.BRACKET_CLOSE) {
                    throw new IllegalStateException("Closed bracket error.");
                }
                getNextToken();
                return res;
            case VAR:
                res = new Variable(tokenizer.getLastToken());
                getNextToken();
                return res;
            case POW2:
                getNextToken();
                return new Power2(parsePow());
            case LOG2:
                getNextToken();
                return new CheckedLog2(parsePow());
            case SUB:
                getNextToken();
                if (curToken == Token.CONST) {
                    res = new Const(Integer.parseInt("-" + tokenizer.getLastToken()));
                    getNextToken();
                    return res;
                } else {
                    return new CheckedNegate(parsePow());
                }
        }
        throw new IllegalStateException("Wrong operator: " + tokenizer.getLastToken());
    }

    private CommonExpression parseMul() {
        CommonExpression res = parsePow();
        while (true) {
            if (curToken == Token.POW) {
                getNextToken();
                res = new CheckedPower(res, parseItem());
            } else
            if (curToken == Token.LOG){
                getNextToken();
                res = new CheckedLog(res, parseItem());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseItem() {
        CommonExpression res = parseMul();
        while (curToken != Token.END) {
            if (curToken == Token.MUL) {
                getNextToken();
                res = new CheckedMultiply(res, parseMul());
            } else
            if (curToken == Token.DIV){
                getNextToken();
                res = new CheckedDivide(res, parseMul());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseExpression() {
        CommonExpression res = parseItem();
        while (curToken != Token.END) {
            if (curToken == Token.ADD) {
                getNextToken();
                res = new CheckedAdd(res, parseItem());
            } else
            if (curToken == Token.SUB){
                getNextToken();
                res = new CheckedSubtract(res, parseItem());
            } else {
                break;
            }
        }
        return res;
    }

    @Override
    public CommonExpression parse(String expression) {
        tokenizer = new Tokenizer(expression);
        getNextToken();
        return parseExpression();
    }
}
