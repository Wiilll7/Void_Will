
public enum TokenType {
	// Delimiters
	LPAREN, RPAREN, LBRACE, RBRACE, POINT, SEMICOLON, EOF,
	
	// No category
	ASSIGN,
	
	// Keywords
	WHILE, IF, ELSE, FOR,
	
	// Numbers and letters
	NUMBER, NAME, LITERAL,
	
	// Operators (Math)
	PLUS, MINUS, MULTIPLY, DIVIDE,
	
	// Operators (Binary)
	GREATER, LESSER, EQUAL, DIFFERENT, NOT
}
