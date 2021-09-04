package com.kevvlvl.vertx.rest.model.config;

public class Http {

    private int port;

    public Http() {

    }

    public Http(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
