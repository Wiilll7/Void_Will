import java.util.List;

public class Main {

	public static void main(String[] args) {
    	Lexer lexer = new Lexer(".345 + 5 * (10 - 223) \'likbilç08603~m cajblas\'");
    	List<Token> list = lexer.tokenize();
    	for (Token token : list) {
    		System.out.print(token.toString() +" ");
    	}
    	
    	//Parser parser = new Parser(lexer.tokenize());
    	//parser.parse();
    	
	}

}
