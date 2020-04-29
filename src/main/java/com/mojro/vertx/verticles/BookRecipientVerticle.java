package com.mojro.vertx.verticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mojro.vertx.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;

@Component
public class BookRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_BOOKS = "get.books.all";

    private final ObjectMapper mapper = Json.mapper;

    @Autowired
    private BookService bookService;

    @Override
    public void start() throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_ALL_BOOKS)
                .handler(getAllBookService(bookService));
    }

    private Handler<Message<String>> getAllBookService(BookService service) {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(service.getAllBook()));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }
}
