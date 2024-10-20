package com.johnnyparra.real_time_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnnyparra.real_time_chat.entities.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Book findById(long id);
}
