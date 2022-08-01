package com.bookstore.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    @NotEmpty
    String isbn;
    @NotEmpty
    String title;
    @NotNull
    List<Author> authors;
    @NotNull
    Integer year;
    @NotNull
    Double price;
    @NotEmpty
    String genre;
}
