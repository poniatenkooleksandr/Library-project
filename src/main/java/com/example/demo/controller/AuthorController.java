package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Country;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CountryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorController(AuthorRepository authorRepository,
                            CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        String countryName = author.getCountry().getName();

        Country country = new Country(countryName);
        country = countryRepository.save(country);

        author.setCountry(country);

        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
public Author updateAuthor(@PathVariable Long id,
                           @RequestBody Author updatedAuthor) {

    Author author = authorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Author not found"));

    author.setName(updatedAuthor.getName());

    if (updatedAuthor.getCountry() != null) {
        String countryName = updatedAuthor.getCountry().getName();

        Country country = new Country(countryName);
        country = countryRepository.save(country);

        author.setCountry(country);
    }

    return authorRepository.save(author);
}

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "Author deleted";
    }
    
}