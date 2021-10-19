package exceptions;

public class DeleteException extends Exception{
    public DeleteException(String errorString) {
        super(errorString);
    }
}
