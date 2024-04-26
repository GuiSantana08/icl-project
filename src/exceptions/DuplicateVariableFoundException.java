package exceptions;

public class DuplicateVariableFoundException extends Exception{

            private static final long serialVersionUID = 1L;

            public DuplicateVariableFoundException() {
            }

            public DuplicateVariableFoundException(String message) {
                super(message);
            }

}
