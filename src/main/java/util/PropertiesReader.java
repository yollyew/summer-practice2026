package util;

import app.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final String filename;

    public Properties loadProperties() {
        Properties properties = new Properties();

        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(filename);
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return properties;
    }

    public PropertiesReader(String filename) {
        this.filename = filename;
    }
}
