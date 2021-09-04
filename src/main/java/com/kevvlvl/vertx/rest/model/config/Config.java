package com.kevvlvl.vertx.rest.model.config;

public class Config {

    private Http http;

    public Config() {
    }

    public Config(Http http) {
        this.http = http;
    }

    public Http getHttp() {
        return http;
    }

    public void setHttp(Http http) {
        this.http = http;
    }
}
