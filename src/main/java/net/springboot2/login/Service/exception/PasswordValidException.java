package net.springboot2.login.Service.exception;

public class PasswordValidException extends ServiceException{
    public PasswordValidException() {
        super();
    }

    public PasswordValidException(String message) {
        super(message);
    }

    public PasswordValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordValidException(Throwable cause) {
        super(cause);
    }

    protected PasswordValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
