package com.kevvlvl.vertx.rest.route;

import java.util.List;

public class RouteFactory {

    public List<IRoute> getRoutes() {
        return List.of(
                new FinanceRoute(),
                new HealthRoute());
    }
}
