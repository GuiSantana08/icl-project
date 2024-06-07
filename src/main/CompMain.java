package main;

import ast.ASTNode;
import compiler.CodeGen;
import exceptions.ASTNodeException;
import interpreter.Interpreter;
import parser.ParseException;
import parser.Parser;
import symbols.Env;
import type.Type;
import type.TypeChecker;
import value.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CompMain {
    private static final String FILENAME = "teste.j";

    public static void main(String[] args) {
        try {
            InputStream in;
            boolean isFileMode;
            if (args.length == 0 || args[0].trim().equals("")) {
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
            ASTNode e = parser.Start();
            System.out.print("Parse OK, type checking...");
            e.accept(typeChecker, environmentType);
            if (!isFileMode) {
                System.out.printf("Type OK... Generating code in file %s ...\n", FILENAME);
                CodeGen.writeToFile(e, FILENAME);
            } else {
                System.out.printf("Type OK... Generating code in file %s ...\n", args[0]);
                CodeGen.writeToFile(e, args[0]);
            }

        } catch (ASTNodeException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Syntax error encountered!");
            e.printStackTrace();
        }
    }
}
//
//        try{
//            String fileName = "teste.j";
//            System.out.println("Compiling " + fileName);
//            Parser parser = new Parser(System.in);
//            TypeChecker typeChecker = new TypeChecker();
//            Env<Type> environmentType = new Env<>();
//            ASTNode e = parser.Start();
//            System.out.println("Parse OK!");
//            e.accept(typeChecker, environmentType);
//            CodeGen.writeToFile(e, fileName);
//
//        } catch (Exception e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        }
