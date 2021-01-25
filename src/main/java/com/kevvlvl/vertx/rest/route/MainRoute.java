package com.kevvlvl.vertx.rest.route;

import com.kevvlvl.vertx.rest.model.Stock;
import com.kevvlvl.vertx.rest.server.ServerConstant;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.math.BigDecimal;

public class MainRoute {

    private final Router router;

    public MainRoute(Vertx vertx) {
        this.router = Router.router(vertx);
    }

    public Router defineRoute() {

        this.router.get("/api/fin?:symbolName").produces(ServerConstant.APPLICATION_JSON).handler(this::getStockData);
        this.router.get("/api/health").handler(this::getHealth);

        return router;
    }

    private void getStockData(RoutingContext ctx) {

        String symbolName = ctx.request().getParam("symbolName");
        Stock stock = new Stock(symbolName, BigDecimal.valueOf(10));

        ctx.response()
                .putHeader("content-type", ServerConstant.APPLICATION_JSON)
                .setStatusCode(200)
                .end(Json.encodePrettily(stock));
    }

    private void getHealth(RoutingContext context) {

        context.response()
                .putHeader("content-type", "text/plain")
                .setStatusCode(200)
                .end("Service is UP");
    }
}