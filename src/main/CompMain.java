package main;

import ast.ASTNode;
import compiler.CodeGen;
import parser.Parser;
import symbols.Env;
import type.Type;
import type.TypeChecker;

public class CompMain {

    public static void main(String[] args) {
        try{
            String fileName = "teste.j";
            System.out.println("Compiling " + fileName);
            Parser parser = new Parser(System.in);
            TypeChecker typeChecker = new TypeChecker();
            Env<Type> environmentType = new Env<>();
            ASTNode e = parser.Start();
            System.out.println("Parse OK!");
            e.accept(typeChecker, environmentType);
            CodeGen.writeToFile(e, fileName);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
