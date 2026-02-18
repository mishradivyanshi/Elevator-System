package com.example.elevator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private static final Properties config = new Properties();

    static {
        try (InputStream stream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (stream != null) {
                config.load(stream);
            }

        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                    "Unable to load config.properties → " + e.getMessage()
            );
        }
    }

    private ConfigLoader() {}

    public static int getInt(String key, int fallback) {
        String value = config.getProperty(key);
        if (value == null) return fallback;

        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }

    public static String getString(String key, String fallback) {
        String value = config.getProperty(key);
        return (value != null) ? value.trim() : fallback;
    }
}
