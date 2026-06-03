import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Lexer {
	private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }
    
    
    public Automaton numbers = new Automaton(
    	'0',
		Map.of(
			'0',
			Map.of(
					'n','1'
			),
			'1',
			Map.of(
					'n','1',
					'.','2'
			),
			'2',
			Map.of(
					'n','3'
			),
			'3',
			Map.of(
					'n','3'
			)
		),
		Set.of('1','3')
	);
    
    

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        
        while (pos < input.length()) {

            // Skip blank space
            if (Character.isWhitespace(input.charAt(pos))) {
                pos++;
                continue;
            }
            
            // Number Detector
            char temp = input.charAt(pos);
            if (Character.isDigit(input.charAt(pos))) temp = 'n';
            if (!numbers.nextInput(temp,input.charAt(pos))) {
            	if (numbers.inFinalState()) {
        			tokens.add(new Token(TokenType.NUMBER,numbers.returnTokenAndReset()));
        		} else {
        			numbers.returnTokenAndReset();
        		}
            } else {
            	pos++;
            	continue;
            }
            
            
            switch (input.charAt(pos)) {
	            case '+':
	            	tokens.add(new Token(TokenType.PLUS, "+"));
	            	break;
	            case '-':
	            	tokens.add(new Token(TokenType.MINUS, "-"));
	            	break;
	            case '*':
	            	tokens.add(new Token(TokenType.MULTIPLY, "*"));
	            	break;
	            case '/':
	            	tokens.add(new Token(TokenType.DIVIDE, "/"));
	            	break;
	            case '(':
	            	tokens.add(new Token(TokenType.LPAREN, "("));
	            	break;
	            case ')':
	            	tokens.add(new Token(TokenType.RPAREN, ")"));
	            	break;
	            case '{':
	            	tokens.add(new Token(TokenType.LBRACE, "{"));
	            	break;
	            case '}':
	            	tokens.add(new Token(TokenType.RBRACE, "}"));
	            	break;
	        }
            
            // Literal detector // Reserved keywords detector
            StringBuilder sb = new StringBuilder();
            if (input.charAt(pos) == '\'') {
            	sb.append(input.charAt(pos++));
            	while (pos < input.length() && input.charAt(pos) != '\'') {
            		sb.append(input.charAt(pos++));
            	}
            	sb.append(input.charAt(pos++));
            	
            	tokens.add(new Token(TokenType.LITERAL, sb.toString()));
            }
            if (pos >= input.length()) continue;
            

            pos++;
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
