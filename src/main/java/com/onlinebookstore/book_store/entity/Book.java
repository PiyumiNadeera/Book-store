package com.onlinebookstore.book_store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String availability;
    private float price;
    private Long discountId;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)

    private Author author;

    private Long publisherId;
    private Long categoryId;





}
