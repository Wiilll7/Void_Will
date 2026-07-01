import java.util.Queue;

public class Main {

	public static void main(String[] args) {
    	Lexer lexer = new Lexer("void boolean_factor() {\r\n"
    			+ "		int a1 = 'ababers';"
    			+ "    	comparison();\r\n"
    			+ "        while (peek().getType() == TokenType.AND || peek().getType() == TokenType.NOT) {\r\n"
    			+ "            consume(peek().getType());\r\n"
    			+ "            logic_expression();\r\n"
    			+ "        }\r\n"
    			+ "    }");
    	Queue<Token> list = lexer.tokenize();
    	for (Token token : list) {
    		System.out.print(token.toString() +" ");
    	}
    	System.out.println();
    	
    	Parser parser = new Parser(list);
    	parser.parse();
    	System.out.println("\nApproved!");
    	
	}

}
