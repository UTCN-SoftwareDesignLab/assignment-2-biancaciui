package com.lab4.demo.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String author;

    @Column(length = 50)
    private String publisher;

    @NotNull
    @Column(nullable = false)
    private int amount;

    @Column(length = 30)
    private String genre;

    @Column(nullable = false)
    private float price;

}
