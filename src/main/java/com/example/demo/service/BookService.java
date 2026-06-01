package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book saveBook(Book book) {
        Long authorId = book.getAuthor().getId();

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBook(id);

        book.setTitle(updatedBook.getTitle());
        book.setYear(updatedBook.getYear());

        if (updatedBook.getAuthor() != null) {
            Author author = authorRepository.findById(updatedBook.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

public List<Book> search(String keyword) {
    return bookRepository.searchBooks(keyword);
}

public List<Book> findByAuthor(Long authorId) {
    return bookRepository.findByAuthorId(authorId);
}
}