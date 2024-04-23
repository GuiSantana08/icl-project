package main;

import ast.ASTNode;
import compiler.CodeGen;

public class CompMain {

    public static void main(String[] args) {
        try{
            String fileName = "teste.j";
            System.out.println("Compiling " + fileName);
            Parser parser = new Parser(System.in);
            ASTNode e = parser.Start();
            System.out.println("Parse OK!");
            CodeGen.writeToFile(e, fileName);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
