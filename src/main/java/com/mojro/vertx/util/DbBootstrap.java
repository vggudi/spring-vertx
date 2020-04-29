package com.mojro.vertx.util;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mojro.vertx.entity.Book;
import com.mojro.vertx.repository.BookRepository;

@Component
public class DbBootstrap implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... arg0) throws Exception {

        /*IntStream.range(0, 10)
          .forEach(count -> this.bookRepository.save(new Book("ADEF456","JS","Rahul",9999.00)));*/
    	bookRepository.save(new Book("ADEF456","JS","Rahul",9999.00));

    }
}
