package exceptions;

public class DuplicateVariableFoundException extends ASTNodeException{

    private static final String ERRORMESSAGE = "Duplicate variable found... This variable already exists: ";

            public DuplicateVariableFoundException(String message) {
                super(ERRORMESSAGE + message);
            }

}
