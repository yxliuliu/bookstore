package com.bookstore.project.common.database;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookEntity {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "authors")
    private String authorIds;

    @Column(name = "year")
    private Integer year;

    @Column(name = "price")
    private Double price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}

