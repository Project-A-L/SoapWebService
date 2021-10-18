package exceptions;

public class FetchException extends Exception{
    public FetchException(String errorString) {
        super(errorString);
    }
}
