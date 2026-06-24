import java.util.Queue;

public class Parser {
    private final Queue<Token> tokens;

    public Parser(Queue<Token> tokens) {
        this.tokens = tokens;
    }
    
    private Token peek() {
        return tokens.element();
    }

    private Token consume(TokenType type) {
        Token token = tokens.poll();
        if (token.getType() == type) {
        	System.out.print(token.toString() +" ");
            return token;
        }
        System.out.println();
        throw new RuntimeException("Syntax Error: Expected " + type + " but found " + token.getType());
    }

    public void parse() {
    	function();
        consume(TokenType.EOF);
    }
    
    // Function
    private void function() {
    	consume(TokenType.TYPE);
    	consume(TokenType.NAME);
    	consume(TokenType.LPAREN);
    	boolean more_than_one = false;
    	while (peek().getType() == TokenType.TYPE || peek().getType() == TokenType.COMMA) {
    		if (more_than_one) {
    			consume(TokenType.COMMA);
    			consume(TokenType.TYPE);
    			consume(TokenType.NAME);
    		} else {
    			consume(TokenType.TYPE);
    			consume(TokenType.NAME);
    			more_than_one = true;
    		}
    	}
    	
    	consume(TokenType.RPAREN);
    	consume(TokenType.LBRACE);
    	program();
    	consume(TokenType.RBRACE);
    }
    
    // Program
    private void program() {
    	command();
    	while (peek().getType() == TokenType.SEMICOLON) {
    		consume(TokenType.SEMICOLON);
    		command();
    	}
    }
    private void command() {
    	switch (peek().getType()) {
    	case TokenType.IF:
    		if_command();
    		break;
    	case TokenType.WHILE:
    		while_command();
    		break;
    	case TokenType.FOR:
    		for_command();
    		break;
    	case TokenType.TYPE:
    		consume(TokenType.TYPE);
    	case TokenType.NAME:
    		compound_name();
    		switch (peek().getType()) {
    		case TokenType.ASSIGN:
    			assignment();
    			break;
    		case TokenType.SEMICOLON:
    			break;
			default:
				throw new RuntimeException("\nSyntax Error: Unexpected token " + peek().getType() +", expected ASSIGN");
    		}
    		break;
    	case TokenType.SEMICOLON, TokenType.RBRACE:
    		break;
    	default:
    		throw new RuntimeException("\nSyntax Error: Unexpected token " + peek().getType() +", expected commands or rbrace");
    	}
    }
    
    
    // If
    private void if_command() {
    	consume(TokenType.IF);
    	consume(TokenType.LPAREN);
    	logic_expression();
    	consume(TokenType.RPAREN);
    	consume(TokenType.LBRACE);
    	program();
    	consume(TokenType.RBRACE);
    }
    
    // While
    private void while_command() {
    	consume(TokenType.WHILE);
    	consume(TokenType.LPAREN);
    	logic_expression();
    	consume(TokenType.RPAREN);
    	consume(TokenType.LBRACE);
    	program();
    	consume(TokenType.RBRACE);
    }
    
    // For
    private void for_command() {
    	consume(TokenType.FOR);
    	consume(TokenType.LPAREN);
    	
    	assignment();
    	consume(TokenType.SEMICOLON);
    	logic_expression();
    	consume(TokenType.SEMICOLON);
    	assignment();
    	
    	consume(TokenType.RPAREN);
    	consume(TokenType.LBRACE);
    	program();
    	consume(TokenType.RBRACE);
    }
    
    
    // Assignment
    private void assignment() {
    	consume(TokenType.ASSIGN);
    	switch (peek().getType()) {
    	case TokenType.LITERAL:
    		consume(TokenType.LITERAL);
    		break;
    	default:
    		math_expression();
    		break;
    	}
    }
    
    // Variables and functions connected with points
    private void compound_name() {
    	while (peek().getType() == TokenType.NAME || peek().getType() == TokenType.POINT) {
    		if (peek().getType() == TokenType.POINT) {
    			consume(TokenType.POINT);
    		}
    		consume(TokenType.NAME);
    		if (peek().getType() == TokenType.LPAREN) {
    			consume(TokenType.LPAREN);
    			list_of_arguments();
    			consume(TokenType.RPAREN);
    		}
        }
    }
    
    // List of arguments
    private void list_of_arguments() {
    	if (peek().getType() != TokenType.RPAREN) {
    		math_expression();
	    	while (peek().getType() == TokenType.COMMA) {
	    		consume(TokenType.COMMA);
	    		math_expression();
	    	}
    	}
    	
    }
    

    // Logic expression
    private void logic_expression() {
    	if (peek().getType() == TokenType.NOT) {
    		consume(TokenType.NOT);
    	}
    	boolean_factor();
    }
    
    private void boolean_factor() {
    	comparison();
        while (peek().getType() == TokenType.AND || peek().getType() == TokenType.OR) {
            consume(peek().getType());
            logic_expression();
        }
    }
    
    private void comparison() {
    	math_expression();
    	switch (peek().getType()) {
    	case TokenType.GREATER, TokenType.LESSER, TokenType.GREATER_EQUAL, TokenType.LESSER_EQUAL,TokenType.EQUAL,TokenType.DIFFERENT:
    		consume(peek().getType());
    		math_expression();
    		break;
    	default: break;
    	}
    }
    
    
    // Math expression
    private void math_expression() {
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
        switch (peek().getType()) {
        case TokenType.NAME:
        	compound_name();
        	break;
		case TokenType.NUMBER:
		    consume(TokenType.NUMBER);	
		    break;
		case TokenType.LPAREN:
			consume(TokenType.LPAREN);
            math_expression();
            consume(TokenType.RPAREN);
			break;
		default:
			throw new RuntimeException("\nSyntax Error: Unexpected token " + peek().getType() +", expected NUMBER, NAME or LPAREN");
        }
    }
}
