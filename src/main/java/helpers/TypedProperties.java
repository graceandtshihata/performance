package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class TypedProperties {

    /** The properties. */
    private final Properties properties = new Properties();

    /**
     * Instantiates a new typed properties.
     *
     * @param resourceName the resource name
     */
    public TypedProperties(String resourceName) {
        final InputStream inputStream = getClass().getResourceAsStream(resourceName);
        try {
            properties.load(inputStream);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the value.
     *
     * @param key the key
     * @return the value
     */
    public String getValue(final String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets the boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean getBoolean(final String key) {
        return parseBoolean(getValue(key));
    }

    /**
     * Gets the int.
     *
     * @param key the key
     * @return the int
     */
    public int getInt(final String key) {
        return parseInt(getValue(key));
    }
}
