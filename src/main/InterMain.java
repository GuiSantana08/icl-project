package main;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import ast.ASTNode;

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
		Parser parser = new Parser(new FileReader(args[0]));
		TypeChecker typeChecker = new TypeChecker();
		Env<Type> environmentType = new Env<>();
		Env<Value<?>> environmentValue = new Env<>();

		while (true) {
			try {
				ASTNode e = parser.Start();
				System.out.println("Parse OK!" );
				e.accept(typeChecker, environmentType);
				System.out.println(Interpreter.interpret(e, environmentValue));

			} catch (TokenMgrError e) {
				System.out.println("Lexical Error!");
				e.printStackTrace();
				parser.ReInit(System.in);
			} catch (InvalidTypeException | DuplicateVariableFoundException e) {
                throw new RuntimeException(e);
            }
			catch (parser.ParseException e) {
				System.out.println("Syntax Error!");
				e.printStackTrace();
				parser.ReInit(System.in);
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
