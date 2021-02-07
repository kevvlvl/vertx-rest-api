package com.kevvlvl.vertx.rest.route;

import com.kevvlvl.vertx.rest.ServerConstant;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HealthRoute {

    public Router router(Vertx vertx) {
        final Router appRouter = Router.router(vertx);
        appRouter.get("/health").produces(ServerConstant.APPLICATION_JSON).handler(this::getHealth);

        return appRouter;
    }

    private void getHealth(RoutingContext ctx) {

        ctx.response()
                .putHeader("content-type", ServerConstant.APPLICATION_JSON)
                .setStatusCode(200)
                .end("Service is UP!");
    }
}
