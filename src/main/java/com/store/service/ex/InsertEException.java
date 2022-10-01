package com.store.service.ex;

//数据在插入过程中所产生的的异常
public class InsertEException extends ServiceException {
    public InsertEException() {
        super();
    }

    public InsertEException(String message) {
        super(message);
    }

    public InsertEException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertEException(Throwable cause) {
        super(cause);
    }

    protected InsertEException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
