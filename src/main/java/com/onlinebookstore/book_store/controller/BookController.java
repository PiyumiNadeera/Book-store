package com.onlinebookstore.book_store.controller;

import com.onlinebookstore.book_store.entity.Book;
import com.onlinebookstore.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(){
        try{
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        try{
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        try {
            return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        try{
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
