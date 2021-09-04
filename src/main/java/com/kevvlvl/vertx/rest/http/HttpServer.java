package com.kevvlvl.vertx.rest.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.kevvlvl.vertx.rest.model.config.Config;
import com.kevvlvl.vertx.rest.route.RouteFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HttpServer extends AbstractVerticle {

    private static final String CONFIG_FILE_NAME = "application.yaml";
    private static final String ENDPOINT_PREFIX = "/api/v1";

    @Override
    public void start(Promise<Void> startPromise) {

        System.out.println("start");

        final RouteFactory routeFactory = new RouteFactory();
        final Router mainRouter = Router.router(vertx);

        routeFactory.getRoutes().forEach(r -> mainRouter.mountSubRouter(ENDPOINT_PREFIX, r.router(vertx)));

        Config httpConfig = getConfig();

        vertx.createHttpServer()
                .requestHandler(mainRouter)
                .listen(httpConfig.getHttp().getPort(),
                        result -> {
                            if (result.succeeded()) {
                                startPromise.complete();
                            } else {
                                startPromise.fail(result.cause());
                            }
                        });
    }

    private Config getConfig() {

        System.out.println("getConfig - About to retrieve config file");

        InputStream configFileStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
        String yamlStr = "";
        Config httpConfig = null;

        try {
            yamlStr = new String(configFileStream.readAllBytes(), StandardCharsets.UTF_8);

            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            httpConfig = yamlReader.readValue(yamlStr, Config.class);
        }
        catch(IOException e) {
            System.out.println("   ERROR trying to read config file - " + e);
        }

        return httpConfig;
    }
}
