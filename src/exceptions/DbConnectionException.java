package exceptions;

public class DbConnectionException extends Exception{
    public DbConnectionException(String errorString) {
        super(errorString);
    }
}
