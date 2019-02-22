package com.home.app.repository.newspaper;

public class NoSuchNewspaperException extends RuntimeException {
    public NoSuchNewspaperException() {
        super();
    }

    public NoSuchNewspaperException(String message) {
        super(message);
    }

    public NoSuchNewspaperException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNewspaperException(Throwable cause) {
        super(cause);
    }

    protected NoSuchNewspaperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
