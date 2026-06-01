package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataLoader(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() == 0) {
            Author shevchenko = authorRepository.save(
                    new Author("Тарас Шевченко", "Україна")
            );

            Author franko = authorRepository.save(
                    new Author("Іван Франко", "Україна")
            );

            Author lesya = authorRepository.save(
                    new Author("Леся Українка", "Україна")
            );

            bookRepository.save(new Book("Кобзар", 1840, shevchenko));
            bookRepository.save(new Book("Захар Беркут", 1883, franko));
            bookRepository.save(new Book("Мойсей", 1905, franko));
            bookRepository.save(new Book("Лісова пісня", 1911, lesya));
        }
    }
}