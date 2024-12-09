package com.onlinebookstore.book_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookstore.book_store.entity.Book;
import com.onlinebookstore.book_store.payloads.requests.BookRequests;
import com.onlinebookstore.book_store.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
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

    @GetMapping("/uploads/{imageName}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String imageName){
        try{
            Path filepath = Paths.get("books/uploads").resolve(imageName).normalize();
            UrlResource resource = new UrlResource(filepath.toUri());

            if(resource.exists()){
                return new ResponseEntity<>(resource, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestParam("bookDetails") String bookDetails,@RequestParam("bookImage") MultipartFile bookImage){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            BookRequests bookRequest = objectMapper.readValue(bookDetails,BookRequests.class);

            if(bookImage != null && !bookImage.isEmpty()){
                String uploadDir = "books/uploads";
                String fileName = bookImage.getOriginalFilename();
                Path path = Paths.get(uploadDir,fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, bookImage.getBytes());
                String imageUrl = "http://localhost:8080/"+uploadDir+"/"+fileName;
                bookRequest.setBookImage(imageUrl);
            }

            return new ResponseEntity<>(bookService.addBook(bookRequest), HttpStatus.CREATED);
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
