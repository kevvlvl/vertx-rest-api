package com.kevvlvl.vertx.rest.route;

import com.kevvlvl.vertx.rest.model.Stock;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.math.BigDecimal;

public class FinanceRoute {

    private final Router router;
    private final String endpoint;

    public FinanceRoute(Vertx vertx, String endpoint) {
        this.router = Router.router(vertx);
        this.endpoint = endpoint;
    }

    public Router defineRoute() {

        router.get(this.endpoint + "?:symbolName")
                .handler(this::getStockData);

        return router;

    }

    private void getStockData(RoutingContext context) {

        String symbolName = context.request().getParam("symbolName");
        Stock stock = new Stock(symbolName, BigDecimal.valueOf(10));

        context.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(stock));
    }
}
