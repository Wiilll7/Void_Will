import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Lexer {
	private String input;
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
    
    

    public Queue<Token> tokenize() {
        Queue<Token> tokens = new LinkedList<Token>();
        
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
            		if (pos < input.length() && Character.isJavaIdentifierPart(input.charAt(pos))) {
            			throw new RuntimeException("Lexer Error: Numbers cannot be immediately followed by letters. Position: "+ pos);
                    }
        			tokens.add(new Token(TokenType.NUMBER,numbers.returnTokenAndReset()));
        		} else {
        			numbers.returnTokenAndReset();
        		}
            } else {
            	pos++;
            	continue;
            }
            
            // Reserved keywords detector
            if (Character.isJavaIdentifierStart(input.charAt(pos))) {
            	StringBuilder sb = new StringBuilder();
            	sb.append(input.charAt(pos++));
            	while (pos < input.length() && Character.isJavaIdentifierPart(input.charAt(pos))) {
            		sb.append(input.charAt(pos++));
            	}
            	
            	String unidentified_token = sb.toString();
            	switch (unidentified_token) {
            		case "while":
            			tokens.add(new Token(TokenType.WHILE,"while"));
            			continue;
            		case "if":
            		    tokens.add(new Token(TokenType.IF, "if"));
            		    continue;
            		case "else":
            		    tokens.add(new Token(TokenType.ELSE, "else"));
            		    continue;
            		case "for":
            		    tokens.add(new Token(TokenType.FOR, "for"));
            		    continue;
            		case "int","char","string","boolean","void":
            		    tokens.add(new Token(TokenType.TYPE, unidentified_token));
            			continue;
            		default:
            			tokens.add(new Token(TokenType.NAME, unidentified_token));
            			continue;
            	}
            }
            
            
            // Special operation characters
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
	            case '.':
	            	tokens.add(new Token(TokenType.POINT, "."));
	            	break;
	            case ',':
	            	tokens.add(new Token(TokenType.COMMA, ","));
	            	break;
	            case ';':
	            	tokens.add(new Token(TokenType.SEMICOLON, ";"));
	            	break;
	            case '!':
	            	if (input.charAt(pos + 1) == '=') {
	            		tokens.add(new Token(TokenType.DIFFERENT,"!="));
	            		pos += 2;
	            		break;
	            	}
	            	tokens.add(new Token(TokenType.NOT,"!"));
            		break;
	            case '=':
	            	if (input.charAt(++pos) == '=') {
	            		tokens.add(new Token(TokenType.EQUAL,"=="));
	            		pos++;
	            		break;
	            	}
	            	tokens.add(new Token(TokenType.ASSIGN,"="));
	            	break;
	            case '<':
	            	if (input.charAt(++pos) == '=') {
	            		tokens.add(new Token(TokenType.LESSER_EQUAL,"<="));
	            		pos++;
	            		break;
	            	}
	            	tokens.add(new Token(TokenType.LESSER,"<"));
	            	break;
	            case '>':
	            	if (input.charAt(++pos) == '=') {
	            		tokens.add(new Token(TokenType.GREATER_EQUAL,">="));
	            		pos++;
	            		break;
	            	}
	            	tokens.add(new Token(TokenType.GREATER,"<"));
	            	break;
	            case '|':
	            	if (input.charAt(++pos) == '|') {
	            		tokens.add(new Token(TokenType.OR,"||"));
	            		pos++;
	            		break;
	            	} else {
	            		throw new RuntimeException("Lexing Error: Expected character |. Character position: "+ pos);
	            	}
	            case '&':
	            	if (input.charAt(++pos) == '&') {
	            		tokens.add(new Token(TokenType.AND,"&&"));
	            		pos++;
	            		break;
	            	} else {
	            		throw new RuntimeException("Lexing Error: Expected character &. Character position: "+ pos);
	            	}
	            default:
	            	throw new RuntimeException("Lexing Error: Unexpected character "+ input.charAt(pos) +". Character position: "+ pos);
	        }
            pos++;
        }
        if (numbers.inFinalState()) {
			tokens.add(new Token(TokenType.NUMBER,numbers.returnTokenAndReset()));
		}
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
