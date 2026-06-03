import java.util.List;

public class Main {

	public static void main(String[] args) {
    	Lexer lexer = new Lexer("j = 2;"
    			+ "if (j != 15.75) {"
    			+ "		for (i = 0; i < 10; i++){"
    			+ "			print(i);"
    			+ "		}"
    			+ "		return 'Hello World';"
    			+ "}"
    			+ "");
    	List<Token> list = lexer.tokenize();
    	for (Token token : list) {
    		System.out.print(token.toString() +" ");
    	}
    	
    	//Parser parser = new Parser(lexer.tokenize());
    	//parser.parse();
    	
	}

}
