package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorId(Long authorId);

    @Query("""
    SELECT b FROM Book b
    WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Book> searchBooks(@Param("keyword") String keyword);
}