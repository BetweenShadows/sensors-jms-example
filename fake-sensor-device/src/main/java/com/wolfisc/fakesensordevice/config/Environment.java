package com.wolfisc.fakesensordevice.config;

public class Environment {

    public static final String DEVICE_ID = "DEVICE_ID";
    public static final String BROKEN_URL = "BROKER_URL";
    public static final String JMS_USER = "JMS_USER";
    public static final String JMS_PASS = "JMS_PASS";
    public static final String QUEUE_NAME = "QUEUE_NAME";
    public static final String SEND_INFO_EVERY_MS = "SEND_INFO_EVERY_MS";

    public static <T> T get(String envName, Class<T> clazz) {
        String result = null;
        try {
            result = System.getenv(envName);
            if(result == null) {
                throw new EnvVarNotFoundException("Environment variable " + envName + " has null value.");
            }
        } catch (EnvVarNotFoundException ex) {
            ex.printStackTrace();
        }

        if(clazz == Integer.class) {
            Integer intValue = Integer.parseInt(result);
            return clazz.cast(intValue);
        }

        if(clazz == Boolean.class) {
            Boolean booleanValue = Boolean.parseBoolean(result);
            return clazz.cast(booleanValue);
        }

        return clazz.cast(result);
    }
}