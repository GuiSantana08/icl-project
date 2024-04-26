cd src/parser/ || exit

javacc -c Parser.jj

javac -d /build src/ast/ASTNode.java src/ast/control/*.java src/ast/operations/*.java src/ast/value/*.java src/exceptions/*.java src/interpreter/Interpreter.java src/main/InterpMain.java src/parser/*.java src/symbols/*.java src/type/*.java src/value/*.java