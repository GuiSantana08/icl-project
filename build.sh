cd src/parser/ || exit

javacc -c Parser.jj

cd ../../ || exit

javac -d build src/ast/*.java src/ast/control/*.java src/ast/functions/*.java src/ast/operations/arithmetic/*.java src/ast/operations/references/*.java src/ast/operations/relational/*.java src/ast/value/*.java src/exceptions/*.java src/interpreter/Interpreter.java src/main/InterMain.java src/parser/*.java src/symbols/*.java src/type/*.java src/value/*.java

jar cfm project.jar build/Manifest.txt build/ast/*.class build/ast/control/*.class build/ast/functions/*.class build/ast/operations/arithmetic/*.class build/ast/operations/references/*.class build/ast/operations/relational/*.class build/ast/value/*.class build/exceptions/*.class build/interpreter/Interpreter.class build/main/InterMain.class build/parser/*.class build/symbols/*.class build/type/*.class build/value/*.class