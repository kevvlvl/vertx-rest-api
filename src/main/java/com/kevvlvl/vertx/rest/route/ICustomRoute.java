package com.kevvlvl.vertx.rest.route;

import io.vertx.ext.web.Router;

public interface ICustomRoute {

    Router defineRoute() throws Exception;
}
