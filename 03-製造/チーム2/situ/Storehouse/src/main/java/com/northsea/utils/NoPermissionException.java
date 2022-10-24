package com.northsea.utils;

import java.io.Serial;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */

public class NoPermissionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
