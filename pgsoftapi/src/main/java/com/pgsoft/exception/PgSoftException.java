package com.pgsoft.exception;

import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.logging.Level;

public abstract class PgSoftException extends RuntimeException {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PgSoftException.class);

    @Nullable
    protected Level getDefaultExceptionLoggingLevel() {
        return null;
    }

    public PgSoftException(String s) {
        this(null, s);
    }

    public PgSoftException(Level loggingLevels, String s) {
        super(s);
        Level loggingLevel = loggingLevels;
        if (null == loggingLevel) {
            loggingLevel = getDefaultExceptionLoggingLevel();
        }
        if (Level.WARNING.equals(loggingLevel)) {
            log.warn(s);
        } else {
            log.error(s);
        }
    }

    public PgSoftException(Level loggingLevels, String s, Throwable throwable) {
        super(s, throwable);
        if (Level.WARNING.equals(loggingLevels)) {
            log.warn(s);
        } else {
            log.error(s);
        }
    }

    public PgSoftException(String s, Throwable throwable) {
        this(null, s, throwable);
    }

    public PgSoftException(Throwable throwable) {
        this(throwable.getLocalizedMessage(), throwable);
    }
}
