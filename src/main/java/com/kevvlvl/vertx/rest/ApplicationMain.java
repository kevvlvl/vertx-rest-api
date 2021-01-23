package com.kevvlvl.vertx.rest;

import com.kevvlvl.vertx.rest.server.HttpServerVerticle;
import io.vertx.core.Vertx;

public class ApplicationMain {

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerVerticle());
    }
}