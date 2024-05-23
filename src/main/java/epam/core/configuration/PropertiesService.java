package epam.core.configuration;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesService {

    private static final String AUTOMATION_PROPERTIES = "src/main/resources/automation.properties";

    private PropertiesService() {
    }

    private static final Logger LOGGER = Logger.getLogger(PropertiesService.class);

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (FileInputStream stream = new FileInputStream(resolvePath(AUTOMATION_PROPERTIES))){
            properties.load(stream);
        } catch (IOException error) {
            LOGGER.warn("Find some error ", error);
        }
        return properties;
    }

    private static String resolvePath(final String propertyWithPath) {
        File file = new File(propertyWithPath);
        return file.getAbsoluteFile().getAbsolutePath();
    }

    public static String getProperty(String property) {
        return getProperty(property, StringUtils.EMPTY);
    }

    @SneakyThrows
    public static String getProperty(String property, String defaultValue) {
        return loadProperties().getProperty(property, defaultValue).trim();
    }
}
