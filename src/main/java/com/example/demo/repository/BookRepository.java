package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByYear(Integer year);

    List<Book> findByAuthorsNameContainingIgnoreCase(String name);

    List<Book> findByAuthorsId(Long authorId);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorsNameContainingIgnoreCase(
        String title,
        String authorName
);
    
}