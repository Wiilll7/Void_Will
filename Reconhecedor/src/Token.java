
public class Token {
	private TokenType type;
    private String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    // Getters
    public TokenType getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	
	// Setters
	public void setType(TokenType type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
    public String toString() {
        return type + "[" + value + "]";
    }
}
