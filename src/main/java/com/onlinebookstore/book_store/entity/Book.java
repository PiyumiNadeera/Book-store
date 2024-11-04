package com.onlinebookstore.book_store.entity;

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

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

}
