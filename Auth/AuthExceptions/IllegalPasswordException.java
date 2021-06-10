package Auth.AuthExceptions;

public class IllegalPasswordException extends Exception {
    public IllegalPasswordException() {
        super("A senha não pode conter espaços e deve conter apenas letras, números e os símbolos @, #, =, +, - e ?");
    }
    
    public IllegalPasswordException(String errorMessage) {
        super(errorMessage);
    }
}