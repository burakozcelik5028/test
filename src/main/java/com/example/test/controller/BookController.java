package com.example.test.controller;

import com.example.test.exception.ResourceNotFoundException;
import com.example.test.model.book;
import com.example.test.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    //get all books
    @GetMapping("books")
    public List<book> getAllBooks(){
        return this.bookRepository.findAll();

    }

    //get book by id
    @GetMapping("/books/{id}")
    public ResponseEntity<book> getBookById(@PathVariable(value="id") Long bookId)
            throws ResourceNotFoundException {

            book Book = bookRepository.findById(bookId)
                    .orElseThrow(()-> new ResourceNotFoundException("Book could not be found for this id ::" + bookId));
            return ResponseEntity.ok().body(Book);
        }
    }

