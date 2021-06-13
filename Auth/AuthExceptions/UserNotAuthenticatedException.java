package Auth.AuthExceptions;

public class UserNotAuthenticatedException extends Exception {
    public enum ErrorType {
        NOT_AUTHENTICATED,
        NULL_RETURN,
        NOT_ESPECIFIED
    }

    ErrorType errorType; 
    
    public UserNotAuthenticatedException(int error) {
        super("Erro de autenticação.");
        switch (error) {
            case 0:
                errorType = ErrorType.NOT_AUTHENTICATED;
                break;
            case 1:
                errorType = ErrorType.NULL_RETURN;
                break;
            default:
                errorType = ErrorType.NOT_ESPECIFIED;
                break;
        }
    }

    public UserNotAuthenticatedException() {
        this(-1);
    }

    public UserNotAuthenticatedException(String errorMessage) {
        super(errorMessage);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
