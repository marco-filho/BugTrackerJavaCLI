package Auth.AuthExceptions;

public class IllegalUsernameException extends Exception {
    public IllegalUsernameException() {
        super("O nome de usuario não pode conter espaços e deve conter apenas letras, números e os símbolos _, . e -");
    }

    public IllegalUsernameException(String errorMessage) {
        super(errorMessage);
    }
}
