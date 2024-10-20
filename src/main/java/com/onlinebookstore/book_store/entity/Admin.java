package com.onlinebookstore.book_store.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "administrators")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    private String adminEmail;
    private String adminUsername;
    private String adminPassword;

}
