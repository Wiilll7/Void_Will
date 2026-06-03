
public enum TokenType {
	// Delimiters
	LPAREN, RPAREN, LBRACE, RBRACE, EOF,
	
	// Keywords
	WHILE, IF, ELSE, FOR,
	
	// Numbers and letters
	NUMBER, NAME, LITERAL,
	
	// Operators (Math)
	PLUS, MINUS, MULTIPLY, DIVIDE,
	
	// Operators (Binary)
	GREATER, LESSER, EQUAL, DIFFERENT,
}
