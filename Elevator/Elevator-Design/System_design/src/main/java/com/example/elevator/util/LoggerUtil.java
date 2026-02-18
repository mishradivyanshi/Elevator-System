package com.example.elevator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggerUtil {
    public static Logger getLogger(Class<?> cls) {
        return LoggerFactory.getLogger(cls);
    }
    private LoggerUtil() {}
}
