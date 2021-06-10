package Auth.AuthExceptions;

public class UsernameExistsException extends IllegalUsernameException {
    public UsernameExistsException() {
        super("O nome de usuario já existe");
    }

    public UsernameExistsException(String errorMessage) {
        super(errorMessage);
    }
}
