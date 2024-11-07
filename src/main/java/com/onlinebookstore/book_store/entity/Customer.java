package com.onlinebookstore.book_store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @OneToMany(mappedBy = "orderId",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

}
