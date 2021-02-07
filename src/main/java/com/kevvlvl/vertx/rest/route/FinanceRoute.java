package com.kevvlvl.vertx.rest.route;

import com.kevvlvl.vertx.rest.ServerConstant;
import com.kevvlvl.vertx.rest.model.Stock;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.math.BigDecimal;

public class FinanceRoute implements IRoute {

    @Override
    public Router router(Vertx vertx) {
        final Router appRouter = Router.router(vertx);
        appRouter.get("/fin?:symbolName").produces(ServerConstant.APPLICATION_JSON).handler(this::getStockData);

        return appRouter;
    }

    private void getStockData(RoutingContext ctx) {

        String symbolName = ctx.request().getParam("symbolName");
        Stock stock = new Stock(symbolName, BigDecimal.valueOf(10));

        ctx.response()
                .putHeader("content-type", ServerConstant.APPLICATION_JSON)
                .setStatusCode(200)
                .end(Json.encodePrettily(stock));
    }
}
