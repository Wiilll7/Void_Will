import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char current = input.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }
            if (Character.isDigit(current)) {
                StringBuilder sb = new StringBuilder();
                while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                    sb.append(input.charAt(pos++));
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }

            switch (current) {
                case '+' -> tokens.add(new Token(TokenType.PLUS, "+"));
                case '-' -> tokens.add(new Token(TokenType.MINUS, "-"));
                case '*' -> tokens.add(new Token(TokenType.MULTIPLY, "*"));
                case '/' -> tokens.add(new Token(TokenType.DIVIDE, "/"));
                case '(' -> tokens.add(new Token(TokenType.LPAREN, "("));
                case ')' -> tokens.add(new Token(TokenType.RPAREN, ")"));
                default -> throw new IllegalArgumentException("Unexpected character: " + current);
            }
            pos++;
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
