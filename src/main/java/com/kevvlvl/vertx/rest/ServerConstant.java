package com.kevvlvl.vertx.rest;

public class ServerConstant {

    private ServerConstant() {
        // prevent instantiation
    }

    public static final String CONFIG_FILE_PATH = "path";
    public static final String CONFIG_FILE_NAME = "application.yaml";
    public static final String CONFIG_HTTP_ROOT = "http";
    public static final String CONFIG_HTTP_KEY = "port";

    public static final String APPLICATION_JSON = "application/json";

    public static final String ENDPOINT_PREFIX = "/api/v1";
}
