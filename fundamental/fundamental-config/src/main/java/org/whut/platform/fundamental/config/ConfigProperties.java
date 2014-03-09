package org.whut.platform.fundamental.config;

import java.util.Properties;

//test


public class ConfigProperties {
    private static Properties properties;

    public static Properties getProperties() {
        return ConfigProperties.properties;
    }

    public void setProperties(Properties properties) {
        setPropertiesStatic(properties);
    }

    protected static void setPropertiesStatic(Properties properties) {
        ConfigProperties.properties = properties;
    }

}
