package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Country;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataLoader(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            CountryRepository countryRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) {

        if (authorRepository.count() == 0) {

    Country ukraine = new Country();
ukraine.setName("Україна");
ukraine = countryRepository.save(ukraine);

Country england = new Country();
england.setName("Англія");
england = countryRepository.save(england);

    Author shevchenko = authorRepository.save(new Author("Тарас Шевченко", ukraine));
    Author franko = authorRepository.save(new Author("Іван Франко", ukraine));
    Author lesya = authorRepository.save(new Author("Леся Українка", ukraine));
    Author shakespeare = authorRepository.save(new Author("Вільям Шекспір", england));

    bookRepository.save(new Book("Кобзар", 1840, List.of(shevchenko)));
    bookRepository.save(new Book("Захар Беркут", 1883, List.of(franko)));
    bookRepository.save(new Book("Лісова пісня", 1911, List.of(lesya)));
    bookRepository.save(new Book("Romeo and Juliet", 1597, List.of(shakespeare)));

    bookRepository.save(new Book(
            "Українська література",
            2024,
            List.of(shevchenko, franko, lesya)
    ));
}
        }
    }
