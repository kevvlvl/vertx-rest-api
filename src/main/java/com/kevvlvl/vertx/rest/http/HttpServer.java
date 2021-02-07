package com.kevvlvl.vertx.rest.http;

import com.kevvlvl.vertx.rest.ServerConstant;
import com.kevvlvl.vertx.rest.route.FinanceRoute;
import com.kevvlvl.vertx.rest.route.HealthRoute;
import com.kevvlvl.vertx.rest.route.IRoute;
import com.kevvlvl.vertx.rest.route.RouteFactory;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class HttpServer extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        final RouteFactory routeFactory = new RouteFactory();
        final Router mainRouter = Router.router(vertx);

        routeFactory.getRoutes().forEach(r -> mainRouter.mountSubRouter(ServerConstant.ENDPOINT_PREFIX, r.router(vertx)));

        getConfig().getConfig(c -> {
            if(c.succeeded()) {
                vertx.createHttpServer()
                        .requestHandler(mainRouter)
                        .listen(c.result().getJsonObject(ServerConstant.CONFIG_HTTP_ROOT).getInteger(ServerConstant.CONFIG_HTTP_KEY),
                                result -> {
                                    if (result.succeeded()) {
                                        startPromise.complete();
                                    } else {
                                        startPromise.fail(result.cause());
                                    }
                                });
            }
            else {
                System.out.println("FAILED TO INIT ConfigRetriever");
            }
        });
    }

    private ConfigRetriever getConfig() {

        ConfigStoreOptions configOptions = new ConfigStoreOptions()
                .setType("file")
                .setFormat("yaml")
                .setConfig(new JsonObject().put(ServerConstant.CONFIG_FILE_PATH, ServerConstant.CONFIG_FILE_NAME));

        ConfigRetriever retriever = ConfigRetriever.create(vertx,
                new ConfigRetrieverOptions().addStore(configOptions));

        return retriever;
    }
}
