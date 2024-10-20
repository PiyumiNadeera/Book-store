package com.onlinebookstore.book_store.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="discounts")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String discountType;
    private float discountValue;
    private Date discountStartDate;
    private Date discountEndDate;
    private Long bookId;

}
