package com.onlinebookstore.book_store.service;

import com.onlinebookstore.book_store.entity.Book;
import com.onlinebookstore.book_store.repository.BookRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElseThrow(null);
    }

    @Override
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book){
        Book existingBook = bookRepository.findById(id).orElseThrow(null);

        if(existingBook != null){
            existingBook.setBookName(book.getBookName());
            existingBook.setAvailability(book.getAvailability());
            existingBook.setPrice(book.getPrice());
            existingBook.setAuthorId(book.getAuthorId());
            existingBook.setDiscountId(book.getDiscountId());
            existingBook.setCategoryId(book.getCategoryId());
            existingBook.setPublisherId(book.getPublisherId());

            return bookRepository.save(existingBook);
        }
        return null;

    }

    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}
