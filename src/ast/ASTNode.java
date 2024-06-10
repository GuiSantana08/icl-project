package ast;

import ast.control.ASTIfThenElse;
import ast.control.ASTSeq;
import ast.control.ASTWhile;
import ast.functions.ASTDefFun;
import ast.functions.ASTFunCall;
import ast.functions.ASTPrint;
import ast.functions.ASTPrintln;
import ast.functions.io.out.ASTExit;
import ast.operations.arithmetic.*;
import ast.operations.references.*;
import ast.operations.relational.*;
import ast.value.ASTBool;
import ast.value.ASTInt;
import ast.value.ASTString;
import type.Type;

public abstract class ASTNode {
    private Type type;

    public ASTNode(Type type) {
        this.type = type;
    }

    public ASTNode() {
        this.type = null;
    }
    public interface Visitor<T,E> {
        T visit(ASTInt e, E env);
        T visit(ASTBool e, E env);
        T visit(ASTString e, E env);
        T visit(ASTNeg e, E env);
        T visit(ASTDiv e, E env);
        T visit(ASTMult e, E env);
        T visit(ASTSub e, E env);
        T visit(ASTAdd e, E env);
        T visit(ASTDiff e, E env);
        T visit(ASTLeq e, E env);
        T visit(ASTLt e, E env);
        T visit(ASTGeq e, E env);
        T visit(ASTGt e, E env);
        T visit(ASTEq e, E env);
        T visit(ASTAnd e, E env);
        T visit(ASTOr e, E env);
        T visit(ASTNot e, E env);
        T visit(ASTIfThenElse e, E env);
        T visit(ASTNew e, E env);
        T visit(ASTLet e, E env);
        T visit(ASTId e, E env);
        T visit(ASTReff e, E env);
        T visit(ASTWhile e, E env);
        T visit(ASTSeq e, E env);
        T visit(ASTDRef e, E env);
        T visit(ASTPrint e, E env);
        T visit(ASTPrintln e, E env);
        T visit(ASTDefFun e, E env);
        T visit(ASTFunCall e, E env);
        T visit(ASTExit e, E env);
    }
    public abstract  <T,E> T accept(Visitor<T, E> v, E env);

    public String getJVMType() {
        return type.jvmType();
    }

    public Type getASTType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
