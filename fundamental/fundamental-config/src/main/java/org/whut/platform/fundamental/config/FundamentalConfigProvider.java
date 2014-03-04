package org.whut.platform.fundamental.config;

import org.whut.platform.fundamental.logger.PlatformLogger;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;

public class FundamentalConfigProvider {

    public static final String FILE_PREFIX = "file:";
    public static final String CLASSPATH_PREFIX = "classpath:";
    public static final String DEFAULT_CONFIG_PATH = "classpath:/fundamental.config.properties";
    private static final PlatformLogger LOGGER = PlatformLogger
            .getLogger(FundamentalConfigProvider.class);

    private static Properties prop = null;

    public static Properties getProp() {
        if (prop == null) {
            init();
        }
        return prop;
    }

    public static void setProperties(Properties prop) {
        FundamentalConfigProvider.prop = prop;
        LOGGER.info("FundamentalConfigProvider.prop ={},size:{}", prop,
                prop.size());
    }

    /**
     * 返回所有的properties
     *
     * @return
     */
    public Properties getProperties() {
        return getProp();
    }

    /**
     * 返回String类型的配置结果
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return getProp().getProperty(key);
    }

    /**
     * 返回Integer型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Integer getInt(String key) {
        String value = get(key);
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            LOGGER.error("get int error, return null.", ex);
            return null;
        }
    }

    /**
     * 返回Boolean型的配置结果。如果没有找到配置，返回false
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        String value = get(key);
        return Boolean.valueOf(value);
    }

    /**
     * 返回Double型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Double getDouble(String key) {
        String value = get(key);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            LOGGER.error("get int error, return null.", e);
            return null;
        }
    }

    /**
     * 返回Long型的配置结果。如果没有找到配置，返回null
     *
     * @param key
     * @return
     */
    public static Long getLong(String key) {
        String value = get(key);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            LOGGER.error("get int error, return null.", e);
            return null;
        }
    }

    public static synchronized void init() {
        //已经初始化，则直接返回
        if (FundamentalConfigProvider.prop != null
                && FundamentalConfigProvider.prop.size() > 0) {
            return;
        }

        LOGGER.info("init FundamentalConfigProvider.prop.");
        Properties properties = ConfigProperties.getProperties();
        if (properties != null && properties.size() > 0) {
            FundamentalConfigProvider.prop = properties;
            LOGGER.info("find properties from ConfigProperties");
        } else {
            String configPath = System.getProperty(Constants.CONFIG_PATH);

            if (configPath == null) {
                LOGGER.info("can't load config from:System.getProperty(Constants.CONFIG_PATH)");
                configPath = System.getenv(Constants.CONFIG_PATH);
                LOGGER.info("can't load config from:System.getenv(Constants.CONFIG_PATH)");
                if (configPath == null) {
                    LOGGER.error("config.path is null，now we use default config.if the environment is not dev,please check you startup param: -Dconfig.path=xxx");
                    configPath = DEFAULT_CONFIG_PATH;
                }
            }
            LOGGER.info("config.path:{}", configPath);

            Properties configs = new Properties();
            FileInputStream fileInputStream = null;
            InputStream inputStream = null;
            try {
                if (configPath.startsWith(FILE_PREFIX)) {
                    configPath = configPath.substring(FILE_PREFIX.length());
                    fileInputStream = new FileInputStream(new File(configPath));
                    configs.load(fileInputStream);
                } else if (configPath.startsWith(CLASSPATH_PREFIX)) {
                    configPath = configPath
                            .substring(CLASSPATH_PREFIX.length());
                    inputStream = FundamentalConfigProvider.class
                            .getResourceAsStream(configPath);
                    configs.load(inputStream);
                }
                FundamentalConfigProvider.prop = configs;
            } catch (FileNotFoundException e) {
                LOGGER.error("", e);
            } catch (IOException e) {
                LOGGER.error("", e);
            } finally {
                IOUtils.closeQuietly(fileInputStream);
                IOUtils.closeQuietly(inputStream);
            }
        }

        LOGGER.info("FundamentalConfigProvider.prop ={},size:{}",
                FundamentalConfigProvider.prop,
                FundamentalConfigProvider.prop.size());
    }

}
