package Auth.AuthExceptions;

public class UnmatchedCredentialsException extends Exception {
    public UnmatchedCredentialsException() {
        super("Nome de usuário e senha não correspondem");
    }
    
    public UnmatchedCredentialsException(String errorMessage) {
        super(errorMessage);
    }
    
}
