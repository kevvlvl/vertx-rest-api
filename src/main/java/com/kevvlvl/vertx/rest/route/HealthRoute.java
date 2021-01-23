package com.kevvlvl.vertx.rest.route;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HealthRoute implements ICustomRoute {

    private final Router router;

    public HealthRoute(Vertx vertx) throws Exception {
        this.router = Router.router(vertx);
    }

    public Router defineRoute() throws Exception {

        router.get("/api/health").handler(context -> {

            HttpServerResponse response = context.response();
            response.putHeader("content-type", "application/json");
            response.end("HTTP Server is up");
        });

        return router;
    }
}
