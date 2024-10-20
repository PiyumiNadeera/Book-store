package com.onlinebookstore.book_store.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    private String customerEmail;
    private String customerContact;
    private String customerUsername;
    private String customerPassword;

}
