package com.kevvlvl.vertx.rest.route;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public interface IRoute {

    Router router(Vertx vertx);
}
