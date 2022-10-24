package com.northsea.utils;

import java.io.Serial;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */

public class CheckException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
