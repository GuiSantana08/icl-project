cd src/parser/ || exit

javacc -c Parser.jj

cd ../../ || exit

javac src/ast/*.java src/ast/control/*.java src/ast/functions/*.java src/ast/operations/arithmetic/*.java src/ast/operations/references/*.java src/ast/operations/relational/*.java src/ast/value/*.java src/exceptions/*.java src/interpreter/Interpreter.java src/main/InterMain.java src/parser/*.java src/symbols/*.java src/type/*.java src/value/*.java

jar cfm project.jar Manifest.txt src/ast/*.class src/ast/control/*.class src/ast/functions/*.class src/ast/operations/arithmetic/*.class src/ast/operations/references/*.class src/ast/operations/relational/*.class src/ast/value/*.class src/exceptions/*.class src/interpreter/Interpreter.class src/main/InterMain.class src/parser/*.class src/symbols/*.class src/type/*.class src/value/*.class

rm src/ast/*.class src/ast/control/*.class src/ast/functions/*.class src/ast/operations/arithmetic/*.class src/ast/operations/references/*.class src/ast/operations/relational/*.class src/ast/value/*.class src/exceptions/*.class src/interpreter/Interpreter.class src/main/InterMain.class src/parser/*.class src/symbols/*.class src/type/*.class src/value/*.class