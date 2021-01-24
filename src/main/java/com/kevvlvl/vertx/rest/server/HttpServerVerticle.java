package com.kevvlvl.vertx.rest.server;

import com.kevvlvl.vertx.rest.route.MainRoute;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class HttpServerVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

        ConfigStoreOptions configOptions = new ConfigStoreOptions()
                .setType("file")
                .setFormat("yaml")
                .setConfig(new JsonObject().put(ServerConstant.CONFIG_FILE_PATH, ServerConstant.CONFIG_FILE_NAME));

        ConfigRetriever retriever = ConfigRetriever.create(vertx,
                new ConfigRetrieverOptions().addStore(configOptions));

        retriever.getConfig(conf -> {
            if(conf.succeeded()) {

                try {
                    vertx.createHttpServer()
                            .requestHandler(new MainRoute(this.vertx).defineRoute())
                            .listen(conf.result().getJsonObject(ServerConstant.CONFIG_HTTP_ROOT).getInteger(ServerConstant.CONFIG_HTTP_KEY),
                                    result -> {
                                        if (result.succeeded()) {
                                            startPromise.complete();
                                        } else {
                                            startPromise.fail(result.cause());
                                        }
                                    });
                } catch (Exception e) {
                    System.out.println("EXCEPTION: " + e);
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("FAILED TO INIT ConfigRetriever");
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
