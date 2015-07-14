package platform.fundamental.datasource;

import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class DbcpFactoryBean implements FactoryBean<BasicDataSource>,
		DisposableBean, InitializingBean {

	private  static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(DbcpFactoryBean.class);

	private BasicDataSource ds;
	private Properties properties;
	private String dbname;

	public void afterPropertiesSet() throws Exception {
		setProperties(FundamentalConfigProvider.getProp());
	}

	public BasicDataSource getObject() {
		ds = new BasicDataSource();

		this.tryToSetProperties();

		return ds;
	}

	public void destroy() {
		if (ds == null) {
			return;
		}
		try {
			ds.close();
			ds = null;
		} catch (Exception ex) {
			LOGGER.warn("close dbcp error", ex);
		}
	}

	public Class<?> getObjectType() {
		return BasicDataSource.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setDbname(String dbname) {
        this.dbname = dbname;
    }

	protected void tryToSetProperties() {
		String configPrefix = "dbcp.";
		if (dbname != null && dbname.trim().length() > 0) {
			configPrefix = configPrefix + dbname + ".";
		}

		if (properties == null) {
			throw new IllegalArgumentException(
					"there is no dbcp properties setting.");
		}
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			if (!key.startsWith(configPrefix)) {
				continue;
			}

			String propertyName = key.substring(configPrefix.length());
			try {
				tryToSetProperty(propertyName, value);
			} catch (Exception ex) {
				LOGGER.info("error to set property : key : " + key
						+ ", value : " + value, ex);
			}
		}
	}

	protected void tryToSetProperty(String propertyName, String propertyValue)
			throws Exception {
		String setterName = "set" + propertyName.substring(0, 1).toUpperCase(Locale.CHINA)
				+ propertyName.substring(1);
		Method[] methods = BasicDataSource.class.getMethods();
		for (Method method : methods) {
			if (!method.getName().equals(setterName)) {
				continue;
			}
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length == 1) {
				LOGGER.debug("match method : {}, {}", new Object[] { method,
						propertyValue });
				Class<?> parameterType = parameterTypes[0];
				this.invokeSetValue(method, parameterType, propertyValue);
			}
		}
	}

	private void invokeSetValue(Method method, Class<?> parameterType,
			String propertyValue) throws Exception {
        LOGGER.info("name :"+method.getName()+" value :"+propertyValue);
		if (parameterType == String.class) {
			method.invoke(ds, propertyValue);
		} else if (parameterType == Integer.class || parameterType == int.class) {
			method.invoke(ds, Integer.parseInt(propertyValue));
		} else if (parameterType == Long.class || parameterType == long.class) {
			method.invoke(ds, Long.parseLong(propertyValue));
		} else if (parameterType == Boolean.class
				|| parameterType == boolean.class) {
			method.invoke(ds, Boolean.valueOf(propertyValue));
		} else {
			LOGGER.info("cannot process parameterType : [" + parameterType
					+ "]");
		}
	}

}
