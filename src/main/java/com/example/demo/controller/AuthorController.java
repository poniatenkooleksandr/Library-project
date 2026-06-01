package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository repository;

    public AuthorController(AuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return repository.save(author);
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        repository.deleteById(id);
        return "Author deleted";
    }
}