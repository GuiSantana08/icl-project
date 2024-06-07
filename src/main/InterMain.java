package main;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ast.ASTNode;

import exceptions.ASTNodeException;
import exceptions.DuplicateVariableFoundException;
import exceptions.InvalidTypeException;
import interpreter.*;
import parser.ParseException;
import parser.Parser;
import parser.TokenMgrError;
import symbols.Env;
import type.Type;
import type.TypeChecker;
import value.Value;

public class InterMain {


	@SuppressWarnings("static-access")
	public static void main(String args[]) throws FileNotFoundException {
		try{
			InputStream in;
			boolean isFileMode;
			if (args.length == 0 || args[0].trim().equals("")){
				in = System.in;
				isFileMode = false;
			} else {
				String filename = args[0];
				in = new FileInputStream(filename);
				isFileMode = true;
			}

			Parser parser = new Parser(in);
			TypeChecker typeChecker = new TypeChecker();
			Env<Type> environmentType = new Env<>();
			Env<Value<?>> environmentValue = new Env<>();

			do {
				if (!isFileMode)
					System.out.print("> ");
				ASTNode e = parser.Start();
				System.out.print("Parse OK, type checking...");
				e.accept(typeChecker, environmentType);
				System.out.println("Type OK");
				Interpreter.interpret(e);
			} while (!isFileMode);

		} catch (TokenMgrError e) {
			System.out.println("\nLexical error encountered!");
			e.printStackTrace();
		} catch (ASTNodeException e) {
			System.out.println("\nAST error encountered!");
			e.printStackTrace();
		} catch (ParseException e)  {
			System.out.println("Syntax error encountered!");
			e.printStackTrace();
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
