package com.mojro.vertx.verticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Component
public class ServerVerticle extends AbstractVerticle {

    //@Autowired
    //private Integer defaultPort;

    private void getAllBooksHandler(RoutingContext routingContext) {
        vertx.eventBus()
            .<String>send(BookRecipientVerticle.GET_ALL_BOOKS, "", result -> {
                if (result.succeeded()) {
                    routingContext.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(200)
                        .end(result.result()
                        .body());
                } else {
                    routingContext.response()
                        .setStatusCode(500)
                        .end();
                }
            });
    }

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        router.get("/api/mojro/books")
            .handler(this::getAllBooksHandler);

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(config().getInteger("http.port", 8080));
    }

}