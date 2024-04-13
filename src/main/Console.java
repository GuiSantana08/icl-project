package main;

import java.io.ByteArrayInputStream;
import java.text.ParseException;

import ast.ASTNode;

import interpreter.*;

public class Console {

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		Parser parser = new Parser(System.in);

		while (true) {
			try {
				ASTNode e = parser.Start();
				System.out.println("Parse OK!" );
				//System.out.println(Interpreter.interpret(e));
			} catch (TokenMgrError e) {
				System.out.println("Lexical Error!");
				e.printStackTrace();
				parser.ReInit(System.in);
			} catch (ParseException e) {
				System.out.println("Syntax Error!");
				e.printStackTrace();
				parser.ReInit(System.in);
			} catch (TypingException e) {
                throw new RuntimeException(e);
            }
        }
	}

	public static boolean accept(String s) throws ParseException {
		Parser parser = new Parser(new ByteArrayInputStream(s.getBytes()));
		try {
			parser.Start();
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
