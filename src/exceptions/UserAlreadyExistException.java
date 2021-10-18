package exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String errorString) {
        super(errorString);
    }
}
