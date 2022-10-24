package com.northsea.utils;

import java.io.Serial;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */

public class UnloginException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnloginException() {
    }

    public UnloginException(String message) {
        super(message);
    }

    public UnloginException(Throwable cause) {
        super(cause);
    }

    public UnloginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnloginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
