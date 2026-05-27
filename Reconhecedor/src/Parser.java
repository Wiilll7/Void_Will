import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentTokenIndex = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(currentTokenIndex);
    }

    private Token consume(TokenType type) {
        Token token = peek();
        if (token.getType() == type) {
            currentTokenIndex++;
            return token;
        }
        throw new RuntimeException("Syntax Error: Expected " + type + " but found " + token.getType());
    }

    public void parse() {
        expression();
        consume(TokenType.EOF);
    }

    private void expression() {
        term();
        while (peek().getType() == TokenType.PLUS || peek().getType() == TokenType.MINUS) {
            consume(peek().getType());
            term();
        }
    }

    private void term() {
        factor();
        while (peek().getType() == TokenType.MULTIPLY || peek().getType() == TokenType.DIVIDE) {
            consume(peek().getType());
            factor();
        }
    }

    private void factor() {
        Token token = peek();
        if (token.getType() == TokenType.NUMBER) {
            consume(TokenType.NUMBER);
        } else if (token.getType() == TokenType.LPAREN) {
            consume(TokenType.LPAREN);
            expression();
            consume(TokenType.RPAREN);
        } else {
            throw new RuntimeException("Syntax Error: Unexpected token " + token.getType());
        }
    }
}
