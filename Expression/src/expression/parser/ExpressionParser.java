package myParser;

import expression.*;
import expression.parser.Parser;

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
                res = parsePow();
                return new Power2(res);
            case LOG2:
                getNextToken();
                res = parsePow();
                return new Log2(res);
            case SUB:
                getNextToken();
                res = parsePow();
                return new Minus(res);
        }
        throw new IllegalStateException("Wrong operator: " + tokenizer.getLastToken());
    }

    private CommonExpression parseMul() {
        CommonExpression res = parsePow();
        while (true) {
            if (curToken == Token.POW) {
                res = new Power(res, parseItem());
            } else
            if (curToken == Token.LOG){
                res = new Log(res, parseItem());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseItem() {
        CommonExpression res = parseMul();
        while (true) {
            if (curToken == Token.MUL) {
                res = new Multiply(res, parseItem());
            } else
            if (curToken == Token.DIV){
                res = new Divide(res, parseItem());
            } else {
                break;
            }
        }
        return res;
    }

    private CommonExpression parseExpression() {
        CommonExpression res = parseItem();
        while (true) {
            if (curToken == Token.ADD) {
                res = new Add(res, parseItem());
            } else
            if (curToken == Token.SUB){
                res = new Subtract(res, parseItem());
            } else {
                break;
            }
        }
        return res;
    }

    @Override
    public TripleExpression parse(String expression) {
        tokenizer = new Tokenizer(expression);
        getNextToken();
        return parseExpression();
    }
}
