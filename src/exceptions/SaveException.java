package exceptions;

public class SaveException extends Exception{
    public SaveException(String errorString) {
        super(errorString);
    }
}
