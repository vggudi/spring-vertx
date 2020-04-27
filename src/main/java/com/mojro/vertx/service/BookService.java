package com.mojro.vertx.service;

import com.mojro.vertx.entity.Book;
import com.mojro.vertx.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

}
