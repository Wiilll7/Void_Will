import java.util.List;

public class Main {

	public static void main(String[] args) {
    	Lexer lexer = new Lexer("3 + 5 * (10 - 223)");
    	Parser parser = new Parser(lexer.tokenize());
    	parser.parse();
    	
	}

}
