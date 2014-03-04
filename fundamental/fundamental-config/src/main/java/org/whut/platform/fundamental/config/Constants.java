package org.whut.platform.fundamental.config;

public final class Constants {

    private Constants() {
        throw new Error("Utility classes should not instantiated!");
    }

    public static final String CONFIG_PATH = "config.path";
    public static final String CONFIG_KEY_JOIN_SYMBOL = ".";

    /**
     * JMS
     */
    public static final String JMS_BROKER_URL = "jms.broker.url";

    /**
     * NOSQL
     */
    public static final String NOSQL_CONFIG_PREFIX = "peixun.nosql.";
    public static final String NOSQL_KEY_DBARRAY = "dbarray";
    public static final String NOSQL_KEY_MAXACTIVE = "maxActive";
    public static final String NOSQL_KEY_MAXIDLE = "maxIdle";
    public static final String NOSQL_KEY_MAXWAIT = "maxWait";
    public static final String NOSQL_KEY_TEST0NBORROW = "testOnBorrow";

    public static final String NOSQL_KEY_HOSTNAME = "hostName";
    public static final String NOSQL_KEY_PASSWORD = "password";
    public static final String NOSQL_KEY_PORT = "port";
    public static final String NOSQL_KEY_USEPOOL = "usePool";
    public static final String NOSQL_KEY_TIMEOUT = "timeout";

    /**
     * auth mock
     */
    public static final String AUTH_IN_MOCK = "auth.in.mock";
    public static final String AUTH_MOCK_UUID = "auth.mock.uuid";
    public static final String AUTH_MOCK_UICID = "auth.mock.uicid";
    public static final String AUTH_MOCK_USERNAME = "auth.mock.username";
    public static final String AUTH_MOCK_USERID = "auth.mock.userid";
    public static final String AUTH_MOCK_APPID = "auth.mock.appid";

    /**
     * mail
     */
    public static final String SMTP_HOST = "smtp.host";
    public static final String SMTP_USERNAME = "smtp.username";
    public static final String SMTP_PASSWORD = "smtp.password";
    public static final String SMTP_AUTH = "smtp.auth";

    public static final String EMAIL_ENCODE = "email.encode";
    public static final String EMAIL_DEFAULT_SENDER = "email.systemMailSender";
    public static final String EMAIL_MAIL_IN_TEST = "email.mailInTest";
    public static final String EMAIL_SEND_TEST_MAIL = "email.sendTestMail";
    public static final String EMAIL_TEST_MAIL_SENDTO = "email.testMailSendTo";
    public static final String EMAIL_BCC_MAIL = "email.bccMail";
    public static final String EMAIL_CC_MAIL = "email.ccMail";

    /**
     * fundamental.util
     */
    public static final String IMAGE_CODE_USE_SINGLE_VALIDATION = "imagecode.use.single.validation";
    public static final String FUNDAMENTAL_DEFAULT_UPLOAD_PATH = "default.upload.path";
    public static final String FUNDAMENTAL_WEBROOT_NAME = "webroot.name";

    /**
     * sem certification
     */
    public static final String SEM_CERTIFICATION_BASEPATH = "certification.basepath";
    public static final String SEM_CERTIFICATION_FOLDERPATH = "certification.folerpath";
}
