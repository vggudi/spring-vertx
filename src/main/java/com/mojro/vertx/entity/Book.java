package com.mojro.vertx.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.PersistenceConstructor;

@Entity
public class Book {
	private String ISBN;
	private String name;
	private String author;
	private double price;

	public Book() {

	}

	@PersistenceConstructor
	public Book(String ISBN, String name, String author, double price) {

		this.ISBN = ISBN;
		this.name = name;
		this.author = author;
		this.price = price;
	}

	@Id
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

