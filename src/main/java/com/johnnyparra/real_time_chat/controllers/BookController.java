package com.johnnyparra.real_time_chat.controllers;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import com.johnnyparra.real_time_chat.entities.Book;
import com.johnnyparra.real_time_chat.repositories.BookRepository;

@Controller
public class BookController {

  private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @QueryMapping
  public Book bookById(@Argument Long id) {
    System.out.println("Fetching book with ID: " + id);
    return bookRepository.findById(id).orElse(null);
  }
}
