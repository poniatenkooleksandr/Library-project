package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
    SELECT DISTINCT b FROM Book b
    JOIN b.authors a
    WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Book> searchBooks(@Param("keyword") String keyword);

    @Query("""
    SELECT DISTINCT b FROM Book b
    JOIN b.authors a
    WHERE a.id = :authorId
    """)
    List<Book> findByAuthorId(@Param("authorId") Long authorId);
}