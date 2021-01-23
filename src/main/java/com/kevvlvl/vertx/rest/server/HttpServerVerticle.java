package com.kevvlvl.vertx.rest.server;

import com.kevvlvl.vertx.rest.route.HealthRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HttpServerVerticle extends AbstractVerticle {

    private static final String HTTP_PORT = "http.port";
    private static final int HTTP_PORT_VALUE = 8081;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        vertx.createHttpServer()
                .requestHandler(new HealthRoute(vertx).defineRoute())
                .listen(config().getInteger(HTTP_PORT, HTTP_PORT_VALUE),
                        result -> {
                            if (result.succeeded()) {
                                startPromise.complete();
                            } else {
                                startPromise.fail(result.cause());
                            }
                });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
